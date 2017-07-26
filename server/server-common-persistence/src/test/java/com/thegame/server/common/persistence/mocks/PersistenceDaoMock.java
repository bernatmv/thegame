package com.thegame.server.common.persistence.mocks;

import com.thegame.server.common.persistence.PersistenceDao;
import com.thegame.server.common.persistence.PersistenceSessionFactory;

/**
 * @author afarre
 */
public class PersistenceDaoMock implements PersistenceDao {

	private final PersistenceSessionFactory persistenceSessionFactory;
	
	public PersistenceDaoMock(final PersistenceSessionFactory _sessionFactory){
		this.persistenceSessionFactory=_sessionFactory;
	}
	
	@Override
	public PersistenceSessionFactory getSessionFactory() {
		return this.persistenceSessionFactory;
	}
}
