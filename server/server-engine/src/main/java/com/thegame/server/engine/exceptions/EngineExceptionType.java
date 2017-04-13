package com.thegame.server.engine.exceptions;

import com.thegame.server.common.exceptions.ExceptionType;

/**
 * @author e103880
 */
public enum EngineExceptionType implements ExceptionType{
	
	MESSAGE_TASK_FACTORY_INITIALIZATION_FAILED("Message task factory initialization failed"),
	MESSAGE_TASK_FACTORY_INSTANT_TASK_FAILED("Message task factory instance of task {} failed"),

	UNABLE_TO_FIND_SUITABLE_CONSTRUCTOR("Business service factory is unable to find suitable {} constructor with args {} to instantiate {}"),
	UNABLE_TO_INSTANTIATE_SERVICE("Business service factory is unable to instantiate {} using {} implementation with {} args"),

	PLAYER_ALREADY_REGISTERED("Player {} is already registered"),
	PLAYER_NOT_REGISTERED("Player {} not registered"),
	;

	private final String description;
	
	
	EngineExceptionType(final String _description){
		this.description=_description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
