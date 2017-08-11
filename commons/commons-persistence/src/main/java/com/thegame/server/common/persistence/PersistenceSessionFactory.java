package com.thegame.server.common.persistence;

import javax.persistence.PersistenceUnitUtil;

/**
 * @author afarre
 */
public interface PersistenceSessionFactory {
	
	public PersistenceSessionManager getSessionManager();
	
	public default PersistenceSession createSession(){
		return getSessionManager().createSession();
	}	
	public default PersistenceSession getSession(){
		return getSessionManager().getSession();
	}	
	public default PersistenceUnitUtil getPersistenceUnitUtil(){
		return getSessionManager().getPersistenceUnitUtil();
	}	

	public default void init(){
		getSessionManager().init();
	}
	public default void close(){
		getSessionManager().close();
	}
}
