package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.messages.input.UnregisterPlayerMessageBean;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;

/**
 * @author afarre
 */
@Task(UnregisterPlayerMessageBean.class)
public class UnregisterPlayerTask extends BaseMessageTask<UnregisterPlayerMessageBean>{

	private final PlayerService playerService;
	private final LocationService locationService;

	
	public UnregisterPlayerTask(final UnregisterPlayerMessageBean _messageBean) {
		this(_messageBean
					,EngineServiceFactory.PLAYER.getInstance(PlayerService.class)
					,EngineServiceFactory.LOCATION.getInstance(LocationService.class));
	}
	public UnregisterPlayerTask(final UnregisterPlayerMessageBean _messageBean,final PlayerService _playerService,final LocationService _locationService) {
		super(_messageBean);
		this.playerService=_playerService;
		this.locationService=_locationService;
	}

	
	@Override
	public void execute() {

		getMessageBean()
			.map(unregisterPlayerMessage -> unregisterPlayerMessage.getSender())
			.ifPresent(playerName -> {
										final PlayerMessageBean player=this.playerService.getPlayer(playerName);
										this.playerService.unregisterPlayer(player);
										final AreaMessageBean currentArea=this.locationService.getArea(player.getArea());
										final AreaMessageBean newArea=this.locationService.getLogonArea();
										this.locationService.removePlayer(currentArea, player,newArea);
									});
	}
}
