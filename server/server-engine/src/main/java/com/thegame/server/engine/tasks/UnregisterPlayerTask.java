package com.thegame.server.engine.tasks;

import com.thegame.server.engine.messages.UnregisterPlayerMessageBean;
import com.thegame.server.engine.services.BusinessServiceFactory;
import com.thegame.server.engine.services.PlayerService;

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
		BusinessServiceFactory.PLAYER
			.getInstance(PlayerService.class)
			.unregisterPlayer(getMessageBean().getName());
	}
}
