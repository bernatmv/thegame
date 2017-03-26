package com.thegame.server.presentation.messages;

import java.util.Date;

/**
 * @author afarre
 */
public class ChatMessage {
	
	private String message;
	private String sender;
	private Date received;

	public String getMessage() {
		return message;
	}
	public void setMessage(String _message) {
		this.message = _message;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String _sender) {
		this.sender = _sender;
	}

	public Date getReceived() {
		return received;
	}
	public void setReceived(Date _received) {
		this.received = _received;
	}
}