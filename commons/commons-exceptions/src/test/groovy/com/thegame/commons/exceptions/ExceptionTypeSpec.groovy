package com.thegame.commons.exceptions;

/**
 * @author afarre
 */
import spock.lang.*
import spock.lang.Specification

import com.thegame.server.common.utils.StringUtils;
import com.thegame.server.common.persistence.*
import com.thegame.server.common.persistence.exceptions.*
import com.thegame.server.common.persistence.mocks.*
import com.thegame.server.common.persistence.mocks.EntityMock
import com.thegame.server.common.persistence.mocks.EntityTransactionMock
import com.thegame.server.common.persistence.mocks.PersistenceDaoMock
import com.thegame.server.common.persistence.mocks.PersistenceSessionFactoryMock
import com.thegame.server.common.persistence.tests.LoggingSpecification

class PersistenceDaoSpec extends Specification {

	
	def setup(){
		EntitySessionMock.cleanup()
		this.currentTransaction=new EntityTransactionMock()
		this.sessionManager=new PersistenceSessionManager(
			{properties -> 
				currentRealSession=new EntitySessionMock(currentTransaction) 
				return currentRealSession
			})
		this.sessionFactory=new PersistenceSessionFactoryMock(sessionManager);
	}
	def cleanup(){
		this.sessionManager=null
		this.sessionFactory=null;
	}

	def "Transactional Query must start and commit transaction"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def Optional<Integer> result;
			
		when:
			result=persistenceDao.transactional(
					Integer.class,
					{persistenceSession -> 
						persistenceSession.persist(new EntityMock("A","B"))
						return 1
					})
			
		then:
			result.get()==1
			this.currentRealSession.getRepo().get("A").getValue().equals("B")
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==true
			this.currentTransaction.hasBeenRollbacked()==false
			this.currentTransaction.isActive()==false
	}
	def "Transactional Query error must start and rollback transaction returning the exception"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def Optional<Integer> result;
			
		when:
			result=persistenceDao.transactional(
					Integer.class,
					{persistenceSession -> 
						throw new RuntimeException("Failure");
						return 1
					})
			
		then:
			def exception=thrown(RuntimeException)
			exception.getMessage().equals("Failure")
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==false
			this.currentTransaction.hasBeenRollbacked()==true
			this.currentTransaction.isActive()==false
	}
	def "save entity should start and end transaction and persist the entity"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			
		when:
			persistenceDao.save(EntityMock.class,entity)
			
		then:
			this.currentRealSession.getRepo().get("id1").getValue().equals("value1")
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==true
			this.currentTransaction.hasBeenRollbacked()==false
			this.currentTransaction.isActive()==false
	}
	def "save entity with duplicated id should raise an exception and rollback the transaction"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			
		when:
			persistenceDao.save(EntityMock.class,entity)
			
		then:
			def exception=thrown(PersistenceException)
			exception.getMessage().equals(StringUtils.format(PersistenceError.ENTITY_CREATION_FAIL_DUPLICATE_ID.getDescription(),EntityMock.class.getName(),"id1"))
			this.currentRealSession.getRepo().get("id1").getValue().equals("value1")
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==false
			this.currentTransaction.hasBeenRollbacked()==true
			this.currentTransaction.isActive()==false
	}
	def "contains entity should start and end transaction and persist the entity"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			def result
			
		when:
			result=persistenceDao.contains(EntityMock.class,entity.getId())
			
		then:
			result==true
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==true
			this.currentTransaction.hasBeenRollbacked()==false
			this.currentTransaction.isActive()==false
	}
	def "contains entity should start and end transaction and persist the entity"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			def result
			
		when:
			result=persistenceDao.contains(EntityMock.class,"id2")
			
		then:
			result==false
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==true
			this.currentTransaction.hasBeenRollbacked()==false
			this.currentTransaction.isActive()==false
	}

	def "get entity should start and end transaction and persist the entity"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			def result
			
		when:
			result=persistenceDao.get(EntityMock.class,entity.getId())
			
		then:
			result!=null
			result.getId().equals("id1")
			result.getValue().equals("value1")
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==true
			this.currentTransaction.hasBeenRollbacked()==false
			this.currentTransaction.isActive()==false
	}
	def "get entity of unknown id should raise an exception and rollback the transaction"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			def result
			
		when:
			persistenceDao.get(EntityMock.class,"id2")
			
		then:
			def exception=thrown(PersistenceException)
			exception.getMessage().equals(StringUtils.format(PersistenceError.ENTITY_RETRIEVAL_FAIL_ID_NOT_EXIST.getDescription(),EntityMock.class.getName(),"id2"))
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==false
			this.currentTransaction.hasBeenRollbacked()==true
			this.currentTransaction.isActive()==false
	}

	def "update entity should start and end transaction and merge the entity"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			def result
			
		when:
			entity.setValue("value2")
			result=persistenceDao.update(EntityMock.class,entity)
			
		then:
			result!=null
			result.getId().equals("id1")
			result.getValue().equals("value2")
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==true
			this.currentTransaction.hasBeenRollbacked()==false
			this.currentTransaction.isActive()==false
	}
	def "update entity of unknown id should raise an exception and rollback the transaction"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			
		when:
			def EntityMock entity2=new EntityMock("id2","value2")
			persistenceDao.update(EntityMock.class,entity2)
			
		then:
			def exception=thrown(PersistenceException)
			exception.getMessage().equals(StringUtils.format(PersistenceError.ENTITY_UPDATE_FAIL_ID_NOT_EXIST.getDescription(),EntityMock.class.getName(),"id2"))
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==false
			this.currentTransaction.hasBeenRollbacked()==true
			this.currentTransaction.isActive()==false
	}

	
	def "delete entity should start and end transaction and remove the entity"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			
		when:
			persistenceDao.delete(EntityMock.class,entity)
			
		then:
			this.currentRealSession.getRepo().get("id1")==null
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==true
			this.currentTransaction.hasBeenRollbacked()==false
			this.currentTransaction.isActive()==false
	}
	def "delete entity of unknown id should raise an exception and rollback the transaction"(){
		setup:
			def PersistenceDao persistenceDao=new PersistenceDaoMock(this.sessionFactory);
			def EntityMock entity=new EntityMock("id1","value1")
			def PersistenceSession session=persistenceDao.getSessionFactory().getSession();
			this.currentRealSession.persist(entity)
			session.close();
			
		when:
			def EntityMock entity2=new EntityMock("id2","value2")
			persistenceDao.delete(EntityMock.class,entity2)
			
		then:
			def exception=thrown(PersistenceException)
			exception.getMessage().equals(StringUtils.format(PersistenceError.ENTITY_DELETION_FAIL_ID_NOT_EXIST.getDescription(),EntityMock.class.getName(),"id2"))
			this.currentRealSession.isClosed()==true
			this.currentTransaction.hasBeenStarted()==true
			this.currentTransaction.hasBeenCommited()==false
			this.currentTransaction.hasBeenRollbacked()==true
			this.currentTransaction.isActive()==false
	}
}

