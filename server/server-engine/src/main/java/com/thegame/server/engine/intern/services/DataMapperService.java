package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.intern.data.ItemData;
import com.thegame.server.engine.intern.data.NonPlayerData;
import com.thegame.server.engine.intern.data.RaceData;
import com.thegame.server.engine.messages.common.Gender;
import com.thegame.server.persistence.entities.NonPlayerCharacter;
import com.thegame.server.persistence.entities.Race;
import com.thegame.server.persistence.dtos.LocationArea;
import com.thegame.server.persistence.dtos.LocationItem;
import java.util.Optional;
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

	
	public default Gender convertGender(final char _entity){
		return Optional.ofNullable(_entity)
							.map(entity -> Gender.valueOf(entity))
							.orElse(Gender.none);
	}
	
	
	public RaceData toData(final Race _entity);
	
	@Mappings({
		@Mapping(target="gender",expression="java(convertGender(_entity.getGender()))"),
	})
	public ItemData toData(final LocationItem _entity);
	@Mappings({
		@Mapping(target="players",expression="java(new java.util.concurrent.CopyOnWriteArrayList())"),
		@Mapping(target="listeners",expression="java(new java.util.concurrent.CopyOnWriteArrayList())"),
	})
	public AreaData toData(final LocationArea _entity);
	
	@Mappings({
		@Mapping(target="gender",expression="java(convertGender(_entity.getGender()))"),
		@Mapping(target="race",expression="java(_entity.getRace().getId())"),
	})
	public NonPlayerData toData(final NonPlayerCharacter _entity);
}
