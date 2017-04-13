package com.thegame.server.common.logging;

import com.thegame.server.common.utils.StringUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author afarre
 */
public class LogStream{

	@SuppressWarnings("NonConstantLogger")
	private final Logger logger;

	
	protected LogStream(final Logger _logger) {
		if(_logger==null)
			throw new NullPointerException("logger is null");
		this.logger=_logger;
	}

	
	public static LogStream getLogger(final Class<?> _class){
		return LogStream.getLogger(_class.getName());
	}
	public static LogStream getLogger(final String _logName){
		return new LogStream(Logger.getLogger(_logName));
	}

	
	
	public LogStream log(final Level _level,final String _message,final Object... _args){
		final String[] caller=Stream.of(Thread.currentThread().getStackTrace())
			.skip(1)
			.filter(stacktrace -> !stacktrace.getClassName().equals(LogStream.class.getName()))
			.map(stacktrace -> new String[]{stacktrace.getClassName(),stacktrace.getMethodName()})
			.findFirst()
			.orElse(new String[]{"unknown","unknown"});
		if((_args.length>0)
				&&(_args[_args.length-1]!=null)
				&&(Throwable.class.isAssignableFrom(_args[_args.length-1].getClass()))){
			this.logger.logp(_level,caller[0],caller[1],(Throwable)_args[_args.length-1],() -> StringUtils.format(_message, _args));
		}else{
			this.logger.logp(_level,caller[0],caller[1], () -> StringUtils.format(_message, _args));
		}
		return this;
	}
	
	public LogStream finest(final Throwable _exception){
		return finest("",_exception);
	}
	public LogStream finest(final String _message,final Object... _args){
		return log(Level.FINEST,_message,_args);
	}

	public LogStream trace(final Throwable _exception){
		return trace("",_exception);
	}
	public LogStream trace(final String _message,final Object... _args){
		return log(Level.FINER,_message,_args);
	}
	
	public LogStream debug(final Throwable _exception){
		return debug("",_exception);
	}
	public LogStream debug(final String _message,final Object... _args){
		return log(Level.FINE,_message,_args);
	}
	
	public LogStream info(final Throwable _exception){
		return info("",_exception);
	}
	public LogStream info(final String _message,final Object... _args){
		return log(Level.INFO,_message,_args);
	}
	
	public LogStream warning(final Throwable _exception){
		return warning("",_exception);
	}
	public LogStream warning(final String _message,final Object... _args){
		return log(Level.WARNING,_message,_args);
	}
	
	public LogStream error(final Throwable _exception){
		return error("",_exception);
	}
	public LogStream error(final String _message,final Object... _args){
		return log(Level.SEVERE,_message,_args);
	}
}
