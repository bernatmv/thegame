package com.thegame.server.common.persistence.mocks;

import java.text.MessageFormat;
import javax.persistence.EntityTransaction;

/**
 * @author afarre
 */
public class EntityTransactionMock implements EntityTransaction{

	private boolean init=false;
	private boolean begin=false;
	private boolean commit=false;
	private boolean rollback=false;
	private boolean rollbackOnly=false;
	private boolean active=false;
	
	
	@Override
	public void begin() {
		this.begin=true;
		if(!init){
			this.active=true;
			this.init=true;
		}
	}
	@Override
	public void commit() {
		this.commit=true;
		this.active=false;
	}
	@Override
	public void rollback() {
		this.rollback=true;
		this.active=false;
	}
	@Override
	public void setRollbackOnly() {
		this.rollbackOnly=true;
	}
	@Override
	public boolean getRollbackOnly() {
		return this.rollbackOnly;
	}
	@Override
	public boolean isActive() {
		return this.active;
	}

	public boolean isInitialized() {
		return init;
	}
	public boolean hasBeenStarted() {
		return begin;
	}
	public boolean hasBeenCommited() {
		return commit;
	}
	public boolean hasBeenRollbacked() {
		return rollback;
	}
	public boolean isRollbackOnly() {
		return rollbackOnly;
	}

	@Override
	public String toString() {
		return MessageFormat.format("EntityTransactionMock[init={0},begin={1},commit={2},rollback={3},rollbackOnly={4},active={5}]",init,begin,commit,rollback,rollbackOnly,active);
	}
}
