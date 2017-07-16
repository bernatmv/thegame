package com.thegame.server.common.persistence.internal.transactions;

import com.thegame.server.common.logging.LogStream;
import java.text.MessageFormat;
import javax.persistence.EntityTransaction;

/**
 * @author afarre
 */
public class PersistenceTransactionMasterImpl extends PersistenceTransactionDelegateImpl {
	
	private final static LogStream logger=LogStream.getLogger(PersistenceTransactionMasterImpl.class);

	protected final PersistenceTransactionManager transactionManager;
	private boolean started;
	private boolean finalized;

	public PersistenceTransactionMasterImpl(final EntityTransaction _underlayingEntityTransaction,final PersistenceTransactionManager _transactionManager){
		super(_underlayingEntityTransaction);
		this.transactionManager=_transactionManager;
		logger.trace("persistence-transaction-master::create::persistence-transaction::{}",this.transaction);
		this.started=false;
		this.finalized=false;
	}

	@Override
	public void begin() {		
		logger.debug("persistence-transaction-master::start::persistence-transaction::{}",this.transaction);
		super.begin();
		this.started=true;
	}
	@Override
	public void commit() {
		logger.debug("persistence-transaction-master::commit::persistence-transaction::{}",this.transaction);
		try{
			if(super.isActive()){
				super.commit();
			}
		}catch(Throwable e){
			logger.warning("persistence-transaction-master::commit::persistence-transaction::{}::commit::failed",this.transaction,e);
		}
		try{
			this.transactionManager.removeTransaction();
		}catch(Throwable e){
			logger.warning("persistence-transaction-master::commit::persistence-transaction::{}::remove::failed",this.transaction,e);
		}
		this.finalized=true;
	}
	@Override
	public void rollback() {
		logger.debug("persistence-transaction-master::rollback::persistence-transaction::{}",this.transaction);
		try{
			if(super.isActive()){
				super.rollback();
			}
		}catch(Throwable e){
			logger.warning("persistence-transaction-master::rollback::persistence-transaction::{}::rollback::failed",this.transaction,e);
		}
		try{
			this.transactionManager.removeTransaction();
		}catch(Throwable e){
			logger.warning("persistence-transaction-master::rollback::persistence-transaction::{}::remove::failed",this.transaction,e);
		}
		this.finalized=true;
	}

	@Override
	public void setRollbackOnly() {
		logger.debug("persistence-transaction-master::set-rollback::persistence-transaction::{}",this.transaction);
		super.setRollbackOnly(); 
	}
	
	@Override
	public void close() {
		logger.trace("persistence-transaction-master::close::persistence-transaction::{}",this.transaction);
		if(!this.finalized){
			if(this.started){
				logger.info("persistence-transaction-master::close::persistence-transaction::{}::auto-closing-by-rollback",this.transaction);
				try{
					if(super.isActive()){
						super.rollback();
					}
				}catch(Throwable e){
					logger.warning("persistence-transaction-master::close::persistence-transaction::{}::rollback::failed",this.transaction,e);
				}
			}
			try{
				this.transactionManager.removeTransaction();
			}catch(Throwable e){
				logger.warning("persistence-transaction-master::close::persistence-transaction::{}::remove::failed",this.transaction,e);
			}
		}
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("PersistenceTransactionMasterImpl[transaction={0}]",transaction);
	}	
}
