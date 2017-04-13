package com.thegame.server.common.utils;

import com.thegame.server.common.utils.security.CypherTool;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

/**
 * @author E103880
 */
public interface EnumProperty {
	
	public String getDefaultValue();
	public boolean isGlobal();
	public boolean isEncrypted();
	public String getKey();
	public Map<String,String> getProperties();
	
	public default String getValue(final Map<String,String> _configuration){
		
		String reply;
	
		final String key=getKey();
		reply=Optional.ofNullable(_configuration.get(key))
				.orElse(getDefaultValue());
		if(isEncrypted()&&reply!=null){
			reply=CypherTool.decrypt(reply);
		}
		
		return reply;
	}
	public default String getValue(){
		return getValue(getProperties());
	}
	
	public default LocalTime getLocalTimeValue(){
		return LocalTime.parse(getValue(), DateTimeFormatter.ISO_TIME);
	}
	public default LocalTime getLocalTimeValue(final Map<String,String> _configuration){
		return LocalTime.parse(getValue(_configuration), DateTimeFormatter.ISO_TIME);
	}
	
	public default <T extends Enum> T getEnumValue(final Class<T> _class){
		return (T)Enum.valueOf(_class,getValue());
	}
	public default <T extends Enum> T getEnumValue(final Class<T> _class,final Map<String,String> _configuration){
		return (T)Enum.valueOf(_class,getValue(_configuration));
	}

	public default boolean getBooleanValue(){
		return Boolean.valueOf(getValue());
	}
	public default boolean getBooleanValue(final Map<String,String> _configuration){
		return Boolean.valueOf(getValue(_configuration));
	}

	public default int getIntValue(){
		return Integer.valueOf(getValue());
	}
	public default int getIntValue(final Map<String,String> _configuration){
		return Integer.valueOf(getValue(_configuration));
	}

	public default Path getPathValue(){
		return Paths.get(getValue());
	}
	public default Path getPathValue(final Map<String,String> _configuration){
		return Paths.get(getValue(_configuration));
	}
	
	public default Class getClassValue(){
		
		Class reply;
		
		try {
			reply=Class.forName(getValue());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		return reply;
	}
	public default Class getClassValue(final Map<String,String> _configuration){

		Class reply;
		
		try {
			reply=Class.forName(getValue(_configuration));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		return reply;
	}
}
