package com.thegame.server.common.persistence.internal.session;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.persistence.PersistenceSession;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
 *
 * @author afarre
 */
public abstract class PersistenceSessionDelegateImpl implements PersistenceSession{
	
	private final static LogStream logger=LogStream.getLogger(PersistenceSessionDelegateImpl.class,"persistence-session(delegated)");

	protected final EntityManager session;
	
	
	@SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
	public PersistenceSessionDelegateImpl(final EntityManager _session){
		this.session=_session;
		logger.trace("[{}]::create::{}",getKind(),this);
	}
	@SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
	public PersistenceSessionDelegateImpl(final PersistenceSession _session){
		this.session=_session;
		logger.trace("[{}]::create::{}",getKind(),this);
	}

	
	protected abstract String getKind();

	@Override
	public void persist(final Object _entity) {
		session.persist(_entity);
	}

	@Override
	public <T> T merge(final T _entity) {
		return session.merge(_entity);
	}

	@Override
	public void remove(final Object _entity) {
		session.remove(_entity);
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey) {
		return session.find(_entityClass, _primaryKey);
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey,final Map<String, Object> _properties) {
		return session.find(_entityClass, _primaryKey, _properties);
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey,final LockModeType _lockMode) {
		return session.find(_entityClass, _primaryKey, _lockMode);
	}

	@Override
	public <T> T find(final Class<T> _entityClass,final Object _primaryKey,final LockModeType _lockMode,final Map<String, Object> _properties) {
		return session.find(_entityClass,_primaryKey, _lockMode, _properties);
	}

	@Override
	public <T> T getReference(final Class<T> _entityClass,final Object _primaryKey) {
		return session.getReference(_entityClass, _primaryKey);
	}

	@Override
	public void flush() {
		session.flush();
	}

	@Override
	public void setFlushMode(final FlushModeType _flushMode) {
		session.setFlushMode(_flushMode);
	}

	@Override
	public FlushModeType getFlushMode() {
		return session.getFlushMode();
	}

	@Override
	public void lock(final Object _entity,final LockModeType _lockMode) {
		session.lock(_entity, _lockMode);
	}

	@Override
	public void lock(final Object _entity,final LockModeType _lockMode,final Map<String, Object> _properties) {
		session.lock(_entity, _lockMode, _properties);
	}

	@Override
	public void refresh(Object _entity) {
		session.refresh(_entity);
	}

	@Override
	public void refresh(Object _entity, final Map<String, Object> _properties) {
		session.refresh(_entity, _properties);
	}

	@Override
	public void refresh(Object _entity, final LockModeType _lockMode) {
		session.refresh(_entity, _lockMode);
	}

	@Override
	public void refresh(Object _entity, final LockModeType _lockMode, final Map<String, Object> _properties) {
		session.refresh(_entity, _lockMode, _properties);
	}

	@Override
	public void clear() {
		session.clear();
	}

	@Override
	public void detach(final Object _entity) {
		session.detach(_entity);
	}

	@Override
	public boolean contains(final Object _entity) {
		return session.contains(_entity);
	}

	@Override
	public LockModeType getLockMode(final Object _entity) {
		return session.getLockMode(_entity);
	}

	@Override
	public void setProperty(final String _propertyName,final Object _value) {
		session.setProperty(_propertyName, _value);
	}

	@Override
	public Map<String, Object> getProperties() {
		return session.getProperties();
	}

	@Override
	public Query createQuery(final String _qlString) {
		return session.createQuery(_qlString);
	}

	@Override
	public <T> TypedQuery<T> createQuery(final CriteriaQuery<T> _criteriaQuery) {
		return session.createQuery(_criteriaQuery);
	}

	@Override
	public Query createQuery(final CriteriaUpdate _updateQuery) {
		return session.createQuery(_updateQuery);
	}

	@Override
	public Query createQuery(final CriteriaDelete _deleteQuery) {
		return session.createQuery(_deleteQuery);
	}

	@Override
	public <T> TypedQuery<T> createQuery(final String _qlString,final Class<T> _resultClass) {
		return session.createQuery(_qlString, _resultClass);
	}

	@Override
	public Query createNamedQuery(final String _name) {
		return session.createNamedQuery(_name);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(final String _name,final Class<T> _resultClass) {
		return session.createNamedQuery(_name, _resultClass);
	}

	@Override
	public Query createNativeQuery(final String _sqlString) {
		return session.createNativeQuery(_sqlString);
	}

	@Override
	public Query createNativeQuery(final String _sqlString,final Class _resultClass) {
		return session.createNativeQuery(_sqlString, _resultClass);
	}

	@Override
	public Query createNativeQuery(final String _sqlString,final String _resultSetMapping) {
		return session.createNativeQuery(_sqlString, _resultSetMapping);
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(final String _name) {
		return session.createNamedStoredProcedureQuery(_name);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(final String _procedureName) {
		return session.createStoredProcedureQuery(_procedureName);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(final String _procedureName, final Class... _resultClasses) {
		return session.createStoredProcedureQuery(_procedureName, _resultClasses);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(final String _procedureName, final String... _resultSetMappings) {
		return session.createStoredProcedureQuery(_procedureName, _resultSetMappings);
	}

	@Override
	public void joinTransaction() {
		session.joinTransaction();
	}

	@Override
	public boolean isJoinedToTransaction() {
		return session.isJoinedToTransaction();
	}

	@Override
	public <T> T unwrap(final Class<T> _cls) {
		return session.unwrap(_cls);
	}

	@Override
	public Object getDelegate() {
		return session.getDelegate();
	}

	@Override
	public boolean isOpen() {
		return session.isOpen();
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return session.getEntityManagerFactory();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return session.getCriteriaBuilder();
	}

	@Override
	public Metamodel getMetamodel() {
		return session.getMetamodel();
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(final Class<T> _rootType) {
		return session.createEntityGraph(_rootType);
	}

	@Override
	public EntityGraph<?> createEntityGraph(final String _graphName) {
		return session.createEntityGraph(_graphName);
	}

	@Override
	public EntityGraph<?> getEntityGraph(final String _graphName) {
		return session.getEntityGraph(_graphName);
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(final Class<T> _entityClass) {
		return session.getEntityGraphs(_entityClass);
	}
}
