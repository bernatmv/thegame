package com.thegame.server.presentation.messages;

import java.io.StringReader;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author afarre
 */
public class ChatDecoderEncoder implements Decoder.Text<ChatMessage>,Encoder.Text<ChatMessage> {

	@Override
	public void init(EndpointConfig _ec) {
	}
	@Override
	public void destroy() {
	}

	@Override
	public String encode(ChatMessage _t) throws EncodeException {
		return Json.createObjectBuilder()
				.add("message", _t.getMessage())
				.add("sender", _t.getSender())
				.add("received", _t.getReceived().toString()).build()
				.toString();
	}

	@Override
	public ChatMessage decode(String _string) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		JsonObject obj = Json.createReader(new StringReader(_string))
				.readObject();
		chatMessage.setMessage(obj.getString("message"));
		chatMessage.setSender(obj.getString("sender"));
		chatMessage.setReceived(new Date());
		return chatMessage;
	}
	@Override
	public boolean willDecode(String _string) {
		return true;
	}
}
