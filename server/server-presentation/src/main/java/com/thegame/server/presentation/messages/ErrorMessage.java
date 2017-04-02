package com.thegame.server.presentation.messages;

import com.thegame.server.common.logging.LogStream;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author afarre
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude={"stacktrace"})
public class ErrorMessage implements Decoder.Text<ErrorMessage>, Encoder.Text<ErrorMessage>{

	private static final LogStream log = LogStream.getLogger(ErrorMessage.class);
	
	@Setter
	@Getter
	private String code;

	@Setter
	@Getter
	private String message;

	@Setter
	@Getter
	private String stacktrace;

	
	@Override
	public void init(EndpointConfig _config) {
		log.finest("chat-message::init::begin");
		log.finest("chat-message::init::end");
	}
	@Override
	public void destroy() {
		log.finest("chat-message::destroy::begin");
		log.finest("chat-message::destroy::end");
	}
	
	
	@Override
	public String encode(final ErrorMessage _message) throws EncodeException {

		String reply="ErrorMessage";
		
		try{
			log.finest("error-message::encode::{}::begin",_message);
			reply=reply.concat(Json.createObjectBuilder()
						.add("code", _message.getCode())
						.add("message", _message.getMessage())
						.add("stacktrace", _message.getStacktrace())
						.build()
						.toString());
			log.trace("error-message::encode::{}::end",_message);		
		}catch(NullPointerException e){
			throw new EncodeException(_message,"error-message::encode::failed",e);
		}
		
		return reply;
	}
	@Override
	public ErrorMessage decode(final String _string) throws DecodeException {
		
		ErrorMessage reply;

		try(JsonReader jsonReader = Json.createReader(new StringReader(_string.substring("ErrorMessage".length())))){
			log.finest("error-message::decode::{}::begin",_string);		
			final JsonObject json = jsonReader.readObject();
			reply=ErrorMessage.builder()
									.code(json.getString("code"))
									.message(json.getString("message"))
									.stacktrace(json.getString("stacktrace"))
									.build();
			log.trace("error-message::decode::{}::end::{}",_string,reply.getMessage());		
		} catch (NullPointerException e) {		
			throw new DecodeException(_string,"error-message::decode::failed",e);
		}

		return reply;
	}
	
	
	@Override
	public boolean willDecode(final String _string) {
	
		boolean reply;
		
		log.finest("error-message::will-decode::{}::begin",_string);
		reply=_string.startsWith("ErrorMessage");
		log.trace("error-message::will-decode::{}::end::{}",_string,reply);
		
		return reply;
	}
}