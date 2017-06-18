package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.intern.services.DataMapperService;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.PersistenceServiceFactory;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.thegame.server.engine.intern.services.MessageMapperService;
import com.thegame.server.engine.intern.services.NonPlayerService;
import com.thegame.server.engine.intern.services.PlayerService;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.output.PlayerEnteringAreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerExitingAreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * @author afarre
 */
public class LocationServiceImpl implements LocationService{

	private static final LogStream logger=LogStream.getLogger(LocationServiceImpl.class);

	protected final String initialArea;
	protected final Map<String,AreaData> areas;

	protected final MessageMapperService messageMapperService;
	protected final DataMapperService dataMapperService;
	protected final PlayerService playerService;
	protected final NonPlayerService nonPlayerService;

	
	public LocationServiceImpl(final LocationDao _locationDao,final PlayerService _playerService,final NonPlayerService _nonPlayerService) {
		this.messageMapperService=EngineServiceFactory.MESSAGEMAPPER.getInstance(MessageMapperService.class);
		this.dataMapperService=EngineServiceFactory.DATAMAPPER.getInstance(DataMapperService.class);
		this.areas=_locationDao.loadAreas()
									.stream()
										.map(area -> this.dataMapperService.toData(area))
										.collect(Collectors
											.toMap(area -> area.getId(),area -> area));
		this.areas.put(LOGON_AREA,AreaData.builder().id(LOGON_AREA).title("login-area").description("login-area").build());
		this.initialArea=Configuration.INITIAL_AREA.getValue();
		this.playerService=_playerService;
		this.nonPlayerService=_nonPlayerService;
	}
	public LocationServiceImpl() {
		this(PersistenceServiceFactory.LOCATIONDAO.getInstance(LocationDao.class)
				,EngineServiceFactory.PLAYER.getInstance(PlayerService.class)
				,EngineServiceFactory.NONPLAYER.getInstance(NonPlayerService.class));
	}
	
	
	protected Optional<AreaData> getAreaData(final AreaMessageBean _area){
		return Optional.of(Optional.ofNullable(_area.getId())
							.map(areaId -> this.areas.get(areaId))
							.orElseThrow(() -> new EngineException(EngineExceptionType.AREA_NOT_EXIST,_area.getId())));
	}
	protected Optional<AreaData> getAreaData(final String _areaId){
		return Optional.of(Optional.ofNullable(_areaId)
							.map(areaId -> this.areas.get(areaId))
							.orElseThrow(() -> new EngineException(EngineExceptionType.AREA_NOT_EXIST,_areaId)));
	}
	protected Optional<AreaData> registerListener(final AreaData _area,final Consumer<IsMessageBean> _listener){
		return Optional.ofNullable(_area)
			.map(areaData -> areaData.addListener(_listener));
	}
	protected Optional<AreaData> unregisterListener(final AreaData _area,final Consumer<IsMessageBean> _listener){
		return Optional.ofNullable(_area)
			.map(areaData -> areaData.removeListener(_listener));
	}
	protected Optional<AreaData> notifyListeners(final AreaData _area,final IsMessageBean _messageBean){
		
		final Optional<AreaData> reply=Optional.ofNullable(_area);
		
		reply.map(areaData -> areaData.getListeners())
			.orElse(Collections.emptyList())
			.stream()
			.forEach(listener -> listener.accept(_messageBean));
		
		return reply;
	}

	protected AreaMessageBean instantiateMessageBean(final AreaData _areaData){
		
		final AreaMessageBean reply=this.messageMapperService.toMessageBean(_areaData);
		
		reply.setEnemies(_areaData.getEnemies().stream()
										.map(enemyId -> this.nonPlayerService.getNonPlayer(enemyId))
										.map(nonPlayerData -> this.messageMapperService.toMessageBean(nonPlayerData))
										.collect(Collectors.toList()));
		
		return reply;
	}
	
	@Override
	public AreaMessageBean getInitialArea() {
		
		return Optional.of(this.initialArea)
						.map(areaId -> this.areas.get(areaId))
						.map(areaData -> instantiateMessageBean(areaData))
						.orElseThrow(() -> new EngineException(EngineExceptionType.INITIAL_AREA_NOT_FOUND,this.initialArea));
	}
	
	@Override
	public AreaMessageBean getArea(final String _areaId){

		return getAreaData(_areaId)
				.map(areaData -> instantiateMessageBean(areaData))
				.get();
	}


	@Override
	public void notify(final AreaMessageBean _area,final IsMessageBean _messageBean){
		notifyListeners(getAreaData(_area).orElse(null),_messageBean);
	}

	@Override
	public AreaMessageBean getExit(final AreaMessageBean _area,final String _exitName){

		return getAreaData(_area)
				.filter(areaData -> areaData.getExits().containsKey(_exitName))
				.flatMap(areaData -> getAreaData(areaData.getExits().get(_exitName)))
				.map(areaData -> instantiateMessageBean(areaData))
				.orElseThrow(() -> new EngineException(EngineExceptionType.NO_AREA_EXIT,_area.getId(),_exitName));
	}

	
	@Override
	public AreaMessageBean addPlayer(final AreaMessageBean _area,final PlayerMessageBean _player,final AreaMessageBean _originArea){

		return getAreaData(_area)
				.map(areaData -> areaData.addPlayer(_player.getName()))
				.flatMap(areaData -> notifyListeners(areaData, PlayerEnteringAreaMessageBean.builder()
																					.fromArea(_originArea.getId())
																					.player(_player)
																					.build()))
				.flatMap(areaData -> registerListener(areaData, _player.getChannel()))
				.map(areaData -> instantiateMessageBean(areaData))
				.get();
	}
	@Override
	public AreaMessageBean removePlayer(final AreaMessageBean _area,final PlayerMessageBean _player,final AreaMessageBean _destinyArea){

		return getAreaData(_area)
				.map(areaData -> areaData.removePlayer(_player.getName()))
				.flatMap(areaData -> unregisterListener(areaData, _player.getChannel()))
				.flatMap(areaData -> notifyListeners(areaData, PlayerExitingAreaMessageBean.builder()
																					.exit(_destinyArea.getId())
																					.player(_player)
																					.build()))
				.map(areaData -> instantiateMessageBean(areaData))
				.get();
	}
}
