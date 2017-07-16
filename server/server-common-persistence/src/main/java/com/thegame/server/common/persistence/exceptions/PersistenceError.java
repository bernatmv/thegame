package com.thegame.server.common.persistence.exceptions;

/**
 * @author afarre
 */
public enum PersistenceError implements PersistenceExceptionType{
	
	PERSISTENCE_SESSION_NOT_SUPPLIED("Unable to create persistence session, persistence supplier returned null"),
	TRANSACTION_NOT_SUPPLIED("Unable to create transaction from persistence session, transaction supplier returned null"),
	UNABLE_TO_CREATE_PERSISTENCE_SESSION("Unable to create persistence session from unit {0} and with properties {1}"),
	;
	
	private final String description;
	
	
	PersistenceError(final String _description){
		this.description=_description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public PersistenceException get() {
		return new PersistenceException(this);
	}
}
