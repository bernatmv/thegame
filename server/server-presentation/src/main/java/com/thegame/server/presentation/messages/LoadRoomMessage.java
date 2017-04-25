package com.thegame.server.presentation.messages;

import java.time.LocalDateTime;
import java.util.Map;
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
public class LoadRoomMessage implements IsMessage<LoadRoomMessage>{

	private LocalDateTime time;	
	private String id;
	private String title;
	private String shortDescription;
	private String description;
	private Map<String,String> exits;
}
