package com.thegame.server.common.persistence;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author afarre
 */
public interface PersistenceDao {

	public PersistenceSessionFactory getSessionFactory();
	

	public default void transactional(final Consumer<PersistenceSession> _consumer){
		transactional(Object.class,persistenceSession -> {_consumer.accept(persistenceSession);return null;});
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
}
