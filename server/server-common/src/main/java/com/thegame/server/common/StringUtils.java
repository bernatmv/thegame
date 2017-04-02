package com.thegame.server.common;

import com.thegame.server.common.logging.LogStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @author afarre
 */
public class StringUtils {
	
	private static final LogStream log = LogStream.getLogger(StringUtils.class);
	
	public static final String toString(final Throwable _exception){
		
		String reply="";
		
		try(StringWriter writer=new StringWriter();
				PrintWriter printWriter=new PrintWriter(writer)){
			_exception.printStackTrace(printWriter);
			reply=writer.toString();
		} catch (IOException e) {
			log.error("Unable to printStacktrace for exception {}",e);
		}
		
		return reply;
	}
}
