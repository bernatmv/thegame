package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.messages.AreaMessageBean;
import com.thegame.server.engine.messages.RegisterPlayerMessageBean;
import com.thegame.server.persistence.entities.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author afarre
 */
@Mapper
public interface MapperService {
	
	public static final MapperService instance=Mappers.getMapper(MapperService.class);

	public Area toEntity(final AreaMessageBean _messageBean);
	public AreaMessageBean toMessageBean(final Area _messageBean);

	public AreaMessageBean toMessageBean(final AreaData _messageBean);
	public AreaData toData(final Area _messageBean);

	@Mappings({
		@Mapping(target="name",source="sender")
	})
	public PlayerData toData(final RegisterPlayerMessageBean _messageBean);
}
