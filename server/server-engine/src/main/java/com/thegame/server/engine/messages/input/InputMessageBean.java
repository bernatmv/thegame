package com.thegame.server.engine.messages.input;

import com.thegame.server.engine.messages.IsMessageBean;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author afarre
 * @param <T>
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class InputMessageBean<T extends InputMessageBean> implements IsMessageBean{

	private String sender;

	
	public String getSender() {
		return sender;
	}
	public T setSender(String _sender) {
		this.sender=_sender;
		return (T)this;
	}
}
