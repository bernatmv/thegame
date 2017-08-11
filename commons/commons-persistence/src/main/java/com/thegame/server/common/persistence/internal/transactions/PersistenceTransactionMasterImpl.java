package com.thegame.server.common.persistence.internal.transactions;

import com.thegame.server.common.logging.LogStream;
import java.text.MessageFormat;
import javax.persistence.EntityTransaction;

/**
 * @author afarre
 */
public class PersistenceTransactionMasterImpl extends PersistenceTransactionDelegateImpl {
	
	private final static String KIND="MASTER";
	private final static LogStream logger=LogStream.getLogger(PersistenceTransactionMasterImpl.class,"persistence-transaction["+KIND+"]::");

	protected final PersistenceTransactionManager transactionManager;
	private boolean started;
	private boolean finalized;

	public PersistenceTransactionMasterImpl(final EntityTransaction _underlayingEntityTransaction,final PersistenceTransactionManager _transactionManager){
		super(_underlayingEntityTransaction);
		this.transactionManager=_transactionManager;
		this.started=false;
		this.finalized=false;
	}

	@Override
	protected String getKind(){
		return KIND;
	}
	
	@Override
	public void begin() {		
		super.begin();
		this.started=true;
	}
	@Override
	public void commit() {
		try{
			super.commit();
		}catch(Throwable e){
			logger.warning("commit::{}::commit::failed",this,e);
		}
		try{
			this.transactionManager.removeTransaction();
		}catch(Throwable e){
			logger.warning("commit::{}::remove::failed",this,e);
		}
		this.finalized=true;
	}
	@Override
	public void rollback() {
		try{
			super.rollback();
		}catch(Throwable e){
			logger.warning("rollback::{}::rollback::failed",this,e);
		}
		try{
			this.transactionManager.removeTransaction();
		}catch(Throwable e){
			logger.warning("rollback::{}::remove::failed",this,e);
		}
		this.finalized=true;
	}

	@Override
	public void close() {
		logger.trace("close::{}",this);
		if(!this.finalized){
			if(this.started){
				logger.info("close::{}::auto-closing-by-rollback",this);
				try{
					super.rollback();
				}catch(Throwable e){
					logger.warning("close::{}::rollback::failed",this,e);
				}
			}
			try{
				this.transactionManager.removeTransaction();
			}catch(Throwable e){
				logger.warning("close::{}::remove::failed",this,e);
			}
		}
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("PersistenceTransactionMasterImpl[transaction={0}]",this.transaction);
	}	
}
