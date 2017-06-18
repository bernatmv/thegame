package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.intern.services.MessageMapperService;
import com.thegame.server.engine.intern.services.NonPlayerService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.intern.services.impl.LocationServiceImpl;
import com.thegame.server.engine.intern.services.impl.NonPlayerServiceImpl;
import com.thegame.server.engine.intern.services.impl.PlayerServiceImpl;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import com.thegame.server.engine.messages.input.RegisterPlayerMessageBean;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.dtos.LocationArea;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
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
public class RegisterPlayerTaskTest{
	
	private PlayerService playerService;
	private NonPlayerService nonPlayerService;
	private LocationService locationService;
	private Queue<IsMessageBean> messages;
	private Consumer<IsMessageBean> playerChannel;
	
	@Before
	public void setUp(){
		LocationDao mocketLocationDao=Mockito.mock(LocationDao.class);
		LocationArea area=LocationArea.builder()
				.id(Configuration.INITIAL_AREA.getValue())
				.title("Initial area")
				.description("Initial area - Description")
				.exits(Collections.emptyMap())
				.items(Collections.emptyList())
				.build();
		Mockito.when(mocketLocationDao.loadAreas()).thenReturn(Stream.of(area).collect(Collectors.toList()));
		this.playerService=new PlayerServiceImpl();
		this.nonPlayerService=new NonPlayerServiceImpl();
		this.locationService=new LocationServiceImpl(mocketLocationDao,this.playerService,this.nonPlayerService);
		this.messages=new LinkedList<>();
		this.playerChannel=messageBean -> this.messages.add(messageBean);
	}

	private PlayerMessageBean createPlayer(final String _name){
		return PlayerMessageBean.builder()
							.name(_name)
							.area("beta-room-001")
							.channel(this.playerChannel)
							.build();
	}

	
	/**
	 * Test of execute method, of class RegisterPlayerTask.
	 */
	@Test
	public void testExecute() {
		System.out.println("execute");
		
		RegisterPlayerMessageBean message=RegisterPlayerMessageBean
			.builder()
			.sender("newPlayer")
			.channel(playerChannel).build();

		PlayerMessageBean expectedPlayer=createPlayer("newPlayer");
		AreaMessageBean expectedArea=AreaMessageBean.builder()
													.id(Configuration.INITIAL_AREA.getValue())
													.title("Initial area")
													.description("Initial area - Description")
													.players(Collections.emptyList())
													.build();
		
		RegisterPlayerTask instance=new RegisterPlayerTask(message,this.playerService,this.locationService,EngineServiceFactory.MESSAGEMAPPER.getInstance(MessageMapperService.class));
		instance.execute();
		Assert.assertTrue(this.playerService.existPlayer("newPlayer"));
		Assert.assertEquals(expectedPlayer,this.playerService.getPlayer("newPlayer"));
		Assert.assertEquals(1,this.messages.size());
		Assert.assertEquals(expectedArea,this.messages.poll());
	}
	
}
