package com.thegame.server.common.persistence

import spock.lang.*
import com.thegame.server.common.persistence.internal.transactions.*
import com.thegame.server.common.persistence.mocks.*
import com.thegame.server.common.persistence.mocks.EntityTransactionMock
import com.thegame.server.common.persistence.tests.LoggingSpecification
import javax.persistence.EntityTransaction

/**
 * @author afarre
 */
class PersistenceTransactionSpec extends LoggingSpecification {
	
	def PersistenceTransactionManager transactionManager
	def EntityTransactionMock currentRealTransaction
	

	def setup(){
		transactionManager=new PersistenceTransactionManager(
			{-> 
				currentRealTransaction=new EntityTransactionMock() 
				return currentRealTransaction
			})
	}
	def cleanup(){
		transactionManager=null
	}
	
	def "create first transaction must left an pending transaction to start (not active)"(){
		setup:
			def PersistenceTransaction persitenceTransaction
		
		when:
			persitenceTransaction=transactionManager.getTransaction()
			
		then:
			persitenceTransaction!=null
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			persitenceTransaction.isActive()==false
			currentRealTransaction.isActive()==false
			currentRealTransaction.hasBeenStarted()==false
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
	}
	def "begin first transaction must left the transaction active"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
		
		when:
			persitenceTransaction.begin()	
			
