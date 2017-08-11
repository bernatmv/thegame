package com.thegame.server.common.persistence.exceptions;

/**
 * @author afarre
 */
public enum PersistenceError implements PersistenceExceptionType{
	
	PERSISTENCE_SESSION_NOT_SUPPLIED("Unable to create persistence session, persistence supplier returned null"),
	TRANSACTION_NOT_SUPPLIED("Unable to create transaction from persistence session, transaction supplier returned null"),
	UNABLE_TO_CREATE_PERSISTENCE_SESSION("Unable to create persistence session from unit {} and with properties {}"),

	ENTITY_CREATION_FAIL("Unable to create {} with {}"),
	ENTITY_CREATION_FAIL_MANDATORY_PARAMETER("Unable to create {}, mandatory parameter {} is null"),
	ENTITY_CREATION_FAIL_DUPLICATE_ID("Unable to create {}, the id {} already exist"),
	ENTITY_CHECK_FAIL("Unable to identify {} existence by id {}"),
	ENTITY_CHECK_FAIL_MANDATORY_PARAMETER("Unable to identify {}, mandatory parameter {} is null"),
	ENTITY_RETRIEVAL_FAIL("Unable to retrieve {} by id {}"),
	ENTITY_RETRIEVAL_FAIL_MANDATORY_PARAMETER("Unable to retrieve {}, mandatory parameter {} is null"),
	ENTITY_RETRIEVAL_FAIL_ID_NOT_EXIST("Unable to retrieve {}, the id {} does not exist"),
	ENTITY_UPDATE_FAIL("Unable to update {} with {}"),
	ENTITY_UPDATE_FAIL_MANDATORY_PARAMETER("Unable to update {}, mandatory parameter {} is null"),
	ENTITY_UPDATE_FAIL_ID_NOT_EXIST("Unable to update {}, the id {} does not exist"),
	ENTITY_DELETION_FAIL("Unable to delete {} with {}"),
	ENTITY_DELETION_FAIL_MANDATORY_PARAMETER("Unable to delete {}, mandatory parameter {} is null"),
	ENTITY_DELETION_FAIL_ID_NOT_EXIST("Unable to delete {}, the id {} does not exist"),
	;
	
	private final String description;
	
	
	PersistenceError(final String _description){
		this.description=_description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
