package com.thegame.commons.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author e103880
 */
public abstract class TypifiedException extends RuntimeException{

	private final ExceptionType exceptionType;
	private final Object[] arguments;
	
	
	public TypifiedException(final ExceptionType _exceptionType,final Object... _arguments){
		super();
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	public TypifiedException(final Throwable _cause,final ExceptionType _exceptionType,final Object... _arguments){
		super(_cause);
		this.exceptionType=_exceptionType;
		this.arguments=_arguments;
	}
	
	
	@Override
	public String getMessage() {
		return getProcessedMessage();
	}

	public final ExceptionType getExceptionType(){
		return this.exceptionType;
	}
	public final Object[] getArguments() {
		return arguments;
	}
	public final String getProcessedMessage(){
		return format(getExceptionType().getDescription(), this.arguments);
	}
	
	public final Optional<String> getStringStacktrace(){
		
		Optional<String> reply;
		
		try(StringWriter writer=new StringWriter();
				PrintWriter printWriter=new PrintWriter(writer)){
			printStackTrace(printWriter);
			reply=Optional.of(writer.toString());
		} catch (IOException e) {
			Logger.getLogger(TypifiedException.class.getName()).log(Level.WARNING, "Unable to convert exception to string", e);
			reply=Optional.empty();
		}
		
		return reply;
	}

	protected static final String format(final String _message, final Object... _args) {

		return Optional.ofNullable(_message)
			.filter(message -> _args.length>0)
			.filter(message -> message.contains("{"))
			.map(message -> Stream.of(message.split("\\{\\}"))
			.flatMap(new Function<String, Stream<String>>() {

				private int ic1=0;

				@Override
				public Stream<String> apply(final String _segment) {
					return Stream.of(_segment, Optional.of(ic1++)
						.filter(counter -> counter<_args.length)
						.map(counter -> _args[counter])
						.map(object -> String.valueOf(object))
						.orElse(""));
				}
			})
			.collect(Collectors.joining("")))
			.orElse(_message);
	}
}
