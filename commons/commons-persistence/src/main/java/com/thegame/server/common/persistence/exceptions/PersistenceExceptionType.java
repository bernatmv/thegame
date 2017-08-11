package com.thegame.server.common.persistence.exceptions;

import com.thegame.server.common.exceptions.ExceptionType;
import java.util.function.Supplier;

/**
 * @author afarre
 */
public interface PersistenceExceptionType extends ExceptionType,Supplier<PersistenceException>{

	@Override
	public default PersistenceException get() {
		return new PersistenceException(this);
	}
	public default PersistenceException get(final Object... _args) {
		return new PersistenceException(this,_args);
	}
	public default PersistenceException get(final Throwable _cause,final Object... _args) {
		return new PersistenceException(_cause,this,_args);
	}
}
