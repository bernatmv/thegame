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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ChatMessageBean implements IsMessageBean{

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
	private String sender;

	@Setter
	@Getter
	private String recipient;
}