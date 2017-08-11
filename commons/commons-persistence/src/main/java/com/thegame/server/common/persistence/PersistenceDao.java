package com.thegame.server.common.persistence;

import com.thegame.server.common.persistence.exceptions.PersistenceError;
import com.thegame.server.common.persistence.exceptions.PersistenceException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author afarre
 */
public interface PersistenceDao {

	public PersistenceSessionFactory getSessionFactory();
	
	public default void transactional(final Consumer<PersistenceSession> _consumer){
		transactional(Object.class,persistenceSession -> {
											_consumer.accept(persistenceSession);
											return null;
									});
	}
	public default <T> Optional<T> transactional(final Class<T> _class,final Function<PersistenceSession,T> _queryExecutor){
		
		Optional<T> reply;
		
		try(PersistenceSession session=getSessionFactory().getSession()){
			try(PersistenceTransaction transaction=session.currentTransaction()){
				transaction.begin();
				reply=Optional.ofNullable(_queryExecutor.apply(session));
				transaction.commit();
			}
		}
		
		return reply;
	}	


	public default Object getId(final Object _entity){
		return Optional.ofNullable(getSessionFactory())
						.map(sessionFactory -> sessionFactory.getPersistenceUnitUtil())
						.filter(sessionFactory -> _entity!=null)
						.map(persistenceUtil -> persistenceUtil.getIdentifier(_entity))
						.get();
	}
	
	@SuppressWarnings("UseSpecificCatch")
	public default <T> void save(final Class<T> _entityClass,final T _entity) {

		final String entityType=Optional.ofNullable(_entityClass)
									.map(entity -> entity.getSimpleName())
									.orElseThrow(() -> PersistenceError.ENTITY_CREATION_FAIL_MANDATORY_PARAMETER.get("Unknown","_entityClass"));
		final Object entityId=Optional.ofNullable(_entity)
									.map(entity -> getId(entity))
									.orElseThrow(() -> PersistenceError.ENTITY_CREATION_FAIL_MANDATORY_PARAMETER.get(entityType,"_entity"));

		try{
			transactional(sessionManager -> {
				if(sessionManager.find(_entityClass, entityId)!=null){
					throw PersistenceError.ENTITY_CREATION_FAIL_DUPLICATE_ID.get(entityType,entityId);
				}
				sessionManager.persist(_entity);
			});
		}catch(PersistenceException e){
			throw e;
		}catch(Throwable e){
			throw PersistenceError.ENTITY_CREATION_FAIL.get(e,entityType,_entity);
		}
	}

	@SuppressWarnings("UseSpecificCatch")
	public default <T> boolean contains(final Class<T> _entityClass,final Object _entityId) {

		Boolean reply;
		final String entityType=Optional.ofNullable(_entityClass)
									.map(entity -> entity.getSimpleName())
									.orElseThrow(() -> PersistenceError.ENTITY_CHECK_FAIL_MANDATORY_PARAMETER.get("Unknown","_entityClass"));
		final Object entityId=Optional.ofNullable(_entityId)
									.orElseThrow(() -> PersistenceError.ENTITY_CHECK_FAIL_MANDATORY_PARAMETER.get(entityType,"_entityId"));

		try{
			reply=transactional(Boolean.class,sessionManager -> 
					sessionManager.find(_entityClass, entityId)!=null)
					.orElse(false);
		}catch(PersistenceException e){
			throw e;
		}catch(Throwable e){
			throw PersistenceError.ENTITY_CHECK_FAIL.get(e,entityType,entityId);
		}
		
		return reply;
	}

	@SuppressWarnings("UseSpecificCatch")
	public default <T> T get(final Class<T> _entityClass,final Object _entityId) {

		T reply;
		final String entityType=Optional.ofNullable(_entityClass)
									.map(entity -> entity.getSimpleName())
									.orElseThrow(() -> PersistenceError.ENTITY_RETRIEVAL_FAIL_MANDATORY_PARAMETER.get("Unknown","_entityClass"));
		final Object entityId=Optional.ofNullable(_entityId)
									.orElseThrow(() -> PersistenceError.ENTITY_RETRIEVAL_FAIL_MANDATORY_PARAMETER.get(entityType,"_entityId"));

		try{
			reply=transactional(_entityClass,sessionManager -> 
					Optional.ofNullable(sessionManager.find(_entityClass, entityId))
						.orElseThrow(() -> PersistenceError.ENTITY_RETRIEVAL_FAIL_ID_NOT_EXIST.get(entityType,entityId)))
				.orElseThrow(() -> PersistenceError.ENTITY_RETRIEVAL_FAIL_ID_NOT_EXIST.get(entityType,entityId));						
		}catch(PersistenceException e){
			throw e;
		}catch(Throwable e){
			throw PersistenceError.ENTITY_RETRIEVAL_FAIL.get(e,entityType,entityId);
		}
		
		return reply;
	}

	@SuppressWarnings("UseSpecificCatch")
	public default <T> T update(final Class<T> _entityClass,final T _entity) {

		T reply;
		final String entityType=Optional.ofNullable(_entityClass)
									.map(entity -> entity.getSimpleName())
									.orElseThrow(() -> PersistenceError.ENTITY_UPDATE_FAIL_MANDATORY_PARAMETER.get("Unknown","_entityClass"));
		final Object entityId=Optional.ofNullable(_entity)
									.map(entity -> getId(_entity))
									.orElseThrow(() -> PersistenceError.ENTITY_UPDATE_FAIL_MANDATORY_PARAMETER.get(entityType,"_entity"));

		try{
			reply=transactional(_entityClass,sessionManager -> {
						if(sessionManager.find(_entityClass, entityId)==null){
							throw PersistenceError.ENTITY_UPDATE_FAIL_ID_NOT_EXIST.get(entityType,entityId);
						}
						return sessionManager.merge(_entity);
					})
					.orElseThrow(() -> PersistenceError.ENTITY_UPDATE_FAIL.get(entityType,entityId));
		}catch(PersistenceException e){
			throw e;
		}catch(Throwable e){
			throw PersistenceError.ENTITY_UPDATE_FAIL.get(e,entityType,_entity);
		}
		
		return reply;
	}

	@SuppressWarnings("UseSpecificCatch")
	public default <T> void delete(final Class<T> _entityClass,final Object _entityId) {

		final String entityType=Optional.ofNullable(_entityClass)
									.map(entity -> entity.getSimpleName())
									.orElseThrow(() -> PersistenceError.ENTITY_DELETION_FAIL_MANDATORY_PARAMETER.get("Unknown","_entityClass"));
		final Object entityId=Optional.ofNullable(_entityId)
									.orElseThrow(() -> PersistenceError.ENTITY_DELETION_FAIL_MANDATORY_PARAMETER.get(entityType,"_entityId"));
		try{
			transactional(sessionManager -> {
				final T entity=Optional.ofNullable(sessionManager.find(_entityClass, entityId))
						.orElseThrow(() -> PersistenceError.ENTITY_DELETION_FAIL_ID_NOT_EXIST.get(entityType,entityId));
				sessionManager.remove(entity);
			});
		}catch(PersistenceException e){
			throw e;
		}catch(Throwable e){
			throw PersistenceError.ENTITY_DELETION_FAIL.get(e,entityType,entityId);
		}
	}
}
