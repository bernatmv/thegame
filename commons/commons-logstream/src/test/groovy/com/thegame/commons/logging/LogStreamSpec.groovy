package com.thegame.commons.exceptions;

/**
 * @author afarre
 */
import com.thegame.commons.logging.LogStream
import com.thegame.commons.logging.mocks.LogHandlerMock
import java.util.logging.*
import spock.lang.*
import spock.lang.Specification

class LogStreamSpec extends Specification{
	
	def LogHandlerMock logsHandler
	
	
	def setup(){
		this.logsHandler=new LogHandlerMock()
		logsHandler.setLevel(Level.FINEST)
		System.setProperty("java.util.logging.SimpleFormatter.format","%1\$tY-%1\$tm-%1\$td %1\$tH:%1\$tM:%1\$tS %4\$s %5\$s%6\$s%n")
		Logger.getLogger("").addHandler(this.logsHandler)
		Logger.getLogger("").setLevel(Level.SEVERE)
	}
	def cleanup(){
		this.logsHandler=null;
	}

	
	def "Helper getLogger with null must raise a nullPointerException"(){
		when:
			LogStream.getLogger((Logger)null)
			
		then:
			thrown(NullPointerException)			
	}

	def "Helper getLogger with #name must instantiate underlaying logger with the #loggerName name without prefix"(){
		setup:
			def LogStream logStream
			
		when:
			logStream=LogStream.getLogger(name)
			
		then:
			logStream!=null
			logStream.getUnderlayingLogger()!=null
			logStream.getUnderlayingLogger().getName().equals(loggerName)
			logStream.getPrefix().isPresent()==false
			
		where:
			name										| loggerName
			Class.class									| Class.class.getName()
			"string"									| "string"
			Logger.getLogger("javaUtilLoggingLogger")	| "javaUtilLoggingLogger"
	}
	
	def "Helper getLogger Constructor with #name and #prefix with args #args must instantiate underlaying logger with the #loggerName name and prefix #loggerPrefix"(){
		setup:
			def LogStream logStream
			
		when:
			logStream=LogStream.getLogger(name,prefix,(Object[])args)
			
		then:
			logStream!=null
			logStream.getUnderlayingLogger()!=null
			logStream.getUnderlayingLogger().getName().equals(loggerName)
			logStream.getPrefix().isPresent()==true
			logStream.getPrefix().get().equals(loggerPrefix)
			
		where:
			name										| prefix				| args		| loggerName				| loggerPrefix
			Class.class									| "myPrefix"			| []		| Class.class.getName()		| "myPrefix"
			Class.class									| "myPrefix {} with {}"	| [1,"b"]	| Class.class.getName()		| "myPrefix 1 with b"
			"string"									| "myPrefix"			| []		| "string"					| "myPrefix"
			"string"									| "myPrefix {} with {}"	| [1,"b"]	| "string"					| "myPrefix 1 with b"
			Logger.getLogger("javaUtilLoggingLogger")	| "myPrefix"			| []		| "javaUtilLoggingLogger"	| "myPrefix"
			Logger.getLogger("javaUtilLoggingLogger")	| "myPrefix {} with {}"	| [1,"b"]	| "javaUtilLoggingLogger"	| "myPrefix 1 with b"
	}

	def "Log finest #exception should log the #prefix message at finest level with the same exception with #message"(){

		when:
			def LogStream logStream=LogStream.getLogger("finest.logger",(String)prefix)
			logStream.getUnderlayingLogger().setLevel(Level.FINEST)
			logStream.finest(exception)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.FINEST
			if(message==null){
				logRecord.getMessage()==null
			}else{
				logRecord.getMessage()==message
			}
			logRecord.getThrown()==exception
			
		where:
			exception									| prefix		| message
			new Exception("finest")						| null			| null
			new Exception("finest2")					| "test-prefix"	| "test-prefix"
			new NullPointerException("finest3")			| null			| null
			new NullPointerException("finest4")			| "test-prefix"	| "test-prefix"
	}

