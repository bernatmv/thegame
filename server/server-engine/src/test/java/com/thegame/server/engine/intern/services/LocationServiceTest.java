package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.services.impl.LocationServiceImpl;
import com.thegame.server.engine.intern.services.impl.PlayerServiceImpl;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerEnteringAreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerExitingAreaMessageBean;
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
public class LocationServiceTest {
	
	private LocationService instance;
	private PlayerService playerService;
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
		instance=new LocationServiceImpl(mocketLocationDao,EngineServiceFactory.MAPPER.getInstance(MapperService.class),playerService);
		this.messages=new LinkedList<>();
		this.playerChannel=messageBean -> this.messages.add(messageBean);
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
	
	/**
	 * Test of getInitialArea method, of class LocationService.
	 */
	@Test
	public void testGetInitialArea() {
		System.out.println("getInitialArea");
		AreaMessageBean expected=AreaMessageBean.builder()
												.id(Configuration.INITIAL_AREA.getValue())
												.title("Room-001 area")
												.description("Room-001 area - Description")
												.exit("north", "beta-room-002")
												.exit("south", "beta-room-003")
												.build();
		AreaMessageBean result=instance.getInitialArea();
		Assert.assertEquals(expected, result);
	}
	
	/**
	 * Test of getArea method, of class LocationServiceImpl.
	 */
	@Test
	public void testGetArea() {
		System.out.println("getArea");
		AreaMessageBean expected=AreaMessageBean.builder()
												.id("beta-room-003")
												.title("Room-003 area")
												.description("Room-003 area - Description")
												.build();
		AreaMessageBean result=instance.getArea("beta-room-003");
		Assert.assertEquals(expected, result);
	}

	/**
	 * Test of notify method, of class LocationServiceImpl.
	 */
	@Test
	public void testNotify() {
		System.out.println("notify");
		PlayerMessageBean sender=createPlayer("sender");
		this.playerService.registerPlayer(sender,"beta-room-001");
		PlayerMessageBean receiver1=createPlayer("receiver1");
		this.playerService.registerPlayer(createPlayer("receiver1"),"beta-room-001");
		PlayerMessageBean receiver2=createPlayer("receiver2");
		this.playerService.registerPlayer(createPlayer("receiver2"),"beta-room-001");		
		PlayerMessageBean ignored=createPlayer("ignored");
		this.playerService.registerPlayer(createPlayer("ignored"),"beta-room-002");		
		AreaMessageBean logonArea=this.instance.getArea(LocationService.LOGON_AREA);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), sender, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), receiver1, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), receiver2, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-002"), ignored, logonArea);
		this.messages.clear();
		AreaMessageBean expected=AreaMessageBean.builder()
												.id("beta-room-003")
												.title("Room-003 area")
												.description("Room-003 area - Description")
												.build();
		
		this.instance.notify(this.instance.getArea("beta-room-001"), expected);
		Assert.assertEquals(3,messages.size());
		while(!messages.isEmpty()){
			Assert.assertEquals(expected,messages.poll());
		}
	}

	/**
	 * Test of getExit method, of class LocationServiceImpl.
	 */
	@Test
	public void testGetExit() {
		System.out.println("getExit");
		AreaMessageBean expected=AreaMessageBean.builder()
						.id("beta-room-002")
						.title("Room-002 area")
						.description("Room-002 area - Description")
						.build();
		AreaMessageBean result=this.instance.getExit(this.instance.getArea("beta-room-001"), "north");
		Assert.assertEquals(expected,result);
	}
	/**
	 * Test of getExit method, of class LocationServiceImpl.
	 */
	@Test(expected=EngineException.class)
	public void testGetExit_notExist() {
		System.out.println("getExit_notExist");
		try{
			AreaMessageBean result=this.instance.getExit(this.instance.getArea("beta-room-001"), "east");
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.NO_AREA_EXIT,e.getExceptionType());
			throw e;			
		}
	}

	/**
	 * Test of addPlayer method, of class LocationServiceImpl.
	 */
	@Test
	public void testAddPlayer() {
		System.out.println("addPlayer");
		PlayerMessageBean sender=createPlayer("sender");
		this.playerService.registerPlayer(sender,"beta-room-001");
		PlayerMessageBean receiver1=createPlayer("receiver1");
		this.playerService.registerPlayer(createPlayer("receiver1"),"beta-room-001");
		PlayerMessageBean receiver2=createPlayer("receiver2");
		this.playerService.registerPlayer(createPlayer("receiver2"),"beta-room-001");		
		PlayerMessageBean ignored=createPlayer("ignored");
		this.playerService.registerPlayer(createPlayer("ignored"),"beta-room-002");		
		AreaMessageBean logonArea=this.instance.getArea(LocationService.LOGON_AREA);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), sender, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), receiver1, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), receiver2, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-002"), ignored, logonArea);

		Set expected=Stream.of(PlayerEnteringAreaMessageBean.builder().player(receiver1).fromArea(LocationService.LOGON_AREA).build()
								,PlayerEnteringAreaMessageBean.builder().player(receiver2).fromArea(LocationService.LOGON_AREA).build())
							.collect(Collectors.toSet());

		Assert.assertEquals(3,messages.size());
		Assert.assertEquals(expected,messages.stream().collect(Collectors.toSet()));
	}

	/**
	 * Test of removePlayer method, of class LocationServiceImpl.
	 */
	@Test
	public void testRemovePlayer() {
		System.out.println("removePlayer");
		PlayerMessageBean sender=createPlayer("sender");
		this.playerService.registerPlayer(sender,"beta-room-001");
		PlayerMessageBean receiver1=createPlayer("receiver1");
		this.playerService.registerPlayer(createPlayer("receiver1"),"beta-room-001");
		PlayerMessageBean receiver2=createPlayer("receiver2");
		this.playerService.registerPlayer(createPlayer("receiver2"),"beta-room-001");		
		PlayerMessageBean ignored=createPlayer("ignored");
		this.playerService.registerPlayer(createPlayer("ignored"),"beta-room-002");		
		AreaMessageBean logonArea=this.instance.getArea(LocationService.LOGON_AREA);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), sender, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), receiver1, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-001"), receiver2, logonArea);
		this.instance.addPlayer(this.instance.getArea("beta-room-002"), ignored, logonArea);
		messages.clear();
		this.instance.removePlayer(this.instance.getArea("beta-room-001"), sender, logonArea);
		Set expected=Stream.of(PlayerExitingAreaMessageBean.builder().player(sender).exit(LocationService.LOGON_AREA).build())
							.collect(Collectors.toSet());

		Assert.assertEquals(2,messages.size());
		Assert.assertEquals(expected,messages.stream().collect(Collectors.toSet()));
	}
}
