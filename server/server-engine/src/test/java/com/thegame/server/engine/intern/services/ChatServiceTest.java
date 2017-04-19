package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.intern.services.impl.ChatServiceImpl;
import com.thegame.server.engine.intern.services.impl.PlayerServiceImpl;
import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.messages.IsMessageBean;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author afarre
 */
public class ChatServiceTest {
	
	private ChatService instance;
	private PlayerService playerService;
	private Queue<IsMessageBean> messages;
	private Consumer<IsMessageBean> playerChannel;
	
	@Before
	public void setup(){
		this.playerService=new PlayerServiceImpl();
		this.messages=new LinkedList<>();
		this.playerChannel=messageBean -> this.messages.add(messageBean);
		instance=new ChatServiceImpl(playerService);
	}

	private PlayerData createPlayer(final String _name){
		return PlayerData.builder()
							.name(_name)
							.channel(this.playerChannel)
							.build();
	}
	private ChatMessageBean createChatMessageBean(final ChatMessageBean.MessageType _type,final String _sender,final String _receiver,final String _message){
		return 	ChatMessageBean.builder()
						.type(_type)
						.sender(_sender)
						.recipient(_receiver)
						.message(_message)
						.build();

	}
	
	
	/**
	 * Test of whisper method, of class ChatService.
	 */
	@Test
	public void testWhisper() {
		System.out.println("whisper");
		this.playerService.registerPlayer(createPlayer("sender"));
		this.playerService.registerPlayer(createPlayer("receiver"));
		this.playerService.registerPlayer(createPlayer("ignored"));		
		
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
			this.playerService.registerPlayer(createPlayer("receiver"));
			this.playerService.registerPlayer(createPlayer("ignored"));		

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
			this.playerService.registerPlayer(createPlayer("sender"));
			this.playerService.registerPlayer(createPlayer("ignored"));		

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
		this.playerService.registerPlayer(createPlayer("sender"));
		this.playerService.registerPlayer(createPlayer("receiver1"));
		this.playerService.registerPlayer(createPlayer("receiver2"));		
		
		String _fromPlayer="sender";
		String _message="test";
		Set expected=Stream.of(createChatMessageBean(ChatMessageBean.MessageType.SAY, _fromPlayer, _fromPlayer, _message),
											createChatMessageBean(ChatMessageBean.MessageType.SAY, _fromPlayer, "receiver1", _message),
											createChatMessageBean(ChatMessageBean.MessageType.SAY, _fromPlayer, "receiver2", _message))
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
			this.playerService.registerPlayer(createPlayer("receiver1"));
			this.playerService.registerPlayer(createPlayer("receiver2"));		

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
		this.playerService.registerPlayer(createPlayer("sender"));
		this.playerService.registerPlayer(createPlayer("receiver1"));
		this.playerService.registerPlayer(createPlayer("receiver2"));		
		
		String _fromPlayer="sender";
		String _message="test";
		Set expected=Stream.of(createChatMessageBean(ChatMessageBean.MessageType.YELL, _fromPlayer, _fromPlayer, _message.toUpperCase()),
											createChatMessageBean(ChatMessageBean.MessageType.YELL, _fromPlayer, "receiver1", _message.toUpperCase()),
											createChatMessageBean(ChatMessageBean.MessageType.YELL, _fromPlayer, "receiver2", _message.toUpperCase()))
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
			this.playerService.registerPlayer(createPlayer("receiver1"));
			this.playerService.registerPlayer(createPlayer("receiver2"));		

			String _fromPlayer="sender";
			String _message="test";
			instance.yell(_fromPlayer, _message);
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.UNKNOWN_SENDER,e.getExceptionType());
			throw e;
		}
	}
}
