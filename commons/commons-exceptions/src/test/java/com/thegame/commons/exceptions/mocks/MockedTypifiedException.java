package com.thegame.commons.exceptions.mocks;

import com.thegame.commons.exceptions.TypifiedException;

/**
 * @author afarre
 */
public class MockedTypifiedException extends TypifiedException{

	public MockedTypifiedException(final MockedExceptionType _exceptionType,final Object... _arguments){
		super(_exceptionType,_arguments);
	}
	public MockedTypifiedException(final Throwable _cause,final MockedExceptionType _exceptionType,final Object... _arguments){
		super(_cause,_exceptionType,_arguments);
	}
}
