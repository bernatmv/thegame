package com.thegame.server.engine.services;

/**
 * @author afarre
 */
public interface ChatService {

	public void whisper(final String _fromPlayer,final String _toPlayer,final String _message);
	public void say(final String _fromPlayer,final String _message);
	public void yell(final String _fromPlayer,final String _message);
}
