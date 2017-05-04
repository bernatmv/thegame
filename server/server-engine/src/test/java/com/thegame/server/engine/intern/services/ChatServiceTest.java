package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.services.impl.ChatServiceImpl;
import com.thegame.server.engine.intern.services.impl.LocationServiceImpl;
import com.thegame.server.engine.intern.services.impl.PlayerServiceImpl;
import com.thegame.server.engine.messages.input.ChatMessageBean;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.entities.AreaExitId;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author afarre
 */
public class ChatServiceTest {
	
	private ChatService instance;
	private PlayerService playerService;
	private LocationService locationService;
	private Queue<IsMessageBean> messages;
	private Consumer<IsMessageBean> playerChannel;
	
	@Before
	public void setup(){
		LocationDao mocketLocationDao=Mockito.mock(LocationDao.class);
		Area area1=Area.builder()
						.id(Configuration.INITIAL_AREA.getValue())
						.title("Room-001 area")
						.exits(new ArrayList<>())
						.description("Room-001 area - Description")
						.build();
		Area area2=Area.builder()
						.id("beta-room-002")
						.title("Room-002 area")
						.exits(new ArrayList<>())
						.description("Room-002 area - Description")
						.build();
		Area area3=Area.builder()
						.id("beta-room-003")
						.title("Room-003 area")
						.exits(new ArrayList<>())
						.description("Room-003 area - Description")
						.build();
		AreaExit areaExit1=AreaExit.builder()
						.id(AreaExitId.builder()
										.area(area1)
										.name("north")
										.build())
						.toArea(area2)
						.build();
		AreaExit areaExit2=AreaExit.builder()
						.id(AreaExitId.builder()
										.area(area1)
										.name("south")
										.build())
						.toArea(area3)
						.build();
		area1.getExits().add(areaExit1);
		area1.getExits().add(areaExit2);
		Mockito.when(mocketLocationDao.loadAreas()).thenReturn(Stream.of(area1,area2,area3).collect(Collectors.toList()));
		this.playerService=new PlayerServiceImpl();
		this.locationService=new LocationServiceImpl(mocketLocationDao,EngineServiceFactory.MAPPER.getInstance(MapperService.class),this.playerService);
		this.messages=new LinkedList<>();
		this.playerChannel=messageBean -> this.messages.add(messageBean);
		instance=new ChatServiceImpl(playerService,locationService);
	}

	private PlayerMessageBean createPlayer(final String _name){
		PlayerMessageBean reply= PlayerMessageBean.builder()
							.name(_name)
							.channel(this.playerChannel)
							.build();
		Assert.assertEquals(_name, reply.getName());
		Assert.assertEquals(this.playerChannel, reply.getChannel());
		
		return reply;
	}
	private ChatMessageBean createChatMessageBean(final ChatMessageBean.MessageType _type,final String _sender,final String _receiver,final String _message){

		ChatMessageBean reply=ChatMessageBean.builder()
						.type(_type)
						.sender(_sender)
						.recipient(_receiver)
						.message(_message)
						.build();
		Assert.assertEquals(_type, reply.getType());
		Assert.assertEquals(_sender, reply.getSender());
		Assert.assertEquals(_receiver, reply.getRecipient());
		Assert.assertEquals(_message, reply.getMessage());

		return reply;
	}
	
	
	/**
	 * Test of whisper method, of class ChatService.
	 */
	@Test
	public void testWhisper() {
		System.out.println("whisper");
		this.playerService.registerPlayer(createPlayer("sender"),"beta-room-001");
		this.playerService.registerPlayer(createPlayer("receiver"),"beta-room-001");
		this.playerService.registerPlayer(createPlayer("ignored"),"beta-room-001");		
		
		String _fromPlayer="sender";
		String _toPlayer="receiver";
		String _message="test";
		Set expected=Stream.of(createChatMessageBean(ChatMessageBean.MessageType.WHISPER, _fromPlayer, _fromPlayer, _message),
											createChatMessageBean(ChatMessageBean.MessageType.WHISPER, _fromPlayer, _toPlayer, _message))
										.collect(Collectors.toSet());

		instance.whisper(_fromPlayer, _toPlayer, _message);
		Assert.assertEquals(2,messages.size());
		Assert.assertEquals(expected,Stream.of(messages.poll(),messages.poll()).collect(Collectors.toSet()));
	}

