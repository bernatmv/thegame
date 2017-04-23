package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.messages.input.UnregisterPlayerMessageBean;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.PlayerService;

/**
 * @author afarre
 */
@Task(UnregisterPlayerMessageBean.class)
public class UnregisterPlayerTask extends BaseMessageTask<UnregisterPlayerMessageBean>{

	public UnregisterPlayerTask(final UnregisterPlayerMessageBean _messageBean) {
		super(_messageBean);
	}

	@Override
	public void execute() {

		final PlayerService playerService=EngineServiceFactory.PLAYER
													.getInstance(PlayerService.class);
		getMessageBean()
			.map(unregisterPlayerMessage -> unregisterPlayerMessage.getSender())
			.ifPresent(playerName -> playerService.unregisterPlayer(playerName));
	}
}