	def "Log finest #message with #messageArgs should log the given message with replaced args #resultMessage concatenated with #prefix with #prefixArgs replaced"(){

		when:
			def LogStream logStream=LogStream.getLogger("finest.logger",(String)prefix,(Object[])prefixArgs)
			logStream.getUnderlayingLogger().setLevel(Level.FINEST)
			logStream.finest(message,(Object[])messageArgs)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.FINEST
			logRecord.getMessage()==resultMessage
			
		where:
			prefix					| prefixArgs	| message					| messageArgs	| resultMessage
			null					| []			| null						| []			| ""
			null					| []			| "myMessage"				| []			| "myMessage"
			null					| []			| "myMessage {} with {}"	| [2,"c"]		| "myMessage 2 with c"
			"myPrefix:"				| []			| null						| []			| "myPrefix:"
			"myPrefix:"				| []			| "myMessage"				| []			| "myPrefix:myMessage"
			"myPrefix:"				| []			| "myMessage {} with {}"	| [2,"c"]		| "myPrefix:myMessage 2 with c"
			"myPrefix {} with {}:"	| [1,"b"]		| null						| []			| "myPrefix 1 with b:"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage"				| []			| "myPrefix 1 with b:myMessage"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage {} with {}"	| [2,"c"]		| "myPrefix 1 with b:myMessage 2 with c"
			
	}

	def "Log trace #exception should log the #prefix message at finest level with the same exception with #message"(){

		when:
			def LogStream logStream=LogStream.getLogger("trace.logger",(String)prefix)
			logStream.getUnderlayingLogger().setLevel(Level.FINER)
			logStream.trace(exception)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.FINER
			if(message==null){
				logRecord.getMessage()==null
			}else{
				logRecord.getMessage()==message
			}
			logRecord.getThrown()==exception
			
		where:
			exception									| prefix		| message
			new Exception("finest")						| null			| null
			new Exception("finest2")					| "test-prefix"	| "test-prefix"
			new NullPointerException("finest3")			| null			| null
			new NullPointerException("finest4")			| "test-prefix"	| "test-prefix"
	}

	def "Log trace #message with #messageArgs should log the given message with replaced args #resultMessage concatenated with #prefix with #prefixArgs replaced"(){

		when:
			def LogStream logStream=LogStream.getLogger("trace.logger",(String)prefix,(Object[])prefixArgs)
			logStream.getUnderlayingLogger().setLevel(Level.FINER)
			logStream.trace(message,(Object[])messageArgs)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.FINER
			logRecord.getMessage()==resultMessage
			
		where:
			prefix					| prefixArgs	| message					| messageArgs	| resultMessage
			null					| []			| null						| []			| ""
			null					| []			| "myMessage"				| []			| "myMessage"
			null					| []			| "myMessage {} with {}"	| [2,"c"]		| "myMessage 2 with c"
			"myPrefix:"				| []			| null						| []			| "myPrefix:"
			"myPrefix:"				| []			| "myMessage"				| []			| "myPrefix:myMessage"
			"myPrefix:"				| []			| "myMessage {} with {}"	| [2,"c"]		| "myPrefix:myMessage 2 with c"
			"myPrefix {} with {}:"	| [1,"b"]		| null						| []			| "myPrefix 1 with b:"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage"				| []			| "myPrefix 1 with b:myMessage"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage {} with {}"	| [2,"c"]		| "myPrefix 1 with b:myMessage 2 with c"
			
	}

	def "Log debug #exception should log the #prefix message at finest level with the same exception with #message"(){

		when:
			def LogStream logStream=LogStream.getLogger("debug.logger",(String)prefix)
			logStream.getUnderlayingLogger().setLevel(Level.FINE)
			logStream.debug(exception)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.FINE
			if(message==null){
				logRecord.getMessage()==null
			}else{
				logRecord.getMessage()==message
			}
			logRecord.getThrown()==exception
			
		where:
			exception									| prefix		| message
			new Exception("finest")						| null			| null
			new Exception("finest2")					| "test-prefix"	| "test-prefix"
			new NullPointerException("finest3")			| null			| null
			new NullPointerException("finest4")			| "test-prefix"	| "test-prefix"
	}

