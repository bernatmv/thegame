package com.thegame.server.engine.messages.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author afarre
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MoveMessageBean extends InputMessageBean<MoveMessageBean>{
	
	private String direction;

	@Builder
	public MoveMessageBean(String direction, String sender) {
		super(sender);
		this.direction=direction;
	}
}
