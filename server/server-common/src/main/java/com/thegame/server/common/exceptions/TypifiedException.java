package com.thegame.server.common.exceptions;

import com.thegame.server.common.utils.StringUtils;

/**
 * @author e103880
 */
public abstract class TypifiedException extends RuntimeException{

	public TypifiedException() {
	}
	public TypifiedException(String _message) {
		super(_message);
	}
	public TypifiedException(String _message, Throwable _cause) {
		super(_message, _cause);
	}
	public TypifiedException(Throwable _cause) {
		super(_cause);
	}

	
	public abstract ExceptionType getExceptionType();
	public abstract Object[] getArguments();
	
	public String getProcessedMessage(){
		return StringUtils.format(getExceptionType().getDescription(), getArguments());
	}
}
