package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.intern.data.ItemData;
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
public interface DataMapperService {
	
	public static final DataMapperService instance=Mappers.getMapper(DataMapperService.class);

	@Mappings({
		@Mapping(target="gender",expression="java(com.thegame.server.engine.messages.common.Gender.valueOf(_entity.getGender()))"),
		@Mapping(target="name",source="_name"),
	})
	public ItemData toData(final Item _entity,final String _name);
	
	@Mappings({
		@Mapping(target="exits",expression="java( _entity.getExits()"
															+ ".stream()"
															+ ".filter(areaExit -> areaExit!=null)"
															+ ".collect(java.util.stream.Collectors.toMap("
																	+ "areaExit -> areaExit.getId().getName(),"
																	+ "areaExit -> areaExit.getToArea().getId())))"),
		@Mapping(target="items",expression="java( _entity.getItems()"
															+ ".stream()"
															+ ".filter(areaItem -> areaItem!=null)"
															+ ".map(areaItem -> toData(areaItem.getId().getItem(),areaItem.getId().getName()))"
															+ ".collect(java.util.stream.Collectors.toList()))"),
		@Mapping(target="players",expression="java(new java.util.concurrent.CopyOnWriteArrayList())"),
		@Mapping(target="listeners",expression="java(new java.util.concurrent.CopyOnWriteArrayList())")
	})
	public AreaData toData(final Area _entity);
}
