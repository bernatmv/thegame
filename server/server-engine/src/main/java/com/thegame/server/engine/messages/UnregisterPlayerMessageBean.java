package com.thegame.server.engine.messages;

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
public class UnregisterPlayerMessageBean implements IsMessageBean{
	
	@Setter
	@Getter
	private String session;

	@Setter
	@Getter
	private String name;
}
