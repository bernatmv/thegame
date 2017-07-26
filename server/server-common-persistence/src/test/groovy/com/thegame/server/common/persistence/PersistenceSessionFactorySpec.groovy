package com.thegame.server.common.persistence

/**
 * @author afarre
 */
import spock.lang.*
import com.thegame.server.common.persistence.*
import com.thegame.server.common.persistence.exceptions.*
import com.thegame.server.common.persistence.mocks.*
import com.thegame.server.common.persistence.tests.LoggingSpecification

class PersistenceSessionFactorySpec extends LoggingSpecification {

	def "Instantiate new enum session factory from an known persistence unit"(){
		setup:
			def PersistenceSessionFactory sessionFactory
			
		when:
			sessionFactory=PersistenceSessionMock.WITH_PERSISTENCE_UNIT
			
		then:
			sessionFactory!=null
	}
	def "Instantiate new session factory from a persistence session factory should return a valid session"(){
		setup:
			def PersistenceSessionFactory sessionFactory=PersistenceSessionMock.WITH_PERSISTENCE_UNIT
			def PersistenceSession persistenceSession
			
		when:
			persistenceSession=sessionFactory.getSession()
			
		then:
			persistenceSession!=null
	}
}

