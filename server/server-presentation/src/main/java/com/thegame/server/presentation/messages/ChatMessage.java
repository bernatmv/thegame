package com.thegame.server.presentation.messages;

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
public class ChatMessage implements IsMessage<ChatMessage>{

	@Setter
	@Getter
	public String type="SAY";
	
	@Setter
	@Getter
	public String message;

	@Setter
	@Getter
	public String sender;

	@Setter
	@Getter
	public String recipient;
}