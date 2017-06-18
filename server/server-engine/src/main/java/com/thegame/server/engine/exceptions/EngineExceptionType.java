package com.thegame.server.engine.exceptions;

import com.thegame.server.common.exceptions.ExceptionType;

/**
 * @author e103880
 */
public enum EngineExceptionType implements ExceptionType{
	
	MESSAGE_TASK_FACTORY_INITIALIZATION_FAILED("Message task factory initialization failed"),
	MESSAGE_TASK_FACTORY_INSTANT_TASK_FAILED("Message task factory instance of task {} failed"),
	MESSAGE_TASK_FACTORY_NOT_PROCESSABLE_TASK("Don't exist any task to process message {}"),

	UNABLE_TO_FIND_SUITABLE_CONSTRUCTOR("Business service factory is unable to find suitable {} constructor with args {} to instantiate {}"),
	UNABLE_TO_INSTANTIATE_SERVICE("Business service factory is unable to instantiate {} using {} implementation with {} args"),

	PLAYER_ALREADY_REGISTERED("Player {} is already registered"),
	PLAYER_NOT_REGISTERED("Player {} not registered"),

	RACE_NOT_EXIST("Race {} not exist"),
	
	NONPLAYER_NOT_EXIST("Non-player {} not exist"),
	NONPLAYER_UNCONSOLIDABLE("Non-player {} unable to be consolidated"),

	AREA_NOT_EXIST("Area {} not exist"),
	NO_AREA_EXIT("Area {} hasn't any {} exit"),

	UNKNOWN_SENDER("Unknown sender {}"),
	UNKNOWN_RECIPIENT("Unknown recipient {}"),

	INITIAL_AREA_NOT_FOUND("Initial area {} not found in database"),
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
