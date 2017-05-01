package com.thegame.server.presentation.messages.output;

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
public class PlayerLeavesRoomMessage implements IsMessage<PlayerLeavesRoomMessage>{

	private LocalDateTime time;	
	private String player;
	private String exit;
}
