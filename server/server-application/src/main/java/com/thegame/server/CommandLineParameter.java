package com.thegame.server;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author E103880
 */
public enum CommandLineParameter {

	ACTION("start"),
	HOST("localhost"),
	PORT("8080"),
	NAME("local"),
	;
	
	public final String defaultValue;
	
	CommandLineParameter(final String _default){
		this.defaultValue=_default;
	}
	
	public static final Map<CommandLineParameter,Optional<String>> loadParams(final String... _args){

		final Map<CommandLineParameter,String> parametersLoaded=Stream.of(_args)
			.map(arg -> arg.split(":"))
			.map(array -> new Object[]{CommandLineParameter.valueOf(array[0].toUpperCase()),array[1]})
			.collect(Collectors.toMap(array -> (CommandLineParameter)array[0],array -> (String)array[1]));

		return Stream.of(CommandLineParameter.values())
			.collect(Collectors.toMap(param -> param, param -> Optional.of((parametersLoaded.containsKey(param))? parametersLoaded.get(param) : param.defaultValue)));
	}
}
