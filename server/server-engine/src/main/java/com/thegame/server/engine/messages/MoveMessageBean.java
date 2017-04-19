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
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MoveMessageBean extends BaseMessageBean<MoveMessageBean>{
	
	@Setter
	@Getter
	private String direction;

	@Builder
	public MoveMessageBean(String direction, String sender) {
		super(sender);
		this.direction=direction;
	}
}
