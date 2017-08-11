package com.thegame.server.common.persistence;

import java.io.Closeable;
import javax.persistence.EntityTransaction;

/**
 * @author afarre
 */
public interface PersistenceTransaction extends EntityTransaction,Closeable{

	@Override
	public void close();
}
