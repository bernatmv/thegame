package com.thegame.server.engine.messages.output;

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
public class PlayerMessageBean implements IsMessageBean{
	
	private String name;
	private String area;
	private Consumer<IsMessageBean> channel;
}
