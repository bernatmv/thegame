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
}