	def "Log debug #message with #messageArgs should log the given message with replaced args #resultMessage concatenated with #prefix with #prefixArgs replaced"(){

		when:
			def LogStream logStream=LogStream.getLogger("debug.logger",(String)prefix,(Object[])prefixArgs)
			logStream.getUnderlayingLogger().setLevel(Level.FINE)
			logStream.debug(message,(Object[])messageArgs)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.FINE
			logRecord.getMessage()==resultMessage
			
		where:
			prefix					| prefixArgs	| message					| messageArgs	| resultMessage
			null					| []			| null						| []			| ""
			null					| []			| "myMessage"				| []			| "myMessage"
			null					| []			| "myMessage {} with {}"	| [2,"c"]		| "myMessage 2 with c"
			"myPrefix:"				| []			| null						| []			| "myPrefix:"
			"myPrefix:"				| []			| "myMessage"				| []			| "myPrefix:myMessage"
			"myPrefix:"				| []			| "myMessage {} with {}"	| [2,"c"]		| "myPrefix:myMessage 2 with c"
			"myPrefix {} with {}:"	| [1,"b"]		| null						| []			| "myPrefix 1 with b:"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage"				| []			| "myPrefix 1 with b:myMessage"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage {} with {}"	| [2,"c"]		| "myPrefix 1 with b:myMessage 2 with c"
			
	}

	def "Log info #exception should log the #prefix message at finest level with the same exception with #message"(){

		when:
			def LogStream logStream=LogStream.getLogger("info.logger",(String)prefix)
			logStream.getUnderlayingLogger().setLevel(Level.INFO)
			logStream.info(exception)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.INFO
			if(message==null){
				logRecord.getMessage()==null
			}else{
				logRecord.getMessage()==message
			}
			logRecord.getThrown()==exception
			
		where:
			exception									| prefix		| message
			new Exception("finest")						| null			| null
			new Exception("finest2")					| "test-prefix"	| "test-prefix"
			new NullPointerException("finest3")			| null			| null
			new NullPointerException("finest4")			| "test-prefix"	| "test-prefix"
	}

	def "Log info #message with #messageArgs should log the given message with replaced args #resultMessage concatenated with #prefix with #prefixArgs replaced"(){

		when:
			def LogStream logStream=LogStream.getLogger("info.logger",(String)prefix,(Object[])prefixArgs)
			logStream.getUnderlayingLogger().setLevel(Level.INFO)
			logStream.info(message,(Object[])messageArgs)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.INFO
			logRecord.getMessage()==resultMessage
			
		where:
			prefix					| prefixArgs	| message					| messageArgs	| resultMessage
			null					| []			| null						| []			| ""
			null					| []			| "myMessage"				| []			| "myMessage"
			null					| []			| "myMessage {} with {}"	| [2,"c"]		| "myMessage 2 with c"
			"myPrefix:"				| []			| null						| []			| "myPrefix:"
			"myPrefix:"				| []			| "myMessage"				| []			| "myPrefix:myMessage"
			"myPrefix:"				| []			| "myMessage {} with {}"	| [2,"c"]		| "myPrefix:myMessage 2 with c"
			"myPrefix {} with {}:"	| [1,"b"]		| null						| []			| "myPrefix 1 with b:"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage"				| []			| "myPrefix 1 with b:myMessage"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage {} with {}"	| [2,"c"]		| "myPrefix 1 with b:myMessage 2 with c"
			
	}

	def "Log warning #exception should log the #prefix message at finest level with the same exception with #message"(){

		when:
			def LogStream logStream=LogStream.getLogger("warning.logger",(String)prefix)
			logStream.getUnderlayingLogger().setLevel(Level.WARNING)
			logStream.warning(exception)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.WARNING
			if(message==null){
				logRecord.getMessage()==null
			}else{
				logRecord.getMessage()==message
			}
			logRecord.getThrown()==exception
			
		where:
			exception									| prefix		| message
			new Exception("finest")						| null			| null
			new Exception("finest2")					| "test-prefix"	| "test-prefix"
			new NullPointerException("finest3")			| null			| null
			new NullPointerException("finest4")			| "test-prefix"	| "test-prefix"
	}

