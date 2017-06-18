package com.thegame.server.presentation.mappers;

import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.input.ChatMessageBean;
import com.thegame.server.engine.messages.output.NonPlayerMessageBean;
import com.thegame.server.engine.messages.output.PlayerEnteringAreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerExitingAreaMessageBean;
import com.thegame.server.presentation.messages.common.NonPlayer;
import com.thegame.server.presentation.messages.common.Profile;
import com.thegame.server.presentation.messages.input.ChatMessage;
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
public interface OutputMessageMapper {

	public Profile toBeanProfile(final NonPlayerMessageBean _messageBean);
	@Mappings({
		@Mapping(target="profile",expression="java(toBeanProfile(_messageBean))"),
	})
	public NonPlayer toBeanNonPlayer(final NonPlayerMessageBean _messageBean);

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
