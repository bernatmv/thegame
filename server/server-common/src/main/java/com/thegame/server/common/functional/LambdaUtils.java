package com.thegame.server.common.functional;

/**
 * @author afarre
 */
public class LambdaUtils {

	public static Class classForName(final String _fullClassName){
		
		Class reply;
		
		try {
			reply=Class.forName(_fullClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.join(" ", "Class",_fullClassName,"not found!"),e);
		}
		
		return reply;
	}
}
