package com.thegame.server.presentation.messages.output;

import com.thegame.server.presentation.messages.IsMessage;
import com.thegame.server.presentation.messages.common.Item;
import com.thegame.server.presentation.messages.common.NonPlayer;
import java.time.LocalDateTime;
import java.util.List;
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
	private String description;
	private Map<String,String> exits;
	private List<String> players;
	private List<Item> items;
	private List<NonPlayer> enemies;
}
