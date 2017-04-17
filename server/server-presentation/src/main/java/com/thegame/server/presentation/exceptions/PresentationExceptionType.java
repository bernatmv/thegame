package com.thegame.server.presentation.exceptions;

import com.thegame.server.common.exceptions.ExceptionType;

/**
 * @author e103880
 */
public enum PresentationExceptionType implements ExceptionType{
	
	MESSAGE_TO_CLIENT_CONVERSION_FAIL("The message {} from server has failed during client sent process. Underlaying error: {}"),
	MESSAGE_FROM_CLIENT_PROCESSING_FAIL("The message {} from client has failed during processing. Underlaying error: {}"),
	CHANNEL_CLOSING_FAIL("Channel closing by reason {} failed during processing. Underlaying error: {}"),
	;

	private final String description;
	
	
	PresentationExceptionType(final String _description){
		this.description=_description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
