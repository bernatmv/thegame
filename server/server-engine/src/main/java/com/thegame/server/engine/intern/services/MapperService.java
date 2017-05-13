package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import com.thegame.server.engine.messages.input.RegisterPlayerMessageBean;
import com.thegame.server.engine.messages.output.ItemMessageBean;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.Item;
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

	//TO ENTITY
	@Mappings({
		@Mapping(target="exits",ignore=true),
		@Mapping(target="items",ignore=true),
	})
	public Area toEntity(final AreaMessageBean _messageBean);
	@Mappings({
		@Mapping(target="gender",expression="java( _messageBean.getGender().getCode())"),
		@Mapping(target="areas",ignore=true),
	})
	public Item toEntity(final ItemMessageBean _messageBean);

	//TO MESSAGEBEAN
	@Mappings({
		@Mapping(target="exits",expression="java( _messageEntity.getExits()"
																	+ ".stream()"
																	+ ".collect(java.util.stream.Collectors.toMap("
																								+ "areaExit -> areaExit.getId().getName(),"
																								+ "areaExit -> areaExit.getToArea().getId())))"),
		@Mapping(target="players",ignore=true)
	})
	public AreaMessageBean toMessageBean(final Area _messageEntity);
	public AreaMessageBean toMessageBean(final AreaData _messageBean);
	public PlayerMessageBean toMessageBean(final PlayerData _playerData);
	@Mappings({
		@Mapping(target="name",source="sender"),
		@Mapping(target="area",ignore=true)
	})
	public PlayerMessageBean toMessageBean(final RegisterPlayerMessageBean _messageBean);

	
	//TO DATA
	@Mappings({
		@Mapping(target="exits",expression="java( _messageEntity.getExits()"
																	+ ".stream()"
																	+ ".collect(java.util.stream.Collectors.toMap("
																								+ "areaExit -> areaExit.getId().getName(),"
																								+ "areaExit -> areaExit.getToArea().getId())))"),
		@Mapping(target="players",expression="java(new java.util.concurrent.CopyOnWriteArrayList())"),
		@Mapping(target="listeners",expression="java(new java.util.concurrent.CopyOnWriteArrayList())")
	})
	public AreaData toData(final Area _messageEntity);
}
