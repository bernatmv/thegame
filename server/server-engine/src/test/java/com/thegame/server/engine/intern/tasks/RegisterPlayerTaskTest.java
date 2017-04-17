package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.intern.BusinessServiceFactory;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.intern.services.MapperService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.intern.services.impl.LocationServiceImpl;
import com.thegame.server.engine.intern.services.impl.PlayerServiceImpl;
import com.thegame.server.engine.messages.AreaMessageBean;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.RegisterPlayerMessageBean;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.entities.Area;
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
public class RegisterPlayerTaskTest {
	
	private PlayerService playerService;
	private LocationService locationService;
	private Queue<IsMessageBean> messages;
	private Consumer<IsMessageBean> playerChannel;
	
	@Before
	public void setUp(){
		LocationDao mocketLocationDao=Mockito.mock(LocationDao.class);
		Area area=new Area();
		area.setId(Configuration.INITIAL_AREA.getValue());
		area.setTitle("Initial area");
		area.setShortDescription("Initial area - Short description");
		area.setDescription("Initial area - Description");
		Mockito.when(mocketLocationDao.loadAreas()).thenReturn(Stream.of(area).collect(Collectors.toList()));
		this.locationService=new LocationServiceImpl(mocketLocationDao);
		this.playerService=new PlayerServiceImpl();
		this.messages=new LinkedList<>();
		this.playerChannel=messageBean -> this.messages.add(messageBean);
	}

	private PlayerData createPlayer(final String _name){
		return PlayerData.builder()
							.name(_name)
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

		PlayerData expectedPlayer=createPlayer("newPlayer");
		AreaMessageBean expectedArea=new AreaMessageBean();
		expectedArea.setId(Configuration.INITIAL_AREA.getValue());
		expectedArea.setTitle("Initial area");
		expectedArea.setShortDescription("Initial area - Short description");
		expectedArea.setDescription("Initial area - Description");
		
		RegisterPlayerTask instance=new RegisterPlayerTask(message,this.playerService,this.locationService,BusinessServiceFactory.MAPPER.getInstance(MapperService.class));
		instance.execute();
		Assert.assertTrue(this.playerService.existPlayer("newPlayer"));
		Assert.assertEquals(expectedPlayer,this.playerService.getPlayer("newPlayer"));
		Assert.assertEquals(1,this.messages.size());
		Assert.assertEquals(expectedArea,this.messages.poll());
	}
	
}
