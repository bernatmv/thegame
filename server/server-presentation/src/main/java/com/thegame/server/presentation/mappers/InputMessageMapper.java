package com.thegame.server.presentation.mappers;

import com.thegame.server.engine.messages.input.ChatMessageBean;
import com.thegame.server.engine.messages.input.MoveMessageBean;
import com.thegame.server.presentation.messages.input.ChatMessage;
import com.thegame.server.presentation.messages.input.MoveMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author afarre
 */
@Mapper
public interface InputMessageMapper {

	@Converter
	@Mappings({
		@Mapping(target="sender",ignore=true),
	})
	public ChatMessageBean toBean(final ChatMessage _message);
	@Converter
	@Mappings({
		@Mapping(target="sender",ignore=true),
	})
	public MoveMessageBean toBean(final MoveMessage _message);
}
