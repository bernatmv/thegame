package com.thegame.server.account.persistence;

import com.thegame.server.common.persistence.PersistenceSessionFactory;
import com.thegame.server.common.persistence.PersistenceSessionManager;
import java.util.Collections;
import java.util.Map;

/**
 * @author afarre
 */
public enum PersistenceUnit implements PersistenceSessionFactory {

	THEGAMEACCOUNTS("thegame-accounts"),
	;

	private final PersistenceSessionManager sessionManager; 

	PersistenceUnit(final String _persistenceUnit){
		this(_persistenceUnit,Collections.emptyMap());
	}
	PersistenceUnit(final String _persistenceUnit,final Map<String,String> _sessionProperties){
		this.sessionManager=new PersistenceSessionManager(_persistenceUnit,_sessionProperties);
	}
	
	@Override
	public PersistenceSessionManager getSessionManager(){
		return this.sessionManager;
	}
}
