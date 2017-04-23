package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.messages.output.PlayerMessageBean;
import java.util.Collection;

/**
 * @author afarre
 */
public interface PlayerService {

	public PlayerMessageBean registerPlayer(final PlayerMessageBean _player,final String _area);
	public default PlayerMessageBean unregisterPlayer(final String _playerName){
		return unregisterPlayer(getPlayer(_playerName));
	}
	public PlayerMessageBean unregisterPlayer(final PlayerMessageBean _player);

	public boolean existPlayer(final String _playerName);
	public PlayerMessageBean getPlayer(final String _playerName);

	public default PlayerMessageBean movePlayer(final String _playerName,final String _playerLocation){
		return movePlayer(getPlayer(_playerName),_playerLocation);
	}
	public PlayerMessageBean movePlayer(final PlayerMessageBean _player,final String _playerLocation);

	public Collection<PlayerMessageBean> listPlayers();
}
