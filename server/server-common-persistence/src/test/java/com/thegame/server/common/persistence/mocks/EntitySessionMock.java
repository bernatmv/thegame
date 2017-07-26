package com.thegame.server.common.persistence.mocks;

import com.thegame.server.common.persistence.PersistenceSession;
import com.thegame.server.common.persistence.PersistenceTransaction;
import com.thegame.server.common.persistence.internal.transactions.PersistenceTransactionManager;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

/**
 * @author afarre
 */
public class EntitySessionMock implements PersistenceSession{

	private final static Map<Object,Object> repo=new ConcurrentHashMap<>();
	private final static Map<Object,LockModeType> locks=new ConcurrentHashMap<>();

	private final PersistenceTransactionManager transactionManager;

	private boolean flushed=false;
	private FlushModeType flushMode=FlushModeType.COMMIT;
	private boolean cleaned=false;
	private boolean closed=false;

	public EntitySessionMock(final EntityTransactionMock _transationMock) {
		this.transactionManager=new PersistenceTransactionManager(() -> _transationMock);
	}
	public EntitySessionMock() {
		this.transactionManager=new PersistenceTransactionManager(() -> new EntityTransactionMock());
	}

	public static void cleanup(){
		repo.clear();
		locks.clear();
	}
	
	
	@Override
	public void persist(final Object _entity) {
		EntityMock entity=(EntityMock)_entity;
		repo.put(entity.getId(), entity);
	}

	@Override
	public <T> T merge(final T _entity) {
		EntityMock entity=(EntityMock)_entity;
		repo.put(entity.getId(), entity);
		return _entity;
	}

	@Override
	public void remove(final Object _entity) {
		EntityMock entity=(EntityMock)_entity;
		repo.remove(entity.getId());
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey) {
		String entityId=(String)_primaryKey;
		return (T)repo.get(entityId);
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey,final Map<String, Object> _properties) {
		String entityId=(String)_primaryKey;
		return (T)repo.get(entityId);
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey,final LockModeType _lockMode) {
		String entityId=(String)_primaryKey;
		return (T)repo.get(entityId);
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey,final LockModeType _lockMode,final Map<String, Object> _properties) {
		String entityId=(String)_primaryKey;
		return (T)repo.get(entityId);
	}

	@Override
	public <T> T getReference(final Class<T> _entityClass,final Object _primaryKey) {
		String entityId=(String)_primaryKey;
		return (T)repo.get(entityId);
	}


	@Override
	public void flush() {
		this.flushed=true;
	}

	@Override
	public void setFlushMode(final FlushModeType _flushMode) {
		this.flushMode=_flushMode;
	}

	@Override
	public FlushModeType getFlushMode() {
		return this.flushMode;
	}

	@Override
	public void lock(final Object _entity,final LockModeType _lockMode) {
		EntityMock entity=(EntityMock)_entity;
		locks.put(entity.getId(),_lockMode);
	}

	@Override
	public void lock(final Object _entity,final LockModeType _lockMode,final Map<String, Object> _properties) {
		EntityMock entity=(EntityMock)_entity;
		locks.put(entity.getId(),_lockMode);
	}

	@Override
	public void refresh(Object _entity) {
		EntityMock entity=(EntityMock)_entity;
		entity.setValue(((EntityMock)repo.get(entity.getId())).getValue());
	}

	@Override
	public void refresh(Object _entity, final Map<String, Object> _properties) {
		EntityMock entity=(EntityMock)_entity;
		entity.setValue(((EntityMock)repo.get(entity.getId())).getValue());
	}

	@Override
	public void refresh(Object _entity, final LockModeType _lockMode) {
		EntityMock entity=(EntityMock)_entity;
		entity.setValue(((EntityMock)repo.get(entity.getId())).getValue());
	}

	@Override
	public void refresh(Object _entity, final LockModeType _lockMode, final Map<String, Object> _properties) {
		EntityMock entity=(EntityMock)_entity;
		entity.setValue(((EntityMock)repo.get(entity.getId())).getValue());
	}

	@Override
	public void clear() {
		this.cleaned=true;
	}

	@Override
	public void detach(final Object _entity) {
	}

	@Override
	public boolean contains(final Object _entity) {
		EntityMock entity=(EntityMock)_entity;
		return repo.containsKey(entity.getId());
	}

	@Override
	public LockModeType getLockMode(final Object _entity) {
		return null;
	}

	@Override
	public void setProperty(final String _propertyName,final Object _value) {
	}

	@Override
	public Map<String, Object> getProperties() {
		return null;
	}

	@Override
	public Query createQuery(final String _qlString) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(final CriteriaQuery<T> _criteriaQuery) {
		return null;
	}

	@Override
	public Query createQuery(final CriteriaUpdate _updateQuery) {
		return null;
	}

	@Override
	public Query createQuery(final CriteriaDelete _deleteQuery) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(final String _qlString,final Class<T> _resultClass) {
		return null;
	}

	@Override
	public Query createNamedQuery(final String _name) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(final String _name,final Class<T> _resultClass) {
		return null;
	}

	@Override
	public Query createNativeQuery(final String _sqlString) {
		return null;
	}

	@Override
	public Query createNativeQuery(final String _sqlString,final Class _resultClass) {
		return null;
	}

	@Override
	public Query createNativeQuery(final String _sqlString,final String _resultSetMapping) {
		return null;
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(final String _name) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(final String _procedureName) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(final String _procedureName, final Class... _resultClasses) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(final String _procedureName, final String... _resultSetMappings) {
		return null;
	}

	@Override
	public void joinTransaction() {
	}

	@Override
	public boolean isJoinedToTransaction() {
		return false;
	}

	@Override
	public <T> T unwrap(final Class<T> _cls) {
		return null;
	}

	@Override
	public Object getDelegate() {
		return null;
	}

	@Override
	public boolean isOpen() {
		return !this.closed;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return null;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return null;
	}

	@Override
	public Metamodel getMetamodel() {
		return null;
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(final Class<T> _rootType) {
		return null;
	}

	@Override
	public EntityGraph<?> createEntityGraph(final String _graphName) {
		return null;
	}

	@Override
	public EntityGraph<?> getEntityGraph(final String _graphName) {
		return null;
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(final Class<T> _entityClass) {
		return null;
	}


	@Override
	public void close() {
		this.closed=true;
	}

	@Override
	public EntityTransaction getTransaction() {
		return transactionManager.getTransaction();
	}
	@Override
	public PersistenceTransaction currentTransaction() {
		return transactionManager.getTransaction();
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("EntitySessionMock[flushed={0},flushMode={1},cleaned={2},closed={3}]",flushed,flushMode,cleaned,closed);
	}

	public Map<Object, Object> getRepo() {
		return repo;
	}

	public Map<Object, LockModeType> getLocks() {
		return locks;
	}

	public boolean isFlushed() {
		return flushed;
	}

	public boolean isCleaned() {
		return cleaned;
	}

	public boolean isClosed() {
		return closed;
	}
}
