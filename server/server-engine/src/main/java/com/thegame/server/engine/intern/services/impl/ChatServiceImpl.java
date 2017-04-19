package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.messages.ChatMessageBean;
import com.thegame.server.engine.intern.BusinessServiceFactory;
import com.thegame.server.engine.intern.services.ChatService;
import com.thegame.server.engine.intern.services.PlayerService;
import java.util.stream.Stream;

/**
 * @author afarre
 */
public class ChatServiceImpl implements ChatService{

	private final PlayerService playerService;
	
	public ChatServiceImpl(){
		this(BusinessServiceFactory.PLAYER.getInstance(PlayerService.class));
	}
	public ChatServiceImpl(final PlayerService _playerService){
		this.playerService=_playerService;
	}
	
	
	@Override
	public void whisper(final String _fromPlayer,final String _toPlayer,final String _message) {
		
		if(!this.playerService.existPlayer(_fromPlayer))
			throw new EngineException(EngineExceptionType.UNKNOWN_SENDER,_fromPlayer);
		if(!this.playerService.existPlayer(_toPlayer))
			throw new EngineException(EngineExceptionType.UNKNOWN_RECIPIENT,_toPlayer);

		Stream.of(this.playerService.getPlayer(_toPlayer),this.playerService.getPlayer(_fromPlayer))
			.forEach(player -> player.getChannel().accept(ChatMessageBean.builder()
									.type(ChatMessageBean.MessageType.WHISPER)
									.sender(_fromPlayer)
									.recipient(player.getName())
									.message(_message)
									.build()));
	}

	@Override
	public void say(final String _fromPlayer,final String _message) {

		if(!this.playerService.existPlayer(_fromPlayer))
			throw new EngineException(EngineExceptionType.UNKNOWN_SENDER,_fromPlayer);

		this.playerService.listPlayers()
			.stream()
			.forEach(player -> player.getChannel().accept(ChatMessageBean.builder()
									.type(ChatMessageBean.MessageType.SAY)
									.sender(_fromPlayer)
									.recipient(player.getName())
									.message(_message)
									.build()));
	}

	@Override
	public void yell(final String _fromPlayer,final String _message) {

		if(!this.playerService.existPlayer(_fromPlayer))
			throw new EngineException(EngineExceptionType.UNKNOWN_SENDER,_fromPlayer);

		this.playerService.listPlayers()
			.stream()
			.forEach(player -> player.getChannel().accept(ChatMessageBean.builder()
									.type(ChatMessageBean.MessageType.YELL)
									.sender(_fromPlayer)
									.recipient(player.getName())
									.message(_message.toUpperCase())
									.build()));
	}
}
