package com.thegame.server.common.utils;

import com.thegame.server.common.logging.LogStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author afarre
 */
public class StringUtils {

	private static final LogStream logger = LogStream.getLogger(StringUtils.class);
	
	public static final String format(final String _message, final Object... _args) {

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
	
	public static final String toString(final Throwable _exception){
		
		String reply="";
		
		try(StringWriter writer=new StringWriter();
				PrintWriter printWriter=new PrintWriter(writer)){
			_exception.printStackTrace(printWriter);
			reply=writer.toString();
		} catch (IOException e) {
			logger.error("Unable to printStacktrace for exception {}",e);
		}
		
		return reply;
	}
}
