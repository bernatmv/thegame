package com.thegame.server.engine.configuration;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.utils.EnumProperty;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author E103880
 */
public enum Configuration implements EnumProperty {
	
	MESSAGE_PROCESSOR_CORE_POOL_SIZE(4,"Message processor core pool size (minimum working threads)"),
	MESSAGE_PROCESSOR_MAX_POOL_SIZE(10,"Message processor maximum pool size (maximum working threads)"),
	MESSAGE_PROCESSOR_KEEP_ALIVE(10,"Message processor thread keep alive amoun (amount of units to keep core exceded threads alive in order to prevent more work demand)"), 
	MESSAGE_PROCESSOR_KEEP_ALIVE_UNITS(TimeUnit.MINUTES,"Message processor thread keep alive units (units to keep core exceded threads alive in order to prevent more work demand)"), 
	MESSAGE_PROCESSOR_QUEUE_SIZE(100,"Message processor work queue size, if more works are added an exception is thrown in order to protect the current work"),
	MESSAGE_PROCESSOR_THREAD_PRIORITY(0,"Message processor processing priority, must be between 1 and 10 included, zero keeps the default value (recommended)"),
	MESSAGE_PROCESSOR_THREAD_STACKSIZE(0,"Message processor stack size, zero keeps the default value (recommended)"),
	;

	private static final LogStream logger=LogStream.getLogger(Configuration.class);
	
	public final String defaultValue;
	public final String description;
	
	Configuration(final Object _defaultValue,final String _description){
		this.defaultValue=String.valueOf(_defaultValue);
		this.description=_description;
	}
	
	@Override
	public boolean isGlobal() {
		return true;
	}
	@Override
	public boolean isEncrypted() {
		return false;
	}
	@Override
	public String getKey() {
		return this.name().toLowerCase();
	}

	public String getDescription() {
		return description;
	}
	@Override
	public Map<String, String> getProperties() {
		
		Map<String, String> reply=new HashMap<>();
		
		//TODO
		
		return reply;
	}
	@Override
	public String getDefaultValue(){
		return this.defaultValue;
	}
	
	public static Map<String,String> getDefaultConfiguration(){
		return Arrays.stream(Configuration.values())
				.collect(Collectors.toMap(config -> config.getKey(), config -> config.defaultValue));
	}
}
