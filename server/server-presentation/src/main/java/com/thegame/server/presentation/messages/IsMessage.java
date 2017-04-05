package com.thegame.server.presentation.messages;

import com.owlike.genson.GensonBuilder;
import com.owlike.genson.annotation.HandleNull;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.presentation.messages.support.LocalDateTimeConverter;
import java.time.LocalDateTime;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author afarre
 */
@HandleNull
public interface IsMessage<T> extends Decoder.Text<T>, Encoder.Text<T> {

	public static final GensonBuilder JsonBuilder=new GensonBuilder()
										.setSkipNull(true)
										.withConverter(new LocalDateTimeConverter(), LocalDateTime.class);


	public default String getKind(){
		return this.getClass().getSimpleName();
	}
	public default LocalDateTime getTime(){
		return LocalDateTime.now();
	}
	
	
	@Override
	public default void init(EndpointConfig _config) {
		final LogStream logger=LogStream.getLogger(this.getClass());
		logger.finest("message::{}::init",getKind());
	}
	@Override
	public default void destroy() {
		final LogStream logger=LogStream.getLogger(this.getClass());
		logger.finest("message::{}::destroy",getKind());
	}
	
	@Override
	public default T decode(final String _string) throws DecodeException{

		final LogStream logger=LogStream.getLogger(this.getClass());
		T reply;

		try{
			logger.finest("message::{}::decode::{}::begin",getKind(),_string);	
			reply=(T)JsonBuilder.create()
								.deserialize(_string, this.getClass());
			logger.trace("message::{}::decode::{}::end",getKind(),_string);		
		} catch (NullPointerException e) {		
			throw new DecodeException(_string,"error-message::decode::failed",e);
		}
		
		return reply;
	}

	
	@Override
	public default String encode(final T _message) throws EncodeException{

		final LogStream logger=LogStream.getLogger(this.getClass());
		String reply;
		
		try{
			logger.finest("message::{}::encode::{}::begin",getKind(),_message);			
			reply=JsonBuilder.create()
							.serialize(_message);
			logger.trace("message::{}::encode::{}::end",getKind(),_message);		
		}catch(NullPointerException e){
			throw new EncodeException(_message,"message::"+getKind()+"::encode::failed",e);
		}
		
		return reply;
	}

	@Override
	public default boolean willDecode(final String _string){
	
		final LogStream logger=LogStream.getLogger(this.getClass());
		boolean reply;
		
		logger.finest("message::{}::will-decode::{}::begin",getKind(),_string);
		reply=_string.contains("\"kind\":\""+getKind()+"\"");
		logger.trace("message::{}::will-decode::{}::end::{}",getKind(),_string,reply);
		
		return reply;
	}
	
}
