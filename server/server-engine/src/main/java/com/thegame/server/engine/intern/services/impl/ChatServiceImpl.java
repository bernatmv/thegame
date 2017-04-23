package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.messages.input.ChatMessageBean;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.ChatService;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author afarre
 */
public class ChatServiceImpl implements ChatService{

	private final PlayerService playerService;
	private final LocationService locationService;
	
	public ChatServiceImpl(){
		this(EngineServiceFactory.PLAYER.getInstance(PlayerService.class),
			EngineServiceFactory.LOCATION.getInstance(LocationService.class));
	}
	public ChatServiceImpl(final PlayerService _playerService,final LocationService _locationService){
		this.playerService=_playerService;
		this.locationService=_locationService;
	}
	
	protected PlayerMessageBean getSender(final String _fromPlayer){

		return Optional.ofNullable(_fromPlayer)
			.filter(playerName -> this.playerService.existPlayer(playerName))
			.map(playerName -> this.playerService.getPlayer(playerName))
			.orElseThrow(() -> new EngineException(EngineExceptionType.UNKNOWN_SENDER,_fromPlayer));
	}
	protected PlayerMessageBean getRecipient(final String _toPlayer){

		return Optional.ofNullable(_toPlayer)
			.filter(playerName -> this.playerService.existPlayer(playerName))
			.map(playerName -> this.playerService.getPlayer(playerName))
			.orElseThrow(() -> new EngineException(EngineExceptionType.UNKNOWN_RECIPIENT,_toPlayer));
	}
	
	@Override
	public void whisper(final String _fromPlayer,final String _toPlayer,final String _message) {
		
		Stream.of(getSender(_fromPlayer),getRecipient(_toPlayer))
			.forEach(player -> player.getChannel().accept(ChatMessageBean.builder()
									.type(ChatMessageBean.MessageType.WHISPER)
									.sender(_fromPlayer)
									.recipient(player.getName())
									.message(_message)
									.build()));
	}

	@Override
	public void say(final String _fromPlayer,final String _message) {

		Optional.ofNullable(getSender(_fromPlayer))
			.map(player -> player.getArea())
			.ifPresent(areaId -> this.locationService.notify(areaId,ChatMessageBean.builder()
									.type(ChatMessageBean.MessageType.SAY)
									.sender(_fromPlayer)
									.message(_message)
									.build()));
	}

	@Override
	public void yell(final String _fromPlayer,final String _message) {

		this.playerService.listPlayers()
			.stream()
			.forEach(player -> player.getChannel().accept(ChatMessageBean.builder()
									.type(ChatMessageBean.MessageType.YELL)
									.sender(getSender(_fromPlayer).getName())
									.message(_message.toUpperCase())
									.build()));
	}
}
