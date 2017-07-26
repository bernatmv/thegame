package com.thegame.server.common.persistence.internal.transactions;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceTransaction;
import java.text.MessageFormat;

/**
 * @author afarre
 */
public class PersistenceTransactionSlaveImpl extends PersistenceTransactionDelegateImpl {

	private final static LogStream logger=LogStream.getLogger(PersistenceTransactionSlaveImpl.class);

	private boolean started;
	private boolean commited;
	
	public PersistenceTransactionSlaveImpl(final PersistenceTransaction _underlayingEntityTransaction){
		super(_underlayingEntityTransaction);
		logger.trace("persistence-transaction-slave::create::persistence-transaction::{}",this.transaction);
		this.started=false;
		this.commited=false;
	}

	
	@Override
	public void begin() {
		logger.debug("persistence-transaction-slave::start::persistence-transaction::{}",this.transaction);
		this.started=true;
	}
	@Override
	public void commit() {
		logger.debug("persistence-transaction-slave::commit::persistence-transaction::{}",this.transaction);
		this.commited=true;
	}
	@Override
	public void rollback() {
		logger.debug("persistence-transaction-slave::rollback::persistence-transaction::{}",this.transaction);
		super.setRollbackOnly();
	}

	@Override
	public void setRollbackOnly() {
		logger.debug("persistence-transaction-slave::set-rollback::persistence-transaction::{}",this.transaction);
		super.setRollbackOnly();
	}
	@Override
	public void close() {
		logger.trace("persistence-transaction-slave::close::persistence-transaction::{}",this.transaction);
		if((this.started)&&(!this.commited)){
			super.setRollbackOnly();
		}
	}

	@Override
	public String toString() {
		return MessageFormat.format("PersistenceTransactionSlaveImpl[transaction={0}]",transaction);
	}	
}
