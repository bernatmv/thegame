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
	
	private final static String KIND="MASTER";
	private final static LogStream logger=LogStream.getLogger(PersistenceSessionMasterImpl.class,"persistence-session["+KIND+"]::");

	protected final PersistenceSessionManager sessionManager;
	protected final PersistenceTransactionManager transactionManager; 

	public PersistenceSessionMasterImpl(final EntityManager _entityManager,final PersistenceSessionManager _sessionManager) {
		super(_entityManager);
		this.sessionManager=_sessionManager;
		this.transactionManager=new PersistenceTransactionManager(() -> this.session.getTransaction());
	}

	
	@Override
	protected String getKind(){
		return KIND;
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
		logger.trace("close::{}",this);
		try{
			this.session.close();
		}catch(Throwable e){
			logger.warning("close::{}::close::failed",this,e);
		}
		try{
			this.sessionManager.removeSession();
		}catch(Throwable e){
			logger.warning("close::{}::remove::failed",this,e);
		}
	}
	

	@Override
	public String toString() {
		return MessageFormat.format("PersistenceSessionMasterImpl[session={0}]",this.session);
	}	
}
