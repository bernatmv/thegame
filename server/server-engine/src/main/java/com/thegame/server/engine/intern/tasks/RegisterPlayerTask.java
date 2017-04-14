package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.messages.RegisterPlayerMessageBean;
import com.thegame.server.engine.intern.BusinessServiceFactory;
import com.thegame.server.engine.intern.services.PlayerService;

/**
 * @author afarre
 */
@Task(RegisterPlayerMessageBean.class)
public class RegisterPlayerTask extends BaseMessageTask<RegisterPlayerMessageBean>{

	public RegisterPlayerTask(final RegisterPlayerMessageBean _messageBean) {
		super(_messageBean);
	}


	@Override
	public void execute() {

		BusinessServiceFactory.PLAYER
			.getInstance(PlayerService.class)
			.registerPlayer(PlayerData.builder()
									.name(getMessageBean().getName())
									.channel(getMessageBean().getChannel())
									.build());
	}
}
