package com.thegame.server.common.persistence.internal.transactions;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceTransaction;
import javax.persistence.EntityTransaction;

/**
 * @author afarre
 */
public abstract class PersistenceTransactionDelegateImpl implements PersistenceTransaction {

	private final static LogStream logger=LogStream.getLogger(PersistenceTransactionDelegateImpl.class,"persistence-transaction(delegated)");
	
	protected final EntityTransaction transaction;
	
	@SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
	public PersistenceTransactionDelegateImpl(final EntityTransaction _underlayingEntityTransaction){
		this.transaction=_underlayingEntityTransaction;
		logger.trace("[{}]::create::{}",getKind(),this);
	}

	
	protected abstract String getKind();
	
	@Override
	public void begin() {
		logger.debug("[{}]::BEGIN::{}",getKind(),this);
		this.transaction.begin();
	}
	@Override
	public void commit() {
		logger.debug("[{}]::COMMIT::{}",getKind(),this);
		if(isActive()){
			this.transaction.commit();
		}
	}
	@Override
	public void rollback() {
		logger.debug("[{}]::ROLLBACK::{}",getKind(),this);
		if(isActive()){
			this.transaction.rollback();
		}
	}
	@Override
	public void setRollbackOnly() {
		logger.debug("[{}]::ROLLBACK-ONLY::{}",getKind(),this);
		transaction.setRollbackOnly();
	}
	@Override
	public boolean getRollbackOnly() {
		return transaction.getRollbackOnly();
	}
	@Override
	public boolean isActive() {
		return transaction.isActive();
	}	
	@Override
	public void close() {
		logger.trace("[{}]::close::{}",this.transaction);
	}
}
