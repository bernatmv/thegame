package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.MessageMapperService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author afarre
 */
public class PlayerServiceImpl implements PlayerService{

	protected final Map<String,PlayerData> players;
	protected final MessageMapperService mapper;

	
	public PlayerServiceImpl() {
		this.players=new ConcurrentHashMap<>();
		this.mapper=EngineServiceFactory.MESSAGEMAPPER.getInstance(MessageMapperService.class);
	}
	
	
	protected Optional<PlayerData> getPlayerData(final String _playerId){
		
		return Optional.of(
						Optional.ofNullable(_playerId)
									.map(playerId -> this.players.get(_playerId))
									.orElseThrow(() -> new EngineException(EngineExceptionType.PLAYER_NOT_REGISTERED,_playerId)));
	}
	protected Optional<PlayerData> getPlayerData(final PlayerMessageBean _player){
		
		return Optional.of(
						Optional.ofNullable(_player)
									.map(player -> this.players.get(player.getName()))
									.orElseThrow(() -> new EngineException(EngineExceptionType.PLAYER_NOT_REGISTERED,_player.getName())));
	}
	
	@Override
	public PlayerMessageBean registerPlayer(final PlayerMessageBean _player,final String _area) {
		
		PlayerData reply;
		
		if(this.players.containsKey(_player.getName())){
			throw new EngineException(EngineExceptionType.PLAYER_ALREADY_REGISTERED,_player);
		}
		reply=PlayerData.builder()
							.name(_player.getName())
							.channel(_player.getChannel())
							.area(_area)
							.build();
		this.players.put(_player.getName(), reply);
		
		return this.mapper.toMessageBean(reply);
	}
	@Override
	public PlayerMessageBean unregisterPlayer(final PlayerMessageBean _player) {

		PlayerData reply=this.players.remove(_player.getName());

		if(reply==null){
			throw new EngineException(EngineExceptionType.PLAYER_NOT_REGISTERED,_player.getName());
		}

		return this.mapper.toMessageBean(reply);
	}

	
	@Override
	public boolean existPlayer(final String _playerName){
		
		return this.players.containsKey(_playerName);
	}
	@Override
	public PlayerMessageBean getPlayer(final String _playerName) {
		
		return getPlayerData(_playerName)
						.map(playerData -> this.mapper.toMessageBean(playerData))
						.get();
	}
	@Override
	public PlayerMessageBean movePlayer(final PlayerMessageBean _player,final String _location) {
		
		return getPlayerData(_player)
						.map(playerData -> {
												playerData.setArea(_location);
												return playerData;
											})
						.map(playerData -> this.mapper.toMessageBean(playerData))
						.get();
	}

	
	@Override
	public Collection<PlayerMessageBean> listPlayers(){
		
		return this.players.values()
							.stream()
							.map(player -> this.mapper.toMessageBean(player))
							.collect(Collectors.toSet());
	}
}
