package com.thegame.server.presentation.messages.input;

import com.thegame.server.presentation.messages.IsMessage;
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

	private LocalDateTime time;	
	private String type;
	private String message;
	private String sender;
	private String recipient;
}