	def "Log warning #message with #messageArgs should log the given message with replaced args #resultMessage concatenated with #prefix with #prefixArgs replaced"(){

		when:
			def LogStream logStream=LogStream.getLogger("warning.logger",(String)prefix,(Object[])prefixArgs)
			logStream.getUnderlayingLogger().setLevel(Level.WARNING)
			logStream.warning(message,(Object[])messageArgs)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.WARNING
			logRecord.getMessage()==resultMessage
			
		where:
			prefix					| prefixArgs	| message					| messageArgs	| resultMessage
			null					| []			| null						| []			| ""
			null					| []			| "myMessage"				| []			| "myMessage"
			null					| []			| "myMessage {} with {}"	| [2,"c"]		| "myMessage 2 with c"
			"myPrefix:"				| []			| null						| []			| "myPrefix:"
			"myPrefix:"				| []			| "myMessage"				| []			| "myPrefix:myMessage"
			"myPrefix:"				| []			| "myMessage {} with {}"	| [2,"c"]		| "myPrefix:myMessage 2 with c"
			"myPrefix {} with {}:"	| [1,"b"]		| null						| []			| "myPrefix 1 with b:"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage"				| []			| "myPrefix 1 with b:myMessage"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage {} with {}"	| [2,"c"]		| "myPrefix 1 with b:myMessage 2 with c"
			
	}

	
	def "Log error #exception should log the #prefix message at finest level with the same exception with #message"(){

		when:
			def LogStream logStream=LogStream.getLogger("error.logger",(String)prefix)
			logStream.getUnderlayingLogger().setLevel(Level.SEVERE)
			logStream.error(exception)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.SEVERE
			if(message==null){
				logRecord.getMessage()==null
			}else{
				logRecord.getMessage()==message
			}
			logRecord.getThrown()==exception
			
		where:
			exception									| prefix		| message
			new Exception("finest")						| null			| null
			new Exception("finest2")					| "test-prefix"	| "test-prefix"
			new NullPointerException("finest3")			| null			| null
			new NullPointerException("finest4")			| "test-prefix"	| "test-prefix"
	}

	def "Log error #message with #messageArgs should log the given message with replaced args #resultMessage concatenated with #prefix with #prefixArgs replaced"(){

		when:
			def LogStream logStream=LogStream.getLogger("error.logger",(String)prefix,(Object[])prefixArgs)
			logStream.getUnderlayingLogger().setLevel(Level.SEVERE)
			logStream.error(message,(Object[])messageArgs)
			
		then:
			def LogRecord logRecord=this.logsHandler.getRecords().poll()
			logRecord!=null
			logRecord.getLevel()==Level.SEVERE
			logRecord.getMessage()==resultMessage
			
		where:
			prefix					| prefixArgs	| message					| messageArgs	| resultMessage
			null					| []			| null						| []			| ""
			null					| []			| "myMessage"				| []			| "myMessage"
			null					| []			| "myMessage {} with {}"	| [2,"c"]		| "myMessage 2 with c"
			"myPrefix:"				| []			| null						| []			| "myPrefix:"
			"myPrefix:"				| []			| "myMessage"				| []			| "myPrefix:myMessage"
			"myPrefix:"				| []			| "myMessage {} with {}"	| [2,"c"]		| "myPrefix:myMessage 2 with c"
			"myPrefix {} with {}:"	| [1,"b"]		| null						| []			| "myPrefix 1 with b:"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage"				| []			| "myPrefix 1 with b:myMessage"
			"myPrefix {} with {}:"	| [1,"b"]		| "myMessage {} with {}"	| [2,"c"]		| "myPrefix 1 with b:myMessage 2 with c"
			
	}
}

