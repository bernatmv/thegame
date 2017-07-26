package com.thegame.server.common.persistence.internal.transactions;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceTransaction;
import java.text.MessageFormat;
import javax.persistence.EntityTransaction;

/**
 * @author afarre
 */
public abstract class PersistenceTransactionDelegateImpl implements PersistenceTransaction {

	private final static LogStream logger=LogStream.getLogger(PersistenceTransactionDelegateImpl.class);

	protected final EntityTransaction transaction;
	
	public PersistenceTransactionDelegateImpl(final EntityTransaction _underlayingEntityTransaction){
		this.transaction=_underlayingEntityTransaction;
		logger.trace("persistence-transaction-delegate::create::persistence-transaction::{}",this.transaction);
	}

	
	@Override
	public void begin() {
		logger.trace("persistence-transaction-delegate::start::persistence-transaction::{}",this.transaction);
		this.transaction.begin();
	}
	@Override
	public void commit() {
		logger.trace("persistence-transaction-delegate::commit::persistence-transaction::{}",this.transaction);
		this.transaction.commit();
	}
	@Override
	public void rollback() {
		logger.trace("persistence-transaction-delegate::rollback::persistence-transaction::{}",this.transaction);
		this.transaction.rollback();
	}
	@Override
	public void setRollbackOnly() {
		logger.trace("persistence-transaction-delegate::set-rollback::persistence-transaction::{}",this.transaction);
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
		logger.trace("persistence-transaction-delegate::close::persistence-transaction::{}",this.transaction);
	}

	@Override
	public String toString() {
		return MessageFormat.format("PersistenceTransactionDelegateImpl[transaction={0}]",this.transaction);
	}
}
