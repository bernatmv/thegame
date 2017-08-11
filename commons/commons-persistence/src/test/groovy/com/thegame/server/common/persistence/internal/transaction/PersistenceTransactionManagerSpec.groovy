package com.thegame.server.common.persistence.internal.transaction

import spock.lang.*
import com.thegame.server.common.persistence.*
import com.thegame.server.common.persistence.exceptions.*
import com.thegame.server.common.persistence.internal.transactions.*
import com.thegame.server.common.persistence.tests.LoggingSpecification

/**
 * @author afarre
 */
class PersistenceTransactionManagerSpec extends LoggingSpecification {
	
	def "create transaction from an empty supplier should launch an exception"(){
		setup:
			def PersistenceTransactionManager transactionManager=new PersistenceTransactionManager({-> return null})
			def PersistenceTransaction transaction

		when:
			transaction=transactionManager.getTransaction()

		then:
			def exception=thrown(PersistenceException)
			exception.getExceptionType().equals(PersistenceError.TRANSACTION_NOT_SUPPLIED);
			exception.getMessage().equals(PersistenceError.TRANSACTION_NOT_SUPPLIED.getDescription())
			exception.getArguments().length==0;
	}
}

