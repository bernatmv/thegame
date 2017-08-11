package com.thegame.server.common.persistence.internal.transactions;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceTransaction;
import java.text.MessageFormat;

/**
 * @author afarre
 */
public class PersistenceTransactionSlaveImpl extends PersistenceTransactionDelegateImpl {

	private final static String KIND="SLAVE";
	private final static LogStream logger=LogStream.getLogger(PersistenceTransactionMasterImpl.class,"persistence-transaction["+KIND+"]::");

	private boolean started;
	private boolean commited;
	
	public PersistenceTransactionSlaveImpl(final PersistenceTransaction _underlayingEntityTransaction){
		super(_underlayingEntityTransaction);
		this.started=false;
		this.commited=false;
	}

	@Override
	protected String getKind(){
		return KIND;
	}
	
	@Override
	public void begin() {
		logger.debug("BEGIN::{}",this);
		this.started=true;
	}
	@Override
	public void commit() {
		logger.debug("COMMIT::{}",this);
		this.commited=true;
	}
	@Override
	public void rollback() {
		logger.debug("ROLLBACK::{}",this);
		super.setRollbackOnly();
	}

	@Override
	public void close() {
		logger.trace("close::{}",this);
		if((this.started)&&(!this.commited)){
			super.setRollbackOnly();
		}
	}

	@Override
	public String toString() {
		return MessageFormat.format("PersistenceTransactionSlaveImpl[transaction={0}]",this.transaction);
	}	
}
