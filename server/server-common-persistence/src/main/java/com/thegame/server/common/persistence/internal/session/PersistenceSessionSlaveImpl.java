package com.thegame.server.common.persistence.internal.session;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceSession;
import com.thegame.server.common.persistence.PersistenceTransaction;
import java.text.MessageFormat;
import javax.persistence.EntityTransaction;

/**
 *
 * @author afarre
 */
public class PersistenceSessionSlaveImpl extends PersistenceSessionDelegateImpl{
	
	private final static LogStream logger=LogStream.getLogger(PersistenceSessionSlaveImpl.class);

	protected boolean open;
	
	
	public PersistenceSessionSlaveImpl(final PersistenceSession _session){
		super(_session);
		this.open=true;
		logger.trace("persistence-session-slave::create::persistence-session::{}",this.session);
	}

	
	@Override
	public void close() {
		logger.trace("persistence-session-slave::close::persistence-session::{}",this.session);
		this.open=false;
	}

	@Override
	public boolean isOpen() {
		return this.open;
	}

	@Override
	public EntityTransaction getTransaction() {
		return this.session.getTransaction();
	}

	@Override
	public PersistenceTransaction currentTransaction() {
		return ((PersistenceSession)this.session).currentTransaction();
	}

	@Override
	public String toString() {
		return MessageFormat.format("PersistenceSessionSlaveImpl[session={0}]",this.session);
	}	
}
