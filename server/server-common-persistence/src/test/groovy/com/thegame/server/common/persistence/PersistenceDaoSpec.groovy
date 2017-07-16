package com.thegame.server.common.persistence

/**
 * @author afarre
 */
import spock.lang.*
import com.thegame.server.common.persistence.*
import com.thegame.server.common.persistence.exceptions.*
import com.thegame.server.common.persistence.mocks.*
import com.thegame.server.common.persistence.mocks.EntityTransactionMock
import com.thegame.server.common.persistence.mocks.PersistenceDaoMock
import com.thegame.server.common.persistence.mocks.PersistenceSessionFactoryMock
import com.thegame.server.common.persistence.tests.LoggingSpecification

class PersistenceDaoSpec extends LoggingSpecification {

	def PersistenceSessionManager sessionManager
	def PersistenceSessionFactory sessionFactory
	def EntitySessionMock currentRealSession
	def EntityTransactionMock currentTransaction
	
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
			
	}
}

