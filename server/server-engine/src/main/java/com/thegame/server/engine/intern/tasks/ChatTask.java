package com.thegame.server.engine.intern.tasks;

import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.ChatService;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author afarre
 */
@Task(ChatMessageBean.class)
public class ChatTask extends BaseMessageTask<ChatMessageBean>{

	public ChatTask(final ChatMessageBean _messageBean) {
		super(_messageBean);
	}

	
	private Consumer<ChatMessageBean> getChatMessageConsumer(final Optional<ChatMessageBean.MessageType> _messageType){

		final ChatService chatService=EngineServiceFactory.CHAT
													.getInstance(ChatService.class);
		Consumer<ChatMessageBean> reply=null;
		
		switch(_messageType.orElse(ChatMessageBean.MessageType.SAY)){
			case SAY:		reply=(ChatMessageBean chatMessage) -> chatService.say(chatMessage.getSender(),chatMessage.getMessage());
							break;
			case WHISPER:	reply=(ChatMessageBean chatMessage) -> chatService.whisper(chatMessage.getSender(),chatMessage.getRecipient(),chatMessage.getMessage());
							break;
			case YELL:		reply=(ChatMessageBean chatMessage) -> chatService.yell(chatMessage.getSender(),chatMessage.getMessage());
							break;
		}
		
		return reply;
	}
	
	
	@Override
	public void execute() {
		
		getChatMessageConsumer(
					getMessageBean()
							.map(chatMessage -> chatMessage.getType()))
			.accept(getMessageBean().get());
	}
}
