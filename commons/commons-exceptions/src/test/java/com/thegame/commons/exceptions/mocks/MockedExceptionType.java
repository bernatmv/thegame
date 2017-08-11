package com.thegame.commons.exceptions.mocks;

import com.thegame.commons.exceptions.ExceptionType;

/**
 * @author afarre
 */
public enum MockedExceptionType implements ExceptionType<MockedTypifiedException>{
	
	TEST_NO_PARAMS("Test message without parameters"),
	TEST_WITH_PARAMS("Test message with parameter1 {} and parameter2 {}"),
	;
	
	private final String description;
	
	MockedExceptionType(final String _description){
		this.description=_description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Class<MockedTypifiedException> getExceptionClass() {
		return MockedTypifiedException.class;
	}
}
