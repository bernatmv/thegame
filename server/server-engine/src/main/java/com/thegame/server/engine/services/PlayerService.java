package com.thegame.server.engine.services;

import com.thegame.server.engine.data.PlayerData;
import java.util.Collection;

/**
 * @author afarre
 */
public interface PlayerService {

	public PlayerData registerPlayer(final PlayerData _player);
	public PlayerData getPlayer(final String _playerName);
	public PlayerData unregisterPlayer(final String _playerName);
	public Collection<PlayerData> listPlayers();
}
