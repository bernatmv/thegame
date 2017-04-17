package com.thegame.server.persistence.exceptions;

import com.thegame.server.common.exceptions.TypifiedException;


/**
 * @author e103880
 */
public class PersistenceException extends RuntimeException implements TypifiedException{

	private final PersistenceExceptionType exceptionType;
	private final Object[] arguments;
	
	
	public PersistenceException(final PersistenceExceptionType _exceptionType){
		this(_exceptionType,new Object[]{});
	}
	public PersistenceException(final PersistenceExceptionType _exceptionType,final Object... _arguments){
		super(_exceptionType.getDescription());
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	public PersistenceException(final Throwable _cause,final PersistenceExceptionType _exceptionType){
		this(_cause,_exceptionType,new Object[]{});
	}
	public PersistenceException(final Throwable _cause,final PersistenceExceptionType _exceptionType,final Object... _arguments){
		super(_exceptionType.getDescription(),_cause);
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	
	
	@Override
	public PersistenceExceptionType getExceptionType(){
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
