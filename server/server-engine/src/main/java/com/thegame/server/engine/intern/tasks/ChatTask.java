package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.intern.BusinessServiceFactory;
import com.thegame.server.engine.intern.services.ChatService;
import java.util.Optional;

/**
 * @author afarre
 */
@Task(ChatMessageBean.class)
public class ChatTask extends BaseMessageTask<ChatMessageBean>{

	public ChatTask(final ChatMessageBean _messageBean) {
		super(_messageBean);
	}


	@Override
	public void execute() {
		
		final ChatService chatService=BusinessServiceFactory.CHAT
													.getInstance(ChatService.class);
		final ChatMessageBean.MessageType type=Optional.ofNullable(getMessageBean().getType())
														.orElse(ChatMessageBean.MessageType.SAY);
		switch(type){
			case SAY:		chatService.say(getMessageBean().getSender(),getMessageBean().getMessage());
							break;
			case WHISPER:	chatService.whisper(getMessageBean().getSender(),getMessageBean().getRecipient(),getMessageBean().getMessage());
							break;
			case YELL:		chatService.yell(getMessageBean().getSender(),getMessageBean().getMessage());
							break;
		}
	}
}
