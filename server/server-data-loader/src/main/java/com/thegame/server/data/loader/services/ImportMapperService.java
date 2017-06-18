package com.thegame.server.data.loader.services;

import com.thegame.server.data.loader.beans.AreaImport;
import com.thegame.server.data.loader.beans.CharacterStatImport;
import com.thegame.server.data.loader.beans.ItemImport;
import com.thegame.server.data.loader.beans.ItemInstanceImport;
import com.thegame.server.data.loader.beans.NonPlayerCharacterImport;
import com.thegame.server.data.loader.beans.RaceImport;
import com.thegame.server.data.loader.beans.RaceStatImport;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaItem;
import com.thegame.server.persistence.entities.CharacterStat;
import com.thegame.server.persistence.entities.Item;
import com.thegame.server.persistence.entities.NonPlayerCharacter;
import com.thegame.server.persistence.entities.Race;
import com.thegame.server.persistence.entities.RaceStat;
import java.util.List;
import java.util.stream.Collectors;
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

	//CONVERTERS
	public default List<AreaItem> convertItem(final AreaImport _area){
		return _area.getItems()
						.stream()
							.map(itemInstanceImport -> toEntity(itemInstanceImport,_area.getId()))
							.collect(Collectors.toList());
	}
	
	//MAPPERS
	public Race toEntity(final RaceImport _import);
	public CharacterStat toEntity(final CharacterStatImport _import);
	public RaceStat toEntity(final RaceStatImport _import);
	@Mappings({
		@Mapping(target="race",ignore=true),
		@Mapping(target="race.id",source="race"),
		@Mapping(target="area.id",source="area"),
		@Mapping(target="gender",source="gender.code"),
	})
	public NonPlayerCharacter toEntity(final NonPlayerCharacterImport _import);

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
		@Mapping(target="enemies",ignore=true),
		@Mapping(target="items",expression="java(convertItem(_import))"),
	})
	public Area toEntity(final AreaImport _import);
}
