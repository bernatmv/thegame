package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.messages.IsMessageBean;
import java.util.function.Consumer;
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
public class PlayerData {
	
	@Setter
	@Getter
	private String name;

	@Setter
	@Getter
	private Consumer<IsMessageBean> channel;
}
