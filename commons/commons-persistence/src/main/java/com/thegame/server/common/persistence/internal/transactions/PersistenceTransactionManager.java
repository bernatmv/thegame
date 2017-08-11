package com.thegame.server.common.persistence.internal.transactions;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceTransaction;
import com.thegame.server.common.persistence.exceptions.PersistenceError;
import java.util.Optional;
import java.util.function.Supplier;
import javax.persistence.EntityTransaction;

/**
 * @author afarre
 */
public class PersistenceTransactionManager{

	private final static LogStream logger=LogStream.getLogger(PersistenceTransactionManager.class,"persistence-transaction-manager::");
	
	private PersistenceTransaction underlayingTransaction;
	private final Supplier<EntityTransaction> transactionSupplier;
	
	public PersistenceTransactionManager(final Supplier<EntityTransaction> _supplier){
		this.underlayingTransaction=null;
		this.transactionSupplier=_supplier;
	}

	private Optional<PersistenceTransaction> get(){
		return Optional.ofNullable(this.underlayingTransaction);
	}
	private PersistenceTransaction init(){
		this.underlayingTransaction=Optional.ofNullable(this.transactionSupplier.get())
											.map(persistenceTransaction -> new PersistenceTransactionMasterImpl(persistenceTransaction,this))
											.orElseThrow(PersistenceError.TRANSACTION_NOT_SUPPLIED);
		logger.trace("register::transaction::{}",this.underlayingTransaction);
		return this.underlayingTransaction;
	}
	

	public PersistenceTransaction getTransaction() {
		return get()
			.map(persistenceTransaction -> new PersistenceTransactionSlaveImpl(persistenceTransaction))
			.map(persistenceTransaction -> (PersistenceTransaction)persistenceTransaction)
			.orElseGet(this::init);
	}
	public void removeTransaction() {
		logger.trace("unregister::transaction::{}",this.underlayingTransaction);
		this.underlayingTransaction=null;
	}
	public boolean hasTransaction(){
		return this.underlayingTransaction!=null;
	}
	public PersistenceTransaction currentTransaction() {
		return this.underlayingTransaction;
	}
}
