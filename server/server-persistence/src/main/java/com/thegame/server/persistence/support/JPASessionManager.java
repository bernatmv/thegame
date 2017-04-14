package com.thegame.server.persistence.support;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
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
 * @author e103880
 */
public class JPASessionManager implements EntityManager,AutoCloseable{

	private final EntityManager entityManager;
	
	public JPASessionManager(final EntityManager _entityManager){
		this.entityManager=_entityManager;
	}

	@Override
	public void persist(Object _o) {
		entityManager.persist(_o);
	}

	@Override
	public <T> T merge(T _t) {
		return entityManager.merge(_t);
	}

	@Override
	public void remove(Object _o) {
		entityManager.remove(_o);
	}

	@Override
	public <T> T find(Class<T> _type, Object _o) {
		return entityManager.find(_type, _o);
	}

	@Override
	public <T> T find(Class<T> _type, Object _o, Map<String, Object> _map) {
		return entityManager.find(_type, _o, _map);
	}

	@Override
	public <T> T find(Class<T> _type, Object _o, LockModeType _lmt) {
		return entityManager.find(_type, _o, _lmt);
	}

	@Override
	public <T> T find(Class<T> _type, Object _o, LockModeType _lmt, Map<String, Object> _map) {
		return entityManager.find(_type, _o, _lmt, _map);
	}

	@Override
	public <T> T getReference(Class<T> _type, Object _o) {
		return entityManager.getReference(_type, _o);
	}

	@Override
	public void flush() {
		entityManager.flush();
	}

	@Override
	public void setFlushMode(FlushModeType _fmt) {
		entityManager.setFlushMode(_fmt);
	}

	@Override
	public FlushModeType getFlushMode() {
		return entityManager.getFlushMode();
	}

	@Override
	public void lock(Object _o, LockModeType _lmt) {
		entityManager.lock(_o, _lmt);
	}

	@Override
	public void lock(Object _o, LockModeType _lmt, Map<String, Object> _map) {
		entityManager.lock(_o, _lmt, _map);
	}

	@Override
	public void refresh(Object _o) {
		entityManager.refresh(_o);
	}

	@Override
	public void refresh(Object _o, Map<String, Object> _map) {
		entityManager.refresh(_o, _map);
	}

	@Override
	public void refresh(Object _o, LockModeType _lmt) {
		entityManager.refresh(_o, _lmt);
	}

	@Override
	public void refresh(Object _o, LockModeType _lmt, Map<String, Object> _map) {
		entityManager.refresh(_o, _lmt, _map);
	}

	@Override
	public void clear() {
		entityManager.clear();
	}

	@Override
	public void detach(Object _o) {
		entityManager.detach(_o);
	}

	@Override
	public boolean contains(Object _o) {
		return entityManager.contains(_o);
	}

	@Override
	public LockModeType getLockMode(Object _o) {
		return entityManager.getLockMode(_o);
	}

	@Override
	public void setProperty(String _string, Object _o) {
		entityManager.setProperty(_string, _o);
	}

	@Override
	public Map<String, Object> getProperties() {
		return entityManager.getProperties();
	}

	@Override
	public Query createQuery(String _string) {
		return entityManager.createQuery(_string);
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> _cq) {
		return entityManager.createQuery(_cq);
	}

	@Override
	public Query createQuery(CriteriaUpdate _cu) {
		return entityManager.createQuery(_cu);
	}

	@Override
	public Query createQuery(CriteriaDelete _cd) {
		return entityManager.createQuery(_cd);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String _string, Class<T> _type) {
		return entityManager.createQuery(_string, _type);
	}

	@Override
	public Query createNamedQuery(String _string) {
		return entityManager.createNamedQuery(_string);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String _string, Class<T> _type) {
		return entityManager.createNamedQuery(_string, _type);
	}

	@Override
	public Query createNativeQuery(String _string) {
		return entityManager.createNativeQuery(_string);
	}

	@Override
	public Query createNativeQuery(String _string, Class _type) {
		return entityManager.createNativeQuery(_string, _type);
	}

	@Override
	public Query createNativeQuery(String _string, String _string1) {
		return entityManager.createNativeQuery(_string, _string1);
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String _string) {
		return entityManager.createNamedStoredProcedureQuery(_string);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String _string) {
		return entityManager.createStoredProcedureQuery(_string);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String _string, Class... _types) {
		return entityManager.createStoredProcedureQuery(_string, _types);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String _string, String... _strings) {
		return entityManager.createStoredProcedureQuery(_string, _strings);
	}

	@Override
	public void joinTransaction() {
		entityManager.joinTransaction();
	}

	@Override
	public boolean isJoinedToTransaction() {
		return entityManager.isJoinedToTransaction();
	}

	@Override
	public <T> T unwrap(Class<T> _type) {
		return entityManager.unwrap(_type);
	}

	@Override
	public Object getDelegate() {
		return entityManager.getDelegate();
	}

	@Override
	public void close() {
		entityManager.close();
	}

	@Override
	public boolean isOpen() {
		return entityManager.isOpen();
	}

	@Override
	public EntityTransaction getTransaction() {
		return entityManager.getTransaction();
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManager.getEntityManagerFactory();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	@Override
	public Metamodel getMetamodel() {
		return entityManager.getMetamodel();
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> _type) {
		return entityManager.createEntityGraph(_type);
	}

	@Override
	public EntityGraph<?> createEntityGraph(String _string) {
		return entityManager.createEntityGraph(_string);
	}

	@Override
	public EntityGraph<?> getEntityGraph(String _string) {
		return entityManager.getEntityGraph(_string);
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> _type) {
		return entityManager.getEntityGraphs(_type);
	}
	
}
