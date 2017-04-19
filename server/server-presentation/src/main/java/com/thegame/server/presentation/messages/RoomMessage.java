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
public class RoomMessage implements IsMessage<RoomMessage>{

	public LocalDateTime time;	
	private String id;
	private String title;
	private String shortDescription;
	private String description;
}
