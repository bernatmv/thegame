package com.thegame.server.presentation.messages;

import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import org.junit.Test;

/**
 * @author afarre
 */
public class ChatMessageTest extends MessageBaseTest<ChatMessage>{

	@Override
	public Class<ChatMessage> getMessageClass() {
		return ChatMessage.class;
	}

	@Test
	@Override
	public void testDecode() throws InstantiationException, IllegalAccessException, DecodeException {
		String message="{\"kind\":\"ChatMessage\",\"message\":\"feww\",\"sender\":\"de\"}";
		ChatMessage chatMessage=ChatMessage.builder().message("feww").sender("de").build();
		testDecode(message,chatMessage);

		message="{\"kind\": \"ChatMessage\",\"sender\": \"bernatmv\",\"message\": \"Hello world!\"}";
		chatMessage=ChatMessage.builder().message("Hello world!").sender("bernatmv").build();
		testDecode(message,chatMessage);
	}

	@Test
	@Override
	public void testEncode() throws InstantiationException, IllegalAccessException, EncodeException {
		String message="{\"kind\":\"ChatMessage\",\"message\":\"feww\",\"sender\":\"de\",\"time\":1489295163}";
		ChatMessage chatMessage=ChatMessage.builder().message("feww").sender("de").build();
		testEncode(message,chatMessage);

		message="{\"kind\":\"ChatMessage\",\"message\":\"Hello world!\",\"sender\":\"bernatmv\",\"time\":0}";
		chatMessage=ChatMessage.builder().message("Hello world!").sender("bernatmv").build();
		testEncode(message,chatMessage);
	}
}
