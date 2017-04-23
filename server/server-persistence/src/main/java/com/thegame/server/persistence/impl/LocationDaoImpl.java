package com.thegame.server.persistence.impl;

import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import com.thegame.server.persistence.support.JPASessionManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;

/**
 * @author afarre
 */
public class LocationDaoImpl implements LocationDao{

	@Override
	public void saveArea(final Area _area) {
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				Optional.ofNullable(entityManager.find(Area.class, _area.getId()))
					.ifPresent(area -> {throw new PersistenceException(PersistenceExceptionType.AREA_CREATION_ALREADY_EXIST,area.getId());});
				entityManager.persist(_area);
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(PersistenceExceptionType.AREA_CREATION_FAIL,_area);
			}
		}
	}
	
	@Override
	public void saveAreaExit(final AreaExit _areaExit) {
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				Optional.ofNullable(entityManager.find(AreaExit.class, _areaExit.getId()))
					.ifPresent(area -> {throw new PersistenceException(PersistenceExceptionType.AREA_CREATION_ALREADY_EXIST,area.getId());});
				entityManager.persist(_areaExit);
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(PersistenceExceptionType.AREA_CREATION_FAIL,_areaExit);
			}
		}
	}

	@Override
	public List<Area> loadAreas(){

		List<Area> reply;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				final TypedQuery<Area> query=entityManager.createQuery("select selected from Area as selected",Area.class);
				reply=Optional.ofNullable(query.getResultList())
						.orElse(Collections.emptyList());
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(PersistenceExceptionType.AREA_LOAD_FAIL);
			}
		}
		
		return reply;
	}
}
