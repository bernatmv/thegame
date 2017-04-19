package com.thegame.server.engine.messages;

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
public abstract class BaseMessageBean<T extends BaseMessageBean> implements IsMessageBean{

	private String sender;

	
	public String getSender() {
		return sender;
	}
	public T setSender(String _sender) {
		this.sender=_sender;
		return (T)this;
	}
}
