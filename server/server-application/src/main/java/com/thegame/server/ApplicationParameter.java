package com.thegame.server;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author E103880
 */
public enum ApplicationParameter {

	ACTION("start"),
	HOST("localhost"),
	PORT("8080"),
	NAME("local"),
	;
	
	public final String defaultValue;
	
	ApplicationParameter(final String _default){
		this.defaultValue=_default;
	}
	
	public static final Map<ApplicationParameter,Optional<String>> loadParams(final String... _args){
		
		return Stream.iterate(0,(counter) -> counter +1)
			.limit(ApplicationParameter.values().length)
			.map(counter -> new Object[]{ApplicationParameter.values()[counter],(_args.length>counter)? _args[counter] : null})
			.map(array -> new Object[]{array[0],(array[1]!=null)? 
													((String)array[1]).substring(((ApplicationParameter)array[0]).name().length()+1) 
													: null})
			.collect(Collectors.toMap(array -> (ApplicationParameter)array[0], array -> Optional.ofNullable((String)array[1])));
	}
}
