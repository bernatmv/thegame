package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.intern.BusinessServiceFactory;
import com.thegame.server.engine.intern.services.ChatService;
import com.thegame.server.engine.intern.services.PlayerService;
import java.util.stream.Stream;

/**
 * @author afarre
 */
public class ChatServiceImpl implements ChatService{

	private final PlayerService playerService=BusinessServiceFactory.PLAYER.getInstance(PlayerService.class);
	
	@Override
	public void whisper(final String _fromPlayer,final String _toPlayer,final String _message) {
		Stream.of(this.playerService.getPlayer(_toPlayer),this.playerService.getPlayer(_fromPlayer))
			.map(player -> player.getChannel())
			.forEach(channel -> channel.accept(ChatMessageBean.builder()
									.sender(_fromPlayer)
									.recipient(_toPlayer)
									.message(_message)
									.build()));
	}

	@Override
	public void say(final String _fromPlayer,final String _message) {
		this.playerService.listPlayers()
			.stream()
			.map(player -> player.getChannel())
			.forEach(channel -> channel.accept(ChatMessageBean.builder()
									.sender(_fromPlayer)
									.message(_message)
									.build()));
	}

	@Override
	public void yell(final String _fromPlayer,final String _message) {
		this.playerService.listPlayers()
			.stream()
			.map(player -> player.getChannel())
			.forEach(channel -> channel.accept(ChatMessageBean.builder()
									.sender(_fromPlayer)
									.message(_message)
									.build()));
	}
}
