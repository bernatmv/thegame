package com.thegame.server.persistence.impl;

import com.thegame.server.common.persistence.PersistenceSessionFactory;
import com.thegame.server.persistence.entities.Race;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import java.util.Optional;
import com.thegame.server.persistence.CharacterDao;
import com.thegame.server.persistence.PersistenceUnit;
import com.thegame.server.persistence.entities.NonPlayerCharacter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author afarre
 */
public class CharacterDaoImpl implements CharacterDao{

	@Override
	public PersistenceSessionFactory getSessionFactory() {
		return PersistenceUnit.THEGAME;
	}

	@Override
	public void saveRace(final Race _race) {
		
		transactional(sessionManager -> {
			Optional.ofNullable(sessionManager.find(Race.class, _race.getId()))
				.ifPresent(race -> {throw new PersistenceException(PersistenceExceptionType.RACE_CREATION_ALREADY_EXIST,race.getId());});
			sessionManager.persist(_race);
		});
	}
	
	@Override
	public Race getRace(final String _raceId){

		Race reply;
		
		reply=transactional(Race.class,sessionManager -> sessionManager.find(Race.class, _raceId))
			.orElseThrow(() -> new PersistenceException(PersistenceExceptionType.RACE_NOT_EXIST,_raceId));
		
		return reply;
	}

	@Override
	public List<Race> loadRaces(){

		List<Race> reply;
		
		reply=transactional(List.class,
				sessionManager -> Optional.ofNullable(
									sessionManager.createQuery("select selected from Race as selected",Race.class)
											.getResultList())
								.orElse(Collections.emptyList())
								//Hibernate can not recover eagerly more than one bag, we perform the recovery manuallly
								.stream()
								.collect(Collectors.toList()))
			.orElse(Collections.emptyList());
		
		return reply;
	}

	@Override
	public void saveCharacter(final NonPlayerCharacter _character){

		transactional(sessionManager -> {
			Optional.ofNullable(sessionManager.find(NonPlayerCharacter.class, _character.getId()))
				.ifPresent(nonPlayerCharacter -> {throw new PersistenceException(PersistenceExceptionType.NPCHARACTER_CREATION_ALREADY_EXIST,nonPlayerCharacter.getId());});
			sessionManager.persist(_character);
		});
	}

	@Override
	public void mergeCharacter(final NonPlayerCharacter _character){

		transactional(sessionManager -> sessionManager.merge(_character));
	}

	@Override
	public NonPlayerCharacter getCharacter(final String _characterId){

		NonPlayerCharacter reply;
		
		reply=transactional(NonPlayerCharacter.class,sessionManager -> sessionManager.find(NonPlayerCharacter.class, _characterId))
					.orElseThrow(() -> new PersistenceException(PersistenceExceptionType.NPCHARACTER_NOT_EXIST,_characterId));
		
		return reply;
	}
	
	@Override
	public List<NonPlayerCharacter> loadCharacters(){

		List<NonPlayerCharacter> reply;
		
		reply=transactional(List.class,sessionManager -> 
			Optional.ofNullable(
				sessionManager.createQuery("select selected from NonPlayerCharacter as selected",NonPlayerCharacter.class)
					.getResultList())
				.orElse(Collections.emptyList())
				.stream()
				//When session is closed all lazy relations raises an exception when accessed, also the id
				.peek(character -> character.setRace(Race.builder()
														.id(character.getRace().getId())
														.build()))
					.collect(Collectors.toList()))
			.orElse(Collections.emptyList());
		
		return reply;
	}
}
