package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.messages.RegisterPlayerMessageBean;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.intern.services.MapperService;

/**
 * @author afarre
 */
@Task(RegisterPlayerMessageBean.class)
public class RegisterPlayerTask extends BaseMessageTask<RegisterPlayerMessageBean>{

	
	private final PlayerService playerService;
	private final LocationService locationService;
	private final MapperService mapper;
	
	
	public RegisterPlayerTask(final RegisterPlayerMessageBean _messageBean) {
		this(_messageBean
					,EngineServiceFactory.PLAYER.getInstance(PlayerService.class)
					,EngineServiceFactory.LOCATION.getInstance(LocationService.class)
					,EngineServiceFactory.MAPPER.getInstance(MapperService.class));
	}
	public RegisterPlayerTask(final RegisterPlayerMessageBean _messageBean,final PlayerService _playerService,final LocationService _locationService,final MapperService _mapper) {
		super(_messageBean);
		this.playerService=_playerService;
		this.locationService=_locationService;
		this.mapper=_mapper;
	}


	@Override
	public void execute() {

		getMessageBean()
			.filter(registerPlayerBean -> !playerService.existPlayer(registerPlayerBean.getSender()))
			.map(registerPlayerBean -> mapper.toData(registerPlayerBean))
			.ifPresent(playerData -> playerService
											.registerPlayer(playerData)
											.getChannel()
											.accept(locationService
														.getInitialArea()));
	}
}
