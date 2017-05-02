package com.thegame.server.presentation.messages;

import com.thegame.server.presentation.messages.input.MoveMessage;
import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import org.junit.Test;

/**
 * @author afarre
 */
public class MoveMessageTest  extends IsMessageTest<MoveMessage>{

	@Override
	public Class<MoveMessage> getMessageClass() {
		return MoveMessage.class;
	}

	@Test
	@Override
	public void testDecode() throws InstantiationException, IllegalAccessException, DecodeException {
		String message="{\"kind\":\"MoveMessage\",\"direction\": \"north\"}";
		MoveMessage chatMessage=MoveMessage.builder().direction("north").build();
		testDecode(message,chatMessage);
	}

	@Test
	@Override
	public void testEncode() throws InstantiationException, IllegalAccessException, EncodeException {
		String message="{\"direction\":\"south\",\"kind\":\"MoveMessage\",\"time\":323523532}";
		MoveMessage chatMessage=MoveMessage.builder().direction("south").build();
		testEncode(message,chatMessage);
	}
}
