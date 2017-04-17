package com.thegame.server.engine.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author afarre
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ChatMessageBean extends BaseMessageBean<ChatMessageBean>{

	public enum MessageType{
		SAY,WHISPER,YELL;
	}
	
	@Setter
	@Getter
	private MessageType type;
	
	@Setter
	@Getter
	private String message;

	@Setter
	@Getter
	private String recipient;

	
	@Builder
	public ChatMessageBean(final MessageType type,final String message,final String recipient,final String sender) {
		super(sender);
		this.type=type;
		this.message=message;
		this.recipient=recipient;
	}
}