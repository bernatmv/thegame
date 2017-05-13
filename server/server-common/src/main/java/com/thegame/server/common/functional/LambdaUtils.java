package com.thegame.server.common.functional;

import com.thegame.server.common.logging.LogStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * @author afarre
 */
public class LambdaUtils {

	public static final LogStream logger=LogStream.getLogger(LambdaUtils.class);
	
	public static Optional<byte[]> readAllBytesFromResource(final String _resource){
		
		Optional reply=Optional.empty();

		try (InputStream istream=Thread.currentThread().getContextClassLoader().getResourceAsStream(_resource)){
			final byte[] array = new byte[istream.available()];
			istream.read(array);
			reply=Optional.of(array);
		} catch (IOException e) {
			logger.warning("Unable to read bytes from resource {}",_resource);
		}

		return reply;
	}
	public static Optional<byte[]> readAllBytes(final Path _filePath){
		
		Optional reply=Optional.empty();

		try {
			reply=Optional.ofNullable(Files.readAllBytes(_filePath));
		} catch (IOException e) {
			logger.warning("Unable to read bytes from file {}",_filePath);
		}

		return reply;
	}
	public static Optional<List<String>> readAllLines(final Path _filePath){
		return readAllLines(_filePath,Charset.defaultCharset());
	}
	public static Optional<List<String>> readAllLines(final Path _filePath,final Charset _charset){
		
		Optional reply=Optional.empty();

		try {
			reply=Optional.ofNullable(Files.readAllLines(_filePath,_charset));
		} catch (IOException e) {
			logger.warning("Unable to read lines from file {}",_filePath);
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
