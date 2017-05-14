package com.thegame.server.presentation.messages.mappers;

import com.thegame.server.engine.messages.input.ChatMessageBean;
import com.thegame.server.engine.messages.input.MoveMessageBean;
import com.thegame.server.presentation.mappers.InputMessageMapper;
import com.thegame.server.presentation.messages.input.ChatMessage;
import com.thegame.server.presentation.messages.input.MoveMessage;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import com.thegame.server.presentation.mappers.OutputMessageMapper;

/**
 * @author afarre
 */
public class MessageMapperTest {
	
	/**
	 * Test of chatMessateToBean method, of class ChatMessageMapper.
	 */
	@Test
	public void testChatMessateToBean() {
		System.out.println("chatMessateToBean");
		ChatMessage _chatMessage=ChatMessage.builder().message("myMessage").sender("sender").recipient("recipient").build();
		InputMessageMapper instance=Mappers.getMapper(InputMessageMapper.class );
		ChatMessageBean expResult=ChatMessageBean.builder().message("myMessage").recipient("recipient").build();
		ChatMessageBean result=instance.toBean(_chatMessage);
		Assert.assertEquals(expResult, result);
	}

	/**
	 * Test of beanToChatMessateMessage method, of class ChatMessageMapper.
	 */
	@Test
	public void testBeanToChatMessateMessage() {
		System.out.println("beanToChatMessateMessage");
		ChatMessageBean _chatMessage=ChatMessageBean.builder().message("myMessage").sender("sender").recipient("recipient").build();
		OutputMessageMapper instance=Mappers.getMapper(OutputMessageMapper.class );
		ChatMessage expResult=ChatMessage.builder().message("myMessage").sender("sender").build();
		ChatMessage result=instance.toMessage(_chatMessage);
		Assert.assertEquals(expResult, result);
	}

	/**
	 * Test of moveMessateToBean method, of class MessageMapper.
	 */
	@Test
	public void testMoveMessateToBean() {
		System.out.println("moveMessateToBean");
		MoveMessage _moveMessage=MoveMessage.builder().direction("recipient").build();
		InputMessageMapper instance=Mappers.getMapper(InputMessageMapper.class );
		MoveMessageBean expResult=MoveMessageBean.builder().direction("recipient").build();
		MoveMessageBean result=instance.toBean(_moveMessage);
		Assert.assertEquals(expResult, result);
	}
}
