package com.thegame.server.common.persistence.internal.session;

import com.thegame.server.common.persistence.PersistenceSessionManager;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceTransaction;
import com.thegame.server.common.persistence.internal.transactions.PersistenceTransactionManager;
import java.text.MessageFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author afarre
 */
public class PersistenceSessionMasterImpl extends PersistenceSessionDelegateImpl{
	
	private final static LogStream logger=LogStream.getLogger(PersistenceSessionMasterImpl.class);

	protected final PersistenceSessionManager sessionManager;
	protected final PersistenceTransactionManager transactionManager; 

	public PersistenceSessionMasterImpl(final EntityManager _entityManager,final PersistenceSessionManager _sessionManager) {
		super(_entityManager);
		this.sessionManager=_sessionManager;
		this.transactionManager=new PersistenceTransactionManager(() -> this.session.getTransaction());
		logger.trace("persistence-session-master::create::persistence-session::{}",this.session);
	}

	@Override
	public EntityTransaction getTransaction() {
		return currentTransaction();
	}
	@Override
	public PersistenceTransaction currentTransaction() {
		return this.transactionManager.getTransaction();
	}
	
	@Override
	public void close() {
		logger.trace("persistence-session-master::close::persistence-session::{}",this.session);
		try{
			this.session.close();
		}catch(Throwable e){
			logger.warning("persistence-session-master::close::persistence-session::{}::close::failed",this.session,e);
		}
		try{
			this.sessionManager.removeSession();
		}catch(Throwable e){
			logger.warning("persistence-session-master::close::persistence-session::{}::remove::failed",this.session,e);
		}
	}
	

	@Override
	public String toString() {
		return MessageFormat.format("PersistenceSessionMasterImpl[session={0}]",session);
	}	
}
