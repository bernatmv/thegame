package com.thegame.server.common.persistence

import spock.lang.*
import com.thegame.server.common.persistence.internal.session.*
import com.thegame.server.common.persistence.PersistenceSessionManager
import com.thegame.server.common.persistence.internal.session.PersistenceSessionSlaveImpl
import com.thegame.server.common.persistence.mocks.*
import com.thegame.server.common.persistence.mocks.EntityMock
import com.thegame.server.common.persistence.tests.LoggingSpecification
import javax.persistence.EntityManager


/**
 * @author afarre
 */
class PersistenceSessionSpec extends LoggingSpecification {
	
	def PersistenceSessionManager sessionManager
	def EntitySessionMock currentRealSession
	
	def setup(){
		EntitySessionMock.cleanup()
		sessionManager=new PersistenceSessionManager(
			{properties -> 
				currentRealSession=new EntitySessionMock() 
				return currentRealSession
			})
	}
	def cleanup(){
		sessionManager=null
	}
	
	def "create first session must left an open session with isEmpty repository"(){
		setup:
			def PersistenceSession persitenceSession
		
		when:
			persitenceSession=sessionManager.getSession()
			
		then:
			persitenceSession!=null
			persitenceSession instanceof PersistenceSessionMasterImpl
			persitenceSession.isOpen()==true
			currentRealSession.isOpen()==true
			currentRealSession.getRepo().isEmpty()==true
			sessionManager.hasSession()==true
	}
	def "add entity to first session must left repository with one entity"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
		
		when:
			persitenceSession.persist(entity)	
			
		then:
			persitenceSession!=null
			persitenceSession.isOpen()==true
			persitenceSession instanceof PersistenceSessionMasterImpl
			currentRealSession.isOpen()==true
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			sessionManager.hasSession()==true
	}
	def "close first session must left session closed but with repository changes"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
			persitenceSession.persist(entity)
		
		when:
			persitenceSession.close()	
			
		then:
			persitenceSession!=null
			persitenceSession.isOpen()==false
			persitenceSession instanceof PersistenceSessionMasterImpl
			currentRealSession.isOpen()==false
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			sessionManager.hasSession()==false
	}

	def "create second session must left the session active like the first but with the repository changes applied"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
			persitenceSession.persist(entity)
			persitenceSession.close()
		
		when:
			persitenceSession=sessionManager.getSession()
			
		then:
			persitenceSession!=null
			persitenceSession.isOpen()==true
			persitenceSession instanceof PersistenceSessionMasterImpl
			currentRealSession.isOpen()==true
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			sessionManager.hasSession()==true
	}
	def "add another entity to second session must left the session active and two repository objects"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
			persitenceSession.persist(entity)
			persitenceSession.close()
			def EntityMock entity2=new EntityMock("myEntity2","myEntityValue2")
			persitenceSession=sessionManager.getSession()
		
		when:
			persitenceSession.persist(entity2)
			
		then:
			persitenceSession!=null
			persitenceSession.isOpen()==true
			persitenceSession instanceof PersistenceSessionMasterImpl
			currentRealSession.isOpen()==true
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			currentRealSession.getRepo().get(entity2.getId())==entity2
			sessionManager.hasSession()==true
	}

	def "close second session must left the session inactive but with the two entities at repository"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
			persitenceSession.persist(entity)
			persitenceSession.close()
			persitenceSession=sessionManager.getSession()
			def EntityMock entity2=new EntityMock("myEntity2","myEntityValue2")
			persitenceSession.persist(entity2)

		when:
			persitenceSession.close()
			
		then:
			persitenceSession!=null
			persitenceSession.isOpen()==false
			persitenceSession instanceof PersistenceSessionMasterImpl
			currentRealSession.isOpen()==false
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			currentRealSession.getRepo().get(entity2.getId())==entity2
			sessionManager.hasSession()==false
	}
	
	def "create anidated session must have not side effects and should see the real repo"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
			persitenceSession.persist(entity)
		
		when:
			persitenceSession=sessionManager.getSession()
			
		then:
			persitenceSession!=null
			persitenceSession.isOpen()==true
			persitenceSession instanceof PersistenceSessionSlaveImpl
			currentRealSession.isOpen()==true
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			sessionManager.hasSession()==true
	}
	
	def "persist an additional entity to anidated session must add to the original repo"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
			persitenceSession.persist(entity)
			persitenceSession=sessionManager.getSession()
			def EntityMock entity2=new EntityMock("myEntity2","myEntityValue2")

		when:
			persitenceSession.persist(entity2)
			
		then:
			persitenceSession!=null
			persitenceSession.isOpen()==true
			persitenceSession instanceof PersistenceSessionSlaveImpl
			currentRealSession.isOpen()==true
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			currentRealSession.getRepo().get(entity2.getId())==entity2
			sessionManager.hasSession()==true
	}
	def "close anidated session must have not side effects, keep the original session open and the given closed and keep the repo status"(){
		setup:
			def PersistenceSession persitenceSession=sessionManager.getSession()
			def EntityMock entity=new EntityMock("myEntity","myEntityValue")
			persitenceSession.persist(entity)
			def anidatedPersitenceSession=sessionManager.getSession()
			def EntityMock entity2=new EntityMock("myEntity2","myEntityValue2")
			anidatedPersitenceSession.persist(entity2)

		when:
			anidatedPersitenceSession.close()
			
		then:
			persitenceSession.isOpen()==true
			persitenceSession instanceof PersistenceSessionMasterImpl
			persitenceSession.toString()
			currentRealSession.isOpen()==true
			currentRealSession.getRepo().isEmpty()==false
			currentRealSession.getRepo().get(entity.getId())==entity
			currentRealSession.getRepo().get(entity2.getId())==entity2
			sessionManager.hasSession()==true
			anidatedPersitenceSession instanceof PersistenceSessionSlaveImpl
			anidatedPersitenceSession.isOpen()==false
			anidatedPersitenceSession.toString()
	}
}

