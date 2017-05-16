package com.thegame.server.data.loader.services;

import com.thegame.server.data.loader.beans.AreaImport;
import com.thegame.server.data.loader.beans.ItemImport;
import com.thegame.server.data.loader.beans.ItemInstanceImport;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaItem;
import com.thegame.server.persistence.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author afarre
 */
@Mapper
public interface ImportMapperService {
	
	public static final ImportMapperService instance=Mappers.getMapper(ImportMapperService.class);

	@Mappings({
		@Mapping(target="areas",ignore=true),
		@Mapping(target="gender",source="gender.code"),
	})
	public Item toEntity(final ItemImport _import);

	@Mappings({
		@Mapping(target="id.area.id",source="_areaId"),
		@Mapping(target="id.name",source="_import.name"),
		@Mapping(target="id.item.id",source="_import.id"),
	})
	public AreaItem toEntity(final ItemInstanceImport _import,final String _areaId);

	@Mappings({
		@Mapping(target="exits",ignore=true),
		@Mapping(target="items",expression="java(_import.getItems()"
																	+ ".stream()"
																	+ ".map(areaInstance -> "
																			+ "toEntity("
																				+ "areaInstance,"
																				+ "_import.getId()))"
																	+ ".collect("
																			+ "java.util.stream.Collectors.toList()))"),
	})
	public Area toEntity(final AreaImport _import);
}
