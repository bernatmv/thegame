package com.thegame.server.presentation.messages;

import com.thegame.server.common.logging.LogStream;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
@ToString
public class ChatMessage implements Decoder.Text<ChatMessage>, Encoder.Text<ChatMessage>{

	private static final LogStream log = LogStream.getLogger(ChatMessage.class);
	
	@Setter
	@Getter
	private String message;

	@Setter
	@Getter
	private String sender;

	@Setter
	@Getter
	private LocalDateTime received;

	
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
	public String encode(final ChatMessage _message) throws EncodeException {

		String reply="ChatMessage";
		
		try{
			log.finest("chat-message::encode::{}::begin",_message);
			reply=reply.concat(Json.createObjectBuilder()
						.add("message", _message.getMessage())
						.add("sender", _message.getSender())
						.add("received", DateTimeFormatter.ISO_DATE_TIME.format(_message.getReceived()))
						.build()
						.toString());
			log.trace("chat-message::encode::{}::end::{}",_message,reply);		
		}catch(NullPointerException e){
			throw new EncodeException(_message,"chat-message::encode::failed",e);
		}
		
		return reply;
	}
	@Override
	public ChatMessage decode(final String _string) throws DecodeException {
		
		ChatMessage reply;

		try(JsonReader jsonReader = Json.createReader(new StringReader(_string.substring("ChatMessage".length())))){
			log.finest("chat-message::decode::{}::begin",_string);		
			final JsonObject json = jsonReader.readObject();
			reply=ChatMessage.builder()
									.message(json.getString("message"))
									.sender(json.getString("sender"))
									.received(Optional.ofNullable(json.getString("received"))
															.map(textDate -> textDate.trim())
															.filter(textDate -> !textDate.isEmpty())
															.map(textDate -> DateTimeFormatter.ISO_DATE_TIME.parse(textDate))
															.map(temporalAccessor -> LocalDateTime.from(temporalAccessor))
															.orElse(LocalDateTime.now()))										
									.build();
			log.trace("chat-message::decode::{}::end::{}",_string,reply);		
		} catch (NullPointerException e) {		
			throw new DecodeException(_string,"chat-message::decode::failed",e);
		}

		return reply;
	}
	
	
	@Override
	public boolean willDecode(final String _string) {
	
		boolean reply;
		
		log.finest("chat-message::will-decode::{}::begin",_string);
		reply=_string.startsWith("ChatMessage");
		log.trace("chat-message::will-decode::{}::end::{}",_string,reply);
		
		return reply;
	}
}