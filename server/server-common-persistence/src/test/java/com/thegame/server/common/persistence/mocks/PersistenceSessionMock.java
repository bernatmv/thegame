package com.thegame.server.common.persistence.mocks;

import com.thegame.server.common.persistence.PersistenceSessionFactory;
import com.thegame.server.common.persistence.PersistenceSessionManager;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author afarre
 */
public enum PersistenceSessionMock implements PersistenceSessionFactory{

	WITH_PERSISTENCE_UNIT("with-persistence-unit"),
	;

	private final PersistenceSessionManager sessionManager; 

	PersistenceSessionMock(final String _persistenceUnit){
		this(_persistenceUnit,Collections.emptyMap());
	}
	PersistenceSessionMock(final String _persistenceUnit,final Map<String,String> _sessionProperties){
		this.sessionManager=new PersistenceSessionManager(_persistenceUnit,_sessionProperties);
	}
	
	@Override
	public PersistenceSessionManager getSessionManager(){
		return this.sessionManager;
	}
}
