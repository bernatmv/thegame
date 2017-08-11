package com.thegame.server.common.persistence.tests

import spock.lang.*
import java.util.logging.*

/**
 * @author afarre
 */
class LoggingSpecification extends Specification{

	def setupSpec(){
		System.setProperty("java.util.logging.SimpleFormatter.format","%1\$tY-%1\$tm-%1\$td %1\$tH:%1\$tM:%1\$tS %4\$s %5\$s%6\$s%n")
		for(Handler handler:Logger.getLogger("").getHandlers()){
			handler.setLevel(Level.FINEST)
		}
		Logger.getLogger("").setLevel(Level.WARNING)
		Logger.getLogger("com.thegame").setLevel(Level.FINEST)
	}

}
