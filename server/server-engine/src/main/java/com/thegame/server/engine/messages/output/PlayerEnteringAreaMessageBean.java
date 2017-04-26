package com.thegame.server.engine.messages.output;

import com.thegame.server.engine.messages.IsMessageBean;
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
public class PlayerEnteringAreaMessageBean implements IsMessageBean{

	private PlayerMessageBean player;
	private String fromArea;
}