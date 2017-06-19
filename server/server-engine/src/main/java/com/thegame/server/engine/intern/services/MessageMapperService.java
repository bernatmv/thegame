package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.intern.data.NonPlayerData;
import com.thegame.server.engine.messages.input.RegisterPlayerMessageBean;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.output.NonPlayerMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author afarre
 */
@Mapper
public interface MessageMapperService {
	
	public static final MessageMapperService instance=Mappers.getMapper(MessageMapperService.class);

	//TO MESSAGEBEAN
	public NonPlayerMessageBean toMessageBean(final NonPlayerData _data);
	@Mappings({
		@Mapping(target="enemies",ignore=true)
	})
	public AreaMessageBean toMessageBean(final AreaData _data);
	public PlayerMessageBean toMessageBean(final PlayerData _data);
	@Mappings({
		@Mapping(target="name",source="sender"),
		@Mapping(target="area",ignore=true)
	})
	public PlayerMessageBean toMessageBean(final RegisterPlayerMessageBean _message);
}
