package com.thegame.server.presentation.exceptions;

import com.thegame.server.common.exceptions.TypifiedException;


/**
 * @author e103880
 */
public class PresentationException extends TypifiedException{

	private final PresentationExceptionType exceptionType;
	private final Object[] arguments;
	
	
	public PresentationException(final PresentationExceptionType _exceptionType){
		this(_exceptionType,new Object[]{});
	}
	public PresentationException(final PresentationExceptionType _exceptionType,final Object... _arguments){
		super(_exceptionType.getDescription());
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	public PresentationException(final Throwable _cause,final PresentationExceptionType _exceptionType){
		this(_cause,_exceptionType,new Object[]{});
	}
	public PresentationException(final Throwable _cause,final PresentationExceptionType _exceptionType,final Object... _arguments){
		super(_exceptionType.getDescription(),_cause);
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	
	
	@Override
	public PresentationExceptionType getExceptionType(){
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
