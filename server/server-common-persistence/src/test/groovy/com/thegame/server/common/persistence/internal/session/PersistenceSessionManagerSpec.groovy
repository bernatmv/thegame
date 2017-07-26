package com.thegame.server.common.persistence.internal.transaction

import spock.lang.*
import com.thegame.server.common.persistence.mocks.*
import com.thegame.server.common.persistence.tests.LoggingSpecification
import com.thegame.server.common.persistence.*
import com.thegame.server.common.persistence.exceptions.*
import com.thegame.server.common.persistence.internal.session.*
import com.thegame.server.common.persistence.internal.session.PersistenceSessionMasterImpl

/**
 * @author afarre
 */
class PersistenceSessionManagerSpec extends LoggingSpecification {

	def "create session from an empty supplier should launch an exception"(){
		setup:
			def PersistenceSessionManager sessionManager=new PersistenceSessionManager({properties -> return null})
			sessionManager.init()
			def PersistenceSession session

		when:
			session=sessionManager.getSession()	

		then:
			def exception=thrown(PersistenceException)
			exception.getExceptionType().equals(PersistenceError.PERSISTENCE_SESSION_NOT_SUPPLIED);
			exception.getMessage().equals(PersistenceError.PERSISTENCE_SESSION_NOT_SUPPLIED.getDescription())
			exception.getArguments().length==0;
		
		cleanup:
			sessionManager.close()
	}

	def "two consequent sessions from the same thread should return distinct session"(){
		setup:
			def PersistenceSessionManager sessionManager=new PersistenceSessionManager({properties -> return new EntitySessionMock()})
			sessionManager.init()
			def PersistenceSession session1
			def PersistenceSession session2

		when:
			session1=sessionManager.getSession()
			session1.close()
			session2=sessionManager.getSession()

		then:
			session1!=session2
			session1 instanceof PersistenceSessionMasterImpl
			session2 instanceof PersistenceSessionMasterImpl

		cleanup:
			sessionManager.close()
	}
	def "two anidated sessions from the same thread should return one master and one slave"(){
		setup:
			def PersistenceSessionManager sessionManager=new PersistenceSessionManager({properties -> return new EntitySessionMock()})
			sessionManager.init()
			def PersistenceSession session1
			def PersistenceSession session2

		when:
			session1=sessionManager.getSession()
			session2=sessionManager.getSession()

		then:
			session1!=session2
			session1 instanceof PersistenceSessionMasterImpl
			session2 instanceof PersistenceSessionSlaveImpl
			session1==session2.session

		cleanup:
			sessionManager.close()
	}
	def "two sessions from the distinct thread should return the distinct session"(){
		setup:
			def final PersistenceSessionManager sessionManager=new PersistenceSessionManager({properties -> return new EntitySessionMock()})
			sessionManager.init()
			def PersistenceSession session1
			def PersistenceSession session2

		when:
			session1=sessionManager.getSession()
			def Thread alternateThread=Thread.start{session2=sessionManager.getSession()}
			alternateThread.join()	

		then:
			session1!=session2
			session1 instanceof PersistenceSessionMasterImpl
			session2 instanceof PersistenceSessionMasterImpl

		cleanup:
			sessionManager.close()
	}
}

