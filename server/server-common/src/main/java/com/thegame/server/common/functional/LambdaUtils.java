package com.thegame.server.common.functional;

import com.thegame.server.common.logging.LogStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author afarre
 */
public class LambdaUtils {

	public static final LogStream logger=LogStream.getLogger(LambdaUtils.class);
	
	public static Optional<byte[]> readAllBytes(final Path _filePath){
		
		Optional reply=Optional.empty();

		try {
			reply=Optional.ofNullable(Files.readAllBytes(_filePath));
		} catch (IOException e) {
			logger.warning("Unable to read bytes from file {}",_filePath);
		}

		return reply;
	}
	
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
