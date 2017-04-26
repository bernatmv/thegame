package com.thegame.server.engine.intern.tasks;

import com.thegame.server.common.functional.Triple;
import com.thegame.server.common.functional.Tuple;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.messages.input.MoveMessageBean;

/**
 * @author afarre
 */
@Task(MoveMessageBean.class)
public class MoveTask extends BaseMessageTask<MoveMessageBean>{

	private LocationService locationService;
	private PlayerService playerService;
	
	
	public MoveTask(final MoveMessageBean _messageBean) {
		this(_messageBean,EngineServiceFactory.LOCATION.getInstance(LocationService.class),EngineServiceFactory.PLAYER.getInstance(PlayerService.class));
	}
	public MoveTask(final MoveMessageBean _messageBean,final LocationService _locationService,final PlayerService _playerService) {
		super(_messageBean);
		this.locationService=_locationService;
		this.playerService=_playerService;
	}

	
	@Override
	public void execute() {
		
		getMessageBean()
			.map(moveMessage -> new Tuple<>(this.playerService.getPlayer(moveMessage.getSender()),moveMessage.getDirection()))
			.map(moveTuple -> new Triple<>(this.locationService.getArea(moveTuple.getObject1().getArea()),moveTuple.getObject1(),moveTuple.getObject2()))
			.map(moveTriple -> new Triple<>(moveTriple.getObject1(),this.locationService.getExit(moveTriple.getObject1().getId(),moveTriple.getObject3()),moveTriple.getObject2()))
			.ifPresent(moveTriple -> {
				this.locationService.removePlayer(moveTriple.getObject1(), moveTriple.getObject3(),moveTriple.getObject2());
				this.locationService.addPlayer(moveTriple.getObject2(), moveTriple.getObject3(),moveTriple.getObject1());
				this.playerService.movePlayer(moveTriple.getObject3().getName(), moveTriple.getObject2().getId());
				moveTriple.getObject3().getChannel().accept(moveTriple.getObject2());
			});
	}
}
