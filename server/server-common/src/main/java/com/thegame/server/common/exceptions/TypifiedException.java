package com.thegame.server.common.exceptions;

import com.thegame.server.common.utils.StringUtils;

/**
 * @author e103880
 */
public interface TypifiedException {

	public ExceptionType getExceptionType();
	public Object[] getArguments();
	
	public default String getProcessedMessage(){
		return StringUtils.format(getExceptionType().getDescription(), getArguments());
	}
}
