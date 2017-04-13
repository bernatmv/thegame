package com.thegame.server.engine.messages;

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
public class RegisterPlayerMessageBean implements IsMessageBean{
	
	@Setter
	@Getter
	private String name;

	@Setter
	@Getter
	private Consumer<IsMessageBean> channel;
}
