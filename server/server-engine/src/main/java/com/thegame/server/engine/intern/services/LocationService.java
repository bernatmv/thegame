package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;

/**
 * @author afarre
 */
public interface LocationService {
	
	public static final String LOGON_AREA="logon";

	public AreaMessageBean getInitialArea();
	public AreaMessageBean getArea(final String _areaId);

	
	public default void notify(final String _areaId,final IsMessageBean _messageBean){
		notify(getArea(_areaId),_messageBean);
	}
	public void notify(final AreaMessageBean _area,final IsMessageBean _messageBean);

	
	public default AreaMessageBean getExit(final String _areaId,final String _exitName){
		return getExit(getArea(_areaId),_exitName);
	}
	public AreaMessageBean getExit(final AreaMessageBean _area,final String _exitName);

	
	public default AreaMessageBean addPlayer(final String _areaId,final String _player,final String _originAreaId){
		return addPlayer(getArea(_areaId), 
							EngineServiceFactory.PLAYER.getInstance(PlayerService.class).getPlayer(_player), 
							getArea(_originAreaId));
	};
	public AreaMessageBean addPlayer(final AreaMessageBean _area,final PlayerMessageBean _player,final AreaMessageBean _originArea);
	
	public default AreaMessageBean removePlayer(final String _areaId,final String _player,final String _destinyAreaId){
		return removePlayer(getArea(_areaId), 
							EngineServiceFactory.PLAYER.getInstance(PlayerService.class).getPlayer(_player), 
							getArea(_destinyAreaId));
	};
	public AreaMessageBean removePlayer(final AreaMessageBean _area,final PlayerMessageBean _player,final AreaMessageBean _destinyArea);
}
