package com.thegame.server.persistence.support;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.persistence.PersistenceServiceFactory;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;

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
	public default void transactional(final Consumer<EntityManager> _consumer){
		transactional(entityManager -> {_consumer.accept(entityManager);return null;}, Object.class);
	}
	public default <T> Optional<T> transactional(final Function<EntityManager,T> _function,final Class<T> _class){
		
		Optional<T> reply=null;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				reply=Optional.ofNullable(_function.apply(entityManager));
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(e,PersistenceExceptionType.UNEXPECTED);
			}
		}
		
		return reply;
	}
}
