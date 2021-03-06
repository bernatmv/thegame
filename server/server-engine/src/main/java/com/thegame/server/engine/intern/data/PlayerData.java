package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.messages.IsMessageBean;
import java.util.function.Consumer;
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
public class PlayerData {
	
	private String name;
	private String area;
	private Consumer<IsMessageBean> channel;
}
