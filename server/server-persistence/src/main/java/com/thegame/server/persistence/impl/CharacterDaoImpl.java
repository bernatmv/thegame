package com.thegame.server.persistence.impl;

import com.thegame.server.persistence.entities.Race;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import com.thegame.server.persistence.support.JPASessionManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import com.thegame.server.persistence.CharacterDao;
import com.thegame.server.persistence.entities.NonPlayerCharacter;

/**
 * @author afarre
 */
public class CharacterDaoImpl implements CharacterDao{

	@Override
	public void saveRace(final Race _race) {
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				Optional.ofNullable(entityManager.find(Race.class, _race.getId()))
					.ifPresent(area -> {throw new PersistenceException(PersistenceExceptionType.RACE_CREATION_ALREADY_EXIST,area.getId());});
				entityManager.persist(_race);
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.RACE_CREATION_FAIL,_race);
			}
		}
	}
	
	@Override
	public Race getRace(final String _raceId){

		Race reply;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				reply=Optional.ofNullable(entityManager.find(Race.class, _raceId))
					.orElseThrow(() -> {throw new PersistenceException(PersistenceExceptionType.RACE_NOT_EXIST,_raceId);});
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.RACE_RETRIEVAL_FAIL,_raceId);
			}
		}
		
		return reply;
	}

	@Override
	public List<Race> loadRaces(){

		List<Race> reply;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				final TypedQuery<Race> query=entityManager.createQuery("select selected from Race as selected",Race.class);
				reply=Optional.ofNullable(query.getResultList())
						.orElse(Collections.emptyList())
						//Hibernate can not recover eagerly more than one bag, we perform the recovery manuallly
						.stream()
						.collect(Collectors.toList());
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.RACE_LOAD_FAIL);
			}
		}
		
		return reply;
	}

	@Override
	public void saveCharacter(final NonPlayerCharacter _character){

		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				Optional.ofNullable(entityManager.find(NonPlayerCharacter.class, _character.getId()))
					.ifPresent(nonPlayerCharacter -> {throw new PersistenceException(PersistenceExceptionType.NPCHARACTER_CREATION_ALREADY_EXIST,nonPlayerCharacter.getId());});
				entityManager.persist(_character);
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.NPCHARACTER_CREATION_FAIL,_character);
			}
		}
	}
	
	@Override
	public NonPlayerCharacter getCharacter(final String _characterId){

		NonPlayerCharacter reply;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				reply=Optional.ofNullable(entityManager.find(NonPlayerCharacter.class, _characterId))
					.orElseThrow(() -> {throw new PersistenceException(PersistenceExceptionType.NPCHARACTER_NOT_EXIST,_characterId);});
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.NPCHARACTER_RETRIEVAL_FAIL,_characterId);
			}
		}
		
		return reply;
	}
	
	@Override
	public List<NonPlayerCharacter> loadCharacters(){

		List<NonPlayerCharacter> reply;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				final TypedQuery<NonPlayerCharacter> query=entityManager.createQuery("select selected from NonPlayerCharacter as selected",NonPlayerCharacter.class);
				reply=Optional.ofNullable(query.getResultList())
						.orElse(Collections.emptyList())
						//Hibernate can not recover eagerly more than one bag, we perform the recovery manuallly
						.stream()
						.collect(Collectors.toList());
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.NPCHARACTER_LOAD_FAIL);
			}
		}
		
		return reply;
	}
}
