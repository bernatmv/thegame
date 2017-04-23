package com.thegame.server.engine.messages.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author afarre
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ChatMessageBean extends InputMessageBean<ChatMessageBean>{

	public enum MessageType{
		SAY,WHISPER,YELL;
	}
	
	private MessageType type;
	private String message;
	private String recipient;

	
	@Builder
	public ChatMessageBean(final MessageType type,final String message,final String sender,final String recipient) {
		super(sender);
		this.type=type;
		this.message=message;
		this.recipient=recipient;
	}
}