		then:
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.isActive()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
	}
	def "close first transaction before begin must left the transaction inactive and without any current transaction"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
		
		when:
			persitenceTransaction.close()	
			
		then:
			persitenceTransaction.isActive()==false
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==false
			currentRealTransaction.isInitialized()==false
			currentRealTransaction.hasBeenStarted()==false
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==false
	}
	def "mark as rollback only first transaction must left the transaction active but marked as rollback only"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
		
		when:
			persitenceTransaction.setRollbackOnly()	
			
		then:
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==true
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			currentRealTransaction.isRollbackOnly()==true
			transactionManager.hasTransaction()==true
	}
	def "commit first transaction must left the transaction inactive and without any current transaction"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
		
		when:
			persitenceTransaction.commit()	
			
		then:
			persitenceTransaction.isActive()==false
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==false
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==true
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==false
	}
	def "rollback first transaction must left the transaction inactive and without any current transaction"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
		
		when:
			persitenceTransaction.rollback()	
			
		then:
			persitenceTransaction.isActive()==false
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==false
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==true
			transactionManager.hasTransaction()==false
	}
	def "close first transaction without commit or rollback must left the transaction inactive and without any current transaction"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
		
		when:
			persitenceTransaction.close()	
			
		then:
			persitenceTransaction.isActive()==false
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==false
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==true
			transactionManager.hasTransaction()==false
	}
	def "close first transaction after commit must left the transaction inactive and without any current transaction"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()
			persitenceTransaction.commit()
		
		when:
			persitenceTransaction.close()	
			
		then:
			persitenceTransaction.isActive()==false
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==false
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==true
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==false
	}
	def "close first transaction after rollback must left the transaction inactive and without any current transaction"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()
			persitenceTransaction.rollback()
		
		when:
			persitenceTransaction.close()	
			
		then:
			persitenceTransaction.isActive()==false
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==false
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==true
			transactionManager.hasTransaction()==false
	}
	

	def "create second transaction must left the transaction inactive like the first one"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			persitenceTransaction.commit()
		
		when:
			persitenceTransaction=transactionManager.getTransaction()
			
		then:
			persitenceTransaction!=null
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			persitenceTransaction.isActive()==false
			currentRealTransaction.isActive()==false
			currentRealTransaction.hasBeenStarted()==false
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
	}
	def "start second transaction must left the transaction active like the first one"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			persitenceTransaction.commit()
			persitenceTransaction=transactionManager.getTransaction()
		
		when:
			persitenceTransaction.begin()	
			
		then:
			persitenceTransaction!=null
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			persitenceTransaction.isActive()==true
			currentRealTransaction.isActive()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
	}

	def "commit second transaction must left the transaction inactive like the first one"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			persitenceTransaction.commit()
			persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()
		
		when:
			persitenceTransaction.commit()
			
		then:
			persitenceTransaction!=null
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			persitenceTransaction.isActive()==false
			currentRealTransaction.isActive()==false
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==true
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==false
	}
	
	def "rollback second transaction must left the transaction inactive like the first one"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			persitenceTransaction.commit()
			persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
		
		when:
			persitenceTransaction.rollback()
			
		then:
			persitenceTransaction!=null
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			persitenceTransaction.isActive()==false
			currentRealTransaction.isActive()==false
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==true
			transactionManager.hasTransaction()==false
	}
	def "close second transaction must left the transaction inactive like the first one"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			persitenceTransaction.commit()
			persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
		
		when:
			persitenceTransaction.close()	
			
		then:
			persitenceTransaction.isActive()==false
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==false
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==true
			transactionManager.hasTransaction()==false
	}

	def "create anidated transaction must have not side effects"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			def PersistenceTransaction anidatedPersitenceTransaction
		
		when:
			anidatedPersitenceTransaction=transactionManager.getTransaction()
			
		then:
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==true
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
			anidatedPersitenceTransaction.isActive()==true
			anidatedPersitenceTransaction instanceof PersistenceTransactionSlaveImpl
	}
	
	def "start anidated transaction must have not side effects"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			def PersistenceTransaction anidatedPersitenceTransaction=transactionManager.getTransaction()
		
		when:
			anidatedPersitenceTransaction.begin()
			
		then:
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==true
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
			anidatedPersitenceTransaction.isActive()==true
			anidatedPersitenceTransaction instanceof PersistenceTransactionSlaveImpl
	}
	def "commit anidated transaction must have not side effects"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			def PersistenceTransaction anidatedPersitenceTransaction=transactionManager.getTransaction()
			anidatedPersitenceTransaction.begin()	

		when:
			anidatedPersitenceTransaction.commit()
			
		then:
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==true
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
			anidatedPersitenceTransaction.isActive()==true
			anidatedPersitenceTransaction instanceof PersistenceTransactionSlaveImpl
	}
	def "mark as rollback only anidated transaction must left the transaction marked as rollback only but active"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			def PersistenceTransaction anidatedPersitenceTransaction=transactionManager.getTransaction()
			anidatedPersitenceTransaction.begin()	
		
		when:
			anidatedPersitenceTransaction.setRollbackOnly()	
			
		then:
			anidatedPersitenceTransaction.isActive()==true
			anidatedPersitenceTransaction instanceof PersistenceTransactionSlaveImpl
			anidatedPersitenceTransaction.getRollbackOnly()==true
			anidatedPersitenceTransaction.toString()
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			persitenceTransaction.getRollbackOnly()==true
			persitenceTransaction.toString()
			currentRealTransaction.isActive()==true
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			currentRealTransaction.isRollbackOnly()==true
			transactionManager.hasTransaction()==true
	}
	def "rollback anidated transaction must mark real transaction as rollback only"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			def PersistenceTransaction anidatedPersitenceTransaction=transactionManager.getTransaction()
			anidatedPersitenceTransaction.begin()	
		
		when:
			anidatedPersitenceTransaction.rollback()
			
		then:
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==true
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.isRollbackOnly()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
			anidatedPersitenceTransaction.isActive()==true
			anidatedPersitenceTransaction instanceof PersistenceTransactionSlaveImpl
	}
	def "close anidated transaction must mark real transaction as rollback only"(){
		setup:
			def PersistenceTransaction persitenceTransaction=transactionManager.getTransaction()
			persitenceTransaction.begin()	
			def PersistenceTransaction anidatedPersitenceTransaction=transactionManager.getTransaction()
			anidatedPersitenceTransaction.begin()	

		when:
			anidatedPersitenceTransaction.close()
			
		then:
			persitenceTransaction.isActive()==true
			persitenceTransaction instanceof PersistenceTransactionMasterImpl
			currentRealTransaction.isActive()==true
			currentRealTransaction.isInitialized()==true
			currentRealTransaction.hasBeenStarted()==true
			currentRealTransaction.isRollbackOnly()==true
			currentRealTransaction.hasBeenCommited()==false
			currentRealTransaction.hasBeenRollbacked()==false
			transactionManager.hasTransaction()==true
			anidatedPersitenceTransaction.isActive()==true
			anidatedPersitenceTransaction instanceof PersistenceTransactionSlaveImpl
	}
}

