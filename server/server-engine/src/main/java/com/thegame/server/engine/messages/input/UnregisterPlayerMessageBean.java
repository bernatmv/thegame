package com.thegame.server.engine.messages.input;

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
public class UnregisterPlayerMessageBean extends InputMessageBean<UnregisterPlayerMessageBean>{
	
	@Setter
	@Getter
	private String session;

	@Builder
	public UnregisterPlayerMessageBean(final String session,final String sender) {
		super(sender);
		this.session=session;
	}
}
