package com.thegame.server.common.persistence.mocks;

import com.thegame.server.common.persistence.PersistenceSessionFactory;
import com.thegame.server.common.persistence.PersistenceSessionManager;

/**
 * @author afarre
 */
public class PersistenceSessionFactoryMock implements PersistenceSessionFactory{

	public final PersistenceSessionManager sessionManager;
	
	
	public PersistenceSessionFactoryMock(final PersistenceSessionManager _sessionManager){
		this.sessionManager=_sessionManager;
	}
	
	@Override
	public PersistenceSessionManager getSessionManager() {
		return sessionManager;
	}
	
}