	/**
	 * Test of whisper method, of class ChatService.
	 */
	@Test(expected=EngineException.class)
	public void testWhisper_unknownsender() {
		
		try{
			System.out.println("whisper_unknownsender");
			this.playerService.registerPlayer(createPlayer("receiver"),"beta-room-001");
			this.playerService.registerPlayer(createPlayer("ignored"),"beta-room-001");		

			String _fromPlayer="sender";
			String _toPlayer="receiver";
			String _message="test";
			instance.whisper(_fromPlayer, _toPlayer, _message);
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.UNKNOWN_SENDER,e.getExceptionType());
			throw e;
		}
	}

	/**
	 * Test of whisper method, of class ChatService.
	 */
	@Test(expected=EngineException.class)
	public void testWhisper_unknownrecipient() {
		
		try{
			System.out.println("whisper_unknownrecipient");
			this.playerService.registerPlayer(createPlayer("sender"),"beta-room-001");
			this.playerService.registerPlayer(createPlayer("ignored"),"beta-room-001");		

			String _fromPlayer="sender";
			String _toPlayer="receiver";
			String _message="test";
			instance.whisper(_fromPlayer, _toPlayer, _message);
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.UNKNOWN_RECIPIENT,e.getExceptionType());
			throw e;
		}
	}

	/**
	 * Test of say method, of class ChatService.
	 */
	@Test
	public void testSay() {
		System.out.println("say");
		PlayerMessageBean sender=createPlayer("sender");
		this.playerService.registerPlayer(sender,"beta-room-001");
		PlayerMessageBean receiver1=createPlayer("receiver1");
		this.playerService.registerPlayer(createPlayer("receiver1"),"beta-room-001");
		PlayerMessageBean receiver2=createPlayer("receiver2");
		this.playerService.registerPlayer(createPlayer("receiver2"),"beta-room-001");		
		PlayerMessageBean ignored=createPlayer("ignored");
		this.playerService.registerPlayer(createPlayer("ignored"),"beta-room-002");		
		AreaMessageBean logonArea=this.locationService.getArea(LocationService.LOGON_AREA);
		this.locationService.addPlayer(this.locationService.getArea("beta-room-001"), sender, logonArea);
		this.locationService.addPlayer(this.locationService.getArea("beta-room-001"), receiver1, logonArea);
		this.locationService.addPlayer(this.locationService.getArea("beta-room-001"), receiver2, logonArea);
		this.locationService.addPlayer(this.locationService.getArea("beta-room-002"), ignored, logonArea);
		this.messages.clear();
		String _fromPlayer="sender";
		String _message="test";
		Set expected=Stream.of(createChatMessageBean(ChatMessageBean.MessageType.SAY, _fromPlayer, null, _message),
											createChatMessageBean(ChatMessageBean.MessageType.SAY, _fromPlayer, null, _message),
											createChatMessageBean(ChatMessageBean.MessageType.SAY, _fromPlayer, null, _message))
										.collect(Collectors.toSet());

		instance.say(_fromPlayer, _message);
		Assert.assertEquals(3,messages.size());
		Assert.assertEquals(expected,Stream.of(messages.poll(),messages.poll(),messages.poll()).collect(Collectors.toSet()));
	}

	/**
	 * Test of say method, of class ChatService.
	 */
	@Test(expected=EngineException.class)
	public void testSay_unknownsender() {

		try{
			System.out.println("say_unknownsender");
			this.playerService.registerPlayer(createPlayer("receiver1"),"beta-room-001");
			this.playerService.registerPlayer(createPlayer("receiver2"),"beta-room-001");		

			String _fromPlayer="sender";
			String _message="test";
			instance.say(_fromPlayer, _message);
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.UNKNOWN_SENDER,e.getExceptionType());
			throw e;
		}
	}


	/**
	 * Test of yell method, of class ChatService.
	 */
	@Test
	public void testYell() {
		System.out.println("yell");
		this.playerService.registerPlayer(createPlayer("sender"),"beta-room-001");
		this.playerService.registerPlayer(createPlayer("receiver1"),"beta-room-001");
		this.playerService.registerPlayer(createPlayer("receiver2"),"beta-room-002");		
		
		String _fromPlayer="sender";
		String _message="test";
		Set expected=Stream.of(createChatMessageBean(ChatMessageBean.MessageType.YELL, _fromPlayer, null, _message.toUpperCase()),
											createChatMessageBean(ChatMessageBean.MessageType.YELL, _fromPlayer, null, _message.toUpperCase()),
											createChatMessageBean(ChatMessageBean.MessageType.YELL, _fromPlayer, null, _message.toUpperCase()))
										.collect(Collectors.toSet());

		instance.yell(_fromPlayer, _message);
		Assert.assertEquals(3,messages.size());
		Assert.assertEquals(expected,Stream.of(messages.poll(),messages.poll(),messages.poll()).collect(Collectors.toSet()));
	}

	/**
	 * Test of say method, of class ChatService.
	 */
	@Test(expected=EngineException.class)
	public void testYell_unknownsender() {

		try{
			System.out.println("yell_unknownsender");
			this.playerService.registerPlayer(createPlayer("receiver1"),"beta-room-001");
			this.playerService.registerPlayer(createPlayer("receiver2"),"beta-room-001");		

			String _fromPlayer="sender";
			String _message="test";
			instance.yell(_fromPlayer, _message);
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.UNKNOWN_SENDER,e.getExceptionType());
			throw e;
		}
	}
}
