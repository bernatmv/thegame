package com.thegame.server.presentation.messages.mappers;

import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.messages.MoveMessageBean;
import com.thegame.server.presentation.messages.ChatMessage;
import com.thegame.server.presentation.messages.MoveMessage;
import org.mapstruct.Mapper;

/**
 * @author afarre
 */
@Mapper
public interface MessageMapper {
 
	@Converter(ChatMessageBean.class)
    public ChatMessageBean chatMessateToBean(final ChatMessage _chatMessage);
	@Converter(ChatMessage.class)
	public ChatMessage beanToChatMessateMessage(final ChatMessageBean _chatMessage);

	@Converter(MoveMessageBean.class)
	public MoveMessageBean moveMessateToBean(final MoveMessage _chatMessage);
	@Converter(MoveMessage.class)
	public MoveMessage beanToMoveMessateMessage(final MoveMessageBean _chatMessage);
}
