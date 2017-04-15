package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.services.PlayerService;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author afarre
 */
public class PlayerServiceImpl implements PlayerService{

	public final Map<String,PlayerData> players;

	public PlayerServiceImpl() {
		this.players=new ConcurrentHashMap<>();
	}
	
	@Override
	public PlayerData registerPlayer(final PlayerData _player) {
		
		PlayerData reply;
		
		if(this.players.containsKey(_player.getName())){
			throw new EngineException(EngineExceptionType.PLAYER_ALREADY_REGISTERED,_player);
		}
		reply=PlayerData.builder()
							.name(_player.getName())
							.channel(_player.getChannel())
							.build();
		this.players.put(_player.getName(), reply);
		
		return reply;
	}
	@Override
	public PlayerData getPlayer(final String _playerName) {
		
		PlayerData reply=this.players.get(_playerName);

		if(reply==null){
			throw new EngineException(EngineExceptionType.PLAYER_NOT_REGISTERED,_playerName);
		}

		return reply;
	}
	@Override
	public PlayerData unregisterPlayer(final String _playerName) {

		PlayerData reply=this.players.remove(_playerName);

		if(reply==null){
			throw new EngineException(EngineExceptionType.PLAYER_NOT_REGISTERED,_playerName);
		}

		return reply;
	}
	@Override
	public Collection<PlayerData> listPlayers(){
		return this.players.values();
	}
}
