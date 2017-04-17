package com.thegame.server.presentation.messages.mappers;

import com.thegame.server.engine.messages.AreaMessageBean;
import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.messages.MoveMessageBean;
import com.thegame.server.presentation.messages.ChatMessage;
import com.thegame.server.presentation.messages.MoveMessage;
import com.thegame.server.presentation.messages.RoomMessage;
import org.mapstruct.Mapper;

/**
 * @author afarre
 */
@Mapper
public interface MessageMapper {

	@Converter
	public ChatMessageBean chatMessateToBean(final ChatMessage _chatMessage);
	@Converter
	public ChatMessage beanToChatMessateMessage(final ChatMessageBean _chatMessage);

	@Converter
	public MoveMessageBean moveMessateToBean(final MoveMessage _chatMessage);
	@Converter
	public MoveMessage beanToMoveMessateMessage(final MoveMessageBean _chatMessage);

	@Converter
	public RoomMessage beanToChatMessateMessage(final AreaMessageBean _chatMessage);
}
