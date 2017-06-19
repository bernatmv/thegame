package com.thegame.server.persistence.impl;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import com.thegame.server.persistence.dtos.LocationArea;
import com.thegame.server.persistence.dtos.LocationItem;
import com.thegame.server.persistence.support.JPASessionManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.TypedQuery;

/**
 * @author afarre
 */
public class LocationDaoImpl implements LocationDao{
	
	private static final LogStream logger=LogStream.getLogger(LocationDaoImpl.class);

	@Override
	public void saveArea(final Area _area) {
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				logger.trace("location-dao::save::area::{}::begin",_area.getId());
				entityManager.getTransaction().begin();
				Optional.ofNullable(entityManager.find(Area.class, _area.getId()))
					.ifPresent(area -> {throw new PersistenceException(PersistenceExceptionType.AREA_CREATION_ALREADY_EXIST,area.getId());});
				entityManager.persist(_area);
				entityManager.getTransaction().commit();
				logger.debug("location-dao::save::area::{}::end",_area.getId());
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.AREA_CREATION_FAIL,_area);
			}
		}
	}
	
	@Override
	public void saveAreaExit(final AreaExit _areaExit) {
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				logger.trace("location-dao::save::area-exit::{}::begin",_areaExit.getId());
				entityManager.getTransaction().begin();
				Optional.ofNullable(entityManager.find(AreaExit.class, _areaExit.getId()))
					.ifPresent(area -> {throw new PersistenceException(PersistenceExceptionType.AREA_CREATION_ALREADY_EXIST,area.getId());});
				entityManager.persist(_areaExit);
				entityManager.getTransaction().commit();
				logger.debug("location-dao::save::area-exit::{}::end",_areaExit.getId());
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.AREA_CREATION_FAIL,_areaExit);
			}
		}
	}

	protected Stream<String> loadNonPlayerIds(final JPASessionManager _entityManager,final String _areaId){

		Stream<String> reply;

		logger.trace("location-dao::load::non-player-ids::{}::begin",_areaId);
		final TypedQuery<String> query=_entityManager
								.createQuery("select selected.id from NonPlayerCharacter as selected where selected.area.id=:areaId",String.class)
								.setParameter("areaId", _areaId);	
		reply=Optional.ofNullable(query.getResultList())
				.orElse(Collections.emptyList())
				.stream();
		logger.debug("location-dao::load::non-player-ids::{}::end",_areaId);
		
		return reply;
	}
	
	@Override
	public List<LocationArea> loadAreas(){

		List<LocationArea> reply;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				logger.trace("location-dao::load::areas::begin");
				entityManager.getTransaction().begin();
				final TypedQuery<Area> query=entityManager.createQuery("select selected from Area as selected",Area.class);
				reply=Optional.ofNullable(query.getResultList())
						.orElse(Collections.emptyList())
						//Hibernate can not recover eagerly more than one bag, we perform the recovery manuallly
						.stream()
						.peek(area -> logger.trace("location-dao::load::areas::processing::area::{}",area.getId()))
						.map(area -> LocationArea.builder()
										.id(area.getId())
										.title(area.getTitle())
										.description(area.getDescription())
										.items(area.getItems()
														.stream()
														.peek(item -> logger.trace("location-dao::load::areas::processing::area::{}::item::{}:.found",area.getId(),item.getId().getName()))
														.map(areaItem -> areaItem.getId())
														.map(areaItemId -> LocationItem.builder()
																				.id(areaItemId.getItem().getId())
																				.name(areaItemId.getName())
																				.gender(areaItemId.getItem().getGender())
																				.alive(areaItemId.getItem().isAlive())
																				.description(areaItemId.getItem().getDescription())
																				.plural(areaItemId.getItem().getPlural())
																				.singular(areaItemId.getItem().getSingular())
																				.chatter(areaItemId.getItem().getChatter())
																				.build()
															)
														.collect(Collectors.toList()))
										.exits(area.getExits()
														.stream()
														.peek(areaExit -> logger.trace("location-dao::load::areas::processing::area::{}::exit::{}:.found",area.getId(),areaExit.getId().getName()))
														.collect(Collectors.toMap(
																	areaExit -> areaExit.getId().getName(),
																	areaExit -> areaExit.getToArea().getId())))
										.enemies(loadNonPlayerIds(entityManager, area.getId())
													.peek(nonPlayerId -> logger.trace("location-dao::load::areas::processing::area::{}::non-player-id::{}:.found",area.getId(),nonPlayerId))
													.collect(Collectors.toList()))
										.build())
						.collect(Collectors.toList());
				entityManager.getTransaction().commit();
				logger.debug("location-dao::load::areass::end");
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.AREA_LOAD_FAIL);
			}
		}
		
		return reply;
	}
}
