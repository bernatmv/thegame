package com.thegame.server.presentation.messages.mappers;

import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.input.ChatMessageBean;
import com.thegame.server.engine.messages.input.MoveMessageBean;
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
	public ChatMessageBean toBean(final ChatMessage _chatMessage);
	@Converter
	public ChatMessage toMessage(final ChatMessageBean _chatMessage);

	@Converter
	public MoveMessageBean toBean(final MoveMessage _chatMessage);
	@Converter
	public MoveMessage toMessage(final MoveMessageBean _chatMessage);

	@Converter
	public RoomMessage toMessage(final AreaMessageBean _chatMessage);
}
