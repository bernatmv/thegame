package com.thegame.server.engine.exceptions;

import com.thegame.server.common.exceptions.TypifiedException;


/**
 * @author e103880
 */
public class EngineException extends RuntimeException implements TypifiedException{

	private final EngineExceptionType exceptionType;
	private final Object[] arguments;
	
	
	public EngineException(final EngineExceptionType _exceptionType){
		this(_exceptionType,new Object[]{});
	}
	public EngineException(final EngineExceptionType _exceptionType,final Object... _arguments){
		super(_exceptionType.getDescription());
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	public EngineException(final Throwable _cause,final EngineExceptionType _exceptionType){
		this(_cause,_exceptionType,new Object[]{});
	}
	public EngineException(final Throwable _cause,final EngineExceptionType _exceptionType,final Object... _arguments){
		super(_exceptionType.getDescription(),_cause);
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	
	
	@Override
	public EngineExceptionType getExceptionType(){
		return this.exceptionType;
	}
	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public String getMessage() {
		return getProcessedMessage();
	}
}
