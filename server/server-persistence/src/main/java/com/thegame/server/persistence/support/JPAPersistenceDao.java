package com.thegame.server.persistence.support;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.persistence.PersistenceServiceFactory;

/**
 * @author e103880
 */
public interface JPAPersistenceDao {

	public default JPASessionManager createEntityManager(){
		return PersistenceServiceFactory.forImplementation(this)
				.map(persistenceServiceFactory -> persistenceServiceFactory.getEntityManagerFactory())
				.map(entityManagerFactory -> entityManagerFactory.createEntityManager())
				.map(entityManager -> new JPASessionManager(entityManager))
				.orElse(null);
	}
	public default void releaseEntityManager(final JPASessionManager _entityManager){
		try{
			if(_entityManager!=null){
				_entityManager.close();
			}
		}catch(Throwable e){
			LogStream.getLogger(JPAPersistenceDao.class).warning("close-entity-manager::failed",e);
		}
	}
}
