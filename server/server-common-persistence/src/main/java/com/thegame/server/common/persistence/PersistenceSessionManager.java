package com.thegame.server.common.persistence;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.exceptions.PersistenceError;
import com.thegame.server.common.persistence.exceptions.PersistenceException;
import com.thegame.server.common.persistence.internal.session.PersistenceSessionMasterImpl;
import com.thegame.server.common.persistence.internal.session.PersistenceSessionSlaveImpl;
import java.io.Closeable;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author afarre
 */
public class PersistenceSessionManager implements Closeable{

	private final static LogStream logger=LogStream.getLogger(PersistenceSessionManager.class);
	
	private final ThreadLocal<PersistenceSession> underlayingSessionThreadLocal;
	private final Function<Map<String,String>,EntityManager> sessionSupplier;
	private final Optional<EntityManagerFactory> entityManagerFactory;
	
	public PersistenceSessionManager(final String _persistenceUnit){
		this(_persistenceUnit,Collections.emptyMap());
	}
	public PersistenceSessionManager(final String _persistenceUnit,final Map<String,String> _properties){
		this.underlayingSessionThreadLocal=new ThreadLocal<>();
		this.entityManagerFactory=Optional.of(createFactory(_persistenceUnit,_properties));
		this.sessionSupplier=(properties) -> this.entityManagerFactory
													.map(entityFactory -> entityFactory.createEntityManager(properties))
													.get();
	}
	public PersistenceSessionManager(final EntityManagerFactory _sessionFactory){
		this((properties) -> (PersistenceSession)_sessionFactory.createEntityManager(properties),_sessionFactory);
	}
	public PersistenceSessionManager(final Function<Map<String,String>,EntityManager> _sessionSupplier){
		this(_sessionSupplier,null);
	}
	public PersistenceSessionManager(final Function<Map<String,String>,EntityManager> _sessionSupplier,final EntityManagerFactory _sessionFactory){
		this.underlayingSessionThreadLocal=new ThreadLocal<>();
		this.sessionSupplier=_sessionSupplier;
		this.entityManagerFactory=Optional.ofNullable(_sessionFactory);
	}

	private EntityManagerFactory createFactory(final String _persistenceUnit,final Map<String,String> _properties){
		
		EntityManagerFactory reply=null;
		
		try{
			reply=Persistence.createEntityManagerFactory(_persistenceUnit,_properties);
		}catch(Throwable e){
			logger.warning("persistence-sesson-manager::create-factory::{}::with-properties::{}::failed",_persistenceUnit,_properties,e);
			throw new PersistenceException(e,PersistenceError.UNABLE_TO_CREATE_PERSISTENCE_SESSION,_persistenceUnit,_properties);
		}
		
		return reply;
	}
	private Optional<PersistenceSession> get(){
		return Optional.ofNullable(this.underlayingSessionThreadLocal.get());
	}
	private PersistenceSession createLocalSession(){
		
		final PersistenceSession reply=createSession();

		this.underlayingSessionThreadLocal.set(reply);
		logger.trace("persistence-sesson-manager::session::{}::registered",this.underlayingSessionThreadLocal.get());

		return reply;
	}
	
	
	public PersistenceSession createSession() {
		return createSession(Collections.emptyMap());
	}
	public PersistenceSession createSession(final Map<String,String> _properties) {
		return Optional.ofNullable(this.sessionSupplier.apply(_properties))
						.map(EntityManager -> new PersistenceSessionMasterImpl(EntityManager,this))
						.orElseThrow(PersistenceError.PERSISTENCE_SESSION_NOT_SUPPLIED);
	}
	public PersistenceSession currentSession(){
		return this.underlayingSessionThreadLocal.get();
	}
	public PersistenceSession getSession() {
		return get()
			.map(persistenceSession -> new PersistenceSessionSlaveImpl(persistenceSession))
			.map(persistenceSession -> (PersistenceSession)persistenceSession)
			.orElseGet(this::createLocalSession);
	}

	public void removeSession() {
		logger.trace("persistence-sesson-manager::session::{}::unregistered",this.underlayingSessionThreadLocal.get());
		this.underlayingSessionThreadLocal.remove();
	}
	public boolean hasSession(){
		return this.underlayingSessionThreadLocal.get()!=null;
	}
	public void init(){
		this.entityManagerFactory
			.ifPresent(entityFactory -> entityFactory.isOpen());
	}
	@Override
	public void close(){
		this.entityManagerFactory
			.filter(entityFactory -> entityFactory.isOpen())
			.ifPresent(entityFactory -> entityFactory.close());
	}
}
