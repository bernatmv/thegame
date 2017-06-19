package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.data.RaceData;
import com.thegame.server.engine.intern.services.CharacterService;
import com.thegame.server.engine.intern.services.DataClonerService;
import com.thegame.server.engine.intern.services.DataMapperService;
import com.thegame.server.persistence.CharacterDao;
import com.thegame.server.persistence.PersistenceServiceFactory;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author afarre
 */
public class CharacterServiceImpl implements CharacterService{
	
	private CharacterDao characterDao;
	private DataMapperService dataMapperService;
	private DataClonerService dataClonerService;
	private Map<String,RaceData> races;
	
	
	public CharacterServiceImpl(){
		this(PersistenceServiceFactory.CHARACTERDAO.getInstance(CharacterDao.class),
			EngineServiceFactory.DATAMAPPER.getInstance(DataMapperService.class),
			EngineServiceFactory.DATACLONER.getInstance(DataClonerService.class));
	}
	public CharacterServiceImpl(final CharacterDao _characterDao,final DataMapperService _dataMapperService,final DataClonerService _dataClonerService){
		this.characterDao=_characterDao;
		this.dataMapperService=_dataMapperService;
		this.dataClonerService=_dataClonerService;
		this.races=this.characterDao.loadRaces().stream()
			.map(race -> dataMapperService.toData(race))
			.collect(Collectors.toMap(race -> race.getId(),race -> race));
	}
	
	
	protected Optional<RaceData> getOptionalRace(final String _id) {
		return Optional.of(Optional.ofNullable(_id)
					.map(raceId -> this.races.get(raceId))
					.orElseThrow(() -> new EngineException(EngineExceptionType.RACE_NOT_EXIST,_id)));

	}	

	@Override
	public RaceData getRace(final String _id) {
		return getOptionalRace(_id)
					.map(race -> this.dataClonerService.clone(race))
					.orElseThrow(() -> new EngineException(EngineExceptionType.RACE_NOT_EXIST,_id));
	}	
}
