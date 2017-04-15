package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.messages.IsMessageBean;
import java.util.function.Consumer;
import lombok.AccessLevel;
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
	
	@Setter(AccessLevel.PACKAGE)
	@Getter
	private String name;

	@Setter(AccessLevel.PACKAGE)
	@Getter
	private Consumer<IsMessageBean> channel;
}
