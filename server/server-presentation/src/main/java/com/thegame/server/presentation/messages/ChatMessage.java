package com.thegame.server.presentation.messages;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage implements IsMessage<ChatMessage>{

	public LocalDateTime time;	
	public String type;
	public String message;
	public String sender;
	public String recipient;
}