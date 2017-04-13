package com.thegame.server.presentation.messages.mappers;

import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.messages.MoveMessageBean;
import com.thegame.server.presentation.messages.ChatMessage;
import com.thegame.server.presentation.messages.MoveMessage;
import com.thegame.server.presentation.messages.mappers.MessageMapperImpl;
import org.junit.Assert;
import org.junit.Test;

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
		MessageMapper instance=new MessageMapperImpl();
		ChatMessageBean expResult=ChatMessageBean.builder().message("myMessage").sender("sender").recipient("recipient").build();
		ChatMessageBean result=instance.chatMessateToBean(_chatMessage);
		Assert.assertEquals(expResult, result);
	}

	/**
	 * Test of beanToChatMessateMessage method, of class ChatMessageMapper.
	 */
	@Test
	public void testBeanToChatMessateMessage() {
		System.out.println("beanToChatMessateMessage");
		ChatMessageBean _chatMessage=ChatMessageBean.builder().message("myMessage").sender("sender").recipient("recipient").build();
		MessageMapper instance=new MessageMapperImpl();
		ChatMessage expResult=ChatMessage.builder().message("myMessage").sender("sender").recipient("recipient").build();
		ChatMessage result=instance.beanToChatMessateMessage(_chatMessage);
		Assert.assertEquals(expResult, result);
	}

	/**
	 * Test of moveMessateToBean method, of class MessageMapper.
	 */
	@Test
	public void testMoveMessateToBean() {
		System.out.println("moveMessateToBean");
		MoveMessage _moveMessage=MoveMessage.builder().sender("sender").direction("recipient").build();
		MessageMapper instance=new MessageMapperImpl();
		MoveMessageBean expResult=MoveMessageBean.builder().sender("sender").direction("recipient").build();
		MoveMessageBean result=instance.moveMessateToBean(_moveMessage);
		Assert.assertEquals(expResult, result);
	}

	/**
	 * Test of beanToMoveMessateMessage method, of class MessageMapper.
	 */
	@Test
	public void testBeanToMoveMessateMessage() {
		System.out.println("beanToMoveMessateMessage");
		MoveMessageBean _moveMessage=MoveMessageBean.builder().sender("sender").direction("recipient").build();
		MessageMapper instance=new MessageMapperImpl();
		MoveMessage expResult=MoveMessage.builder().sender("sender").direction("recipient").build();
		MoveMessage result=instance.beanToMoveMessateMessage(_moveMessage);
		Assert.assertEquals(expResult, result);
	}
}