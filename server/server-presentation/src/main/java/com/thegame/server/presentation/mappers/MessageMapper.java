package com.thegame.server.presentation.mappers;

import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.input.ChatMessageBean;
import com.thegame.server.engine.messages.input.MoveMessageBean;
import com.thegame.server.engine.messages.output.PlayerEnteringAreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerExitingAreaMessageBean;
import com.thegame.server.presentation.messages.input.ChatMessage;
import com.thegame.server.presentation.messages.input.MoveMessage;
import com.thegame.server.presentation.messages.output.LoadRoomMessage;
import com.thegame.server.presentation.messages.output.PlayerEntersRoomMessage;
import com.thegame.server.presentation.messages.output.PlayerLeavesRoomMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author afarre
 */
@Mapper
public interface MessageMapper {

	//INPUT
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

	//OUTPUT
	@Converter
	@Mappings({
		@Mapping(target="time",ignore=true),
		@Mapping(target="recipient",ignore=true)
	})
	public ChatMessage toMessage(final ChatMessageBean _messageBean);
	@Converter
	@Mappings({
		@Mapping(target="time",ignore=true)
	})
	public LoadRoomMessage toMessage(final AreaMessageBean _messageBean);
	@Converter
	@Mappings({
		@Mapping(target="time",ignore=true),
		@Mapping(target="player",source="player.name"),
		@Mapping(target="from",source="fromArea")
	})
	public PlayerEntersRoomMessage toMessage(final PlayerEnteringAreaMessageBean _messageBean);
	@Converter
	@Mappings({
		@Mapping(target="time",ignore=true),
		@Mapping(target="player",source="player.name"),
	})
	public PlayerLeavesRoomMessage toMessage(final PlayerExitingAreaMessageBean _messageBean);
}
