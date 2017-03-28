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

		final Map<ApplicationParameter,String> parametersLoaded=Stream.of(_args)
			.map(arg -> arg.split(":"))
			.map(array -> new Object[]{ApplicationParameter.valueOf(array[0].toUpperCase()),array[1]})
			.collect(Collectors.toMap(array -> (ApplicationParameter)array[0],array -> (String)array[1]));

		return Stream.of(ApplicationParameter.values())
			.collect(Collectors.toMap(param -> param, param -> Optional.of((parametersLoaded.containsKey(param))? parametersLoaded.get(param) : param.defaultValue)));
	}
}
