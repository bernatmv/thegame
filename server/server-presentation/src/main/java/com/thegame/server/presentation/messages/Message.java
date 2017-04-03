package com.thegame.server.presentation.messages;

import com.thegame.server.common.logging.LogStream;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import lombok.Getter;

/**
 * @author afarre
 * @param <T>
 */
public abstract class Message<T> implements Decoder.Text<T>, Encoder.Text<T> {
	
	private static final LogStream log = LogStream.getLogger(Message.class);
	
	@Getter
	protected final String kind;

	
	public Message() {
		this.kind=this.getClass().getSimpleName();
	}
	
	
	@Override
	public void init(EndpointConfig _config) {
		log.finest("message::{}::init",getKind());
	}
	@Override
	public void destroy() {
		log.finest("message::{}::destroy",getKind());
	}

	
	protected JsonObjectBuilder encodeBuilder(final Message _message) throws EncodeException {
		return Json.createObjectBuilder()
			.add("kind", _message.getKind());
	}

	
	@Override
	public boolean willDecode(final String _string){
	
		boolean reply;
		
		log.finest("message::{}::will-decode::{}::begin",getKind(),_string);
		reply=_string.contains("\"kind\":\""+getKind()+"\"");
		log.trace("message::{}::will-decode::{}::end::{}",getKind(),_string,reply);
		
		return reply;
	}
}
