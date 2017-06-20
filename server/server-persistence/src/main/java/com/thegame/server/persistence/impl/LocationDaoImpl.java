package com.thegame.server.persistence.impl;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import com.thegame.server.persistence.dtos.LocationArea;
import com.thegame.server.persistence.dtos.LocationItem;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

/**
 * @author afarre
 */
public class LocationDaoImpl implements LocationDao{
	
	private static final LogStream logger=LogStream.getLogger(LocationDaoImpl.class);

	@Override
	public void saveArea(final Area _area) {

		transactional(entityManager -> {
				Optional.ofNullable(entityManager.find(Area.class, _area.getId()))
					.ifPresent(area -> {throw new PersistenceException(PersistenceExceptionType.AREA_CREATION_ALREADY_EXIST,area.getId());});
				entityManager.persist(_area);
				});
	}
	
	@Override
	public void saveAreaExit(final AreaExit _areaExit) {
		
		transactional(entityManager -> {
				Optional.ofNullable(entityManager.find(AreaExit.class, _areaExit.getId()))
					.ifPresent(areaExit -> {throw new PersistenceException(PersistenceExceptionType.AREAEXIT_CREATION_ALREADY_EXIST,areaExit.getId());});
				entityManager.persist(_areaExit);
				});
	}

	protected BiFunction<EntityManager,String,List<String>> enemiesRetrieval(){

		return (entityManager,areaId) -> entityManager
			.createQuery("select selected.id from NonPlayerCharacter as selected where selected.area.id=:areaId",String.class)
			.setParameter("areaId", areaId)
			.getResultList()
			.stream()
			.peek(nonPlayerId -> logger.trace("location-dao::load::areas::processing::area::{}::non-player-id::{}:.found",areaId,nonPlayerId))
			.collect(Collectors.toList());
	}
	
	@Override
	public List<LocationArea> loadAreas(){

		List<LocationArea> reply;
		
		reply=transactional(
			entityManager -> Optional.ofNullable(
				entityManager.createQuery("select selected from Area as selected",Area.class)
					.getResultList())
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
								.enemies(this.enemiesRetrieval().apply(entityManager, area.getId()))
								.build())
						.collect(Collectors.toList())
				,List.class)
			.orElse(Collections.emptyList());
		
		return reply;
	}
}
