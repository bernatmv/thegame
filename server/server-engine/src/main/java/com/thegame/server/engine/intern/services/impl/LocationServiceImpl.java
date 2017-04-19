package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.intern.services.LocationService;
import com.thegame.server.engine.messages.AreaMessageBean;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.PersistenceServiceFactory;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.thegame.server.engine.intern.services.MapperService;

/**
 * @author afarre
 */
public class LocationServiceImpl implements LocationService{

	private static final LogStream logger=LogStream.getLogger(LocationServiceImpl.class);
	
	private final String initialArea;
	private final Map<String,AreaData> areas;

	
	public LocationServiceImpl(final LocationDao _locationDao) {
		this.areas=_locationDao.loadAreas()
									.stream()
										.map(area -> MapperService.instance.toData(area))
										.collect(Collectors
											.toMap(area -> area.getId(),area -> area));
		this.initialArea=Configuration.INITIAL_AREA.getValue();
	}
	public LocationServiceImpl() {
		this(PersistenceServiceFactory.LOCATIONDAO.getInstance(LocationDao.class));
	}
	
	
	@Override
	public AreaMessageBean getInitialArea() {
		
		return Optional.of(this.initialArea)
						.map(areaId -> this.areas.get(areaId))
						.map(areaData -> MapperService.instance.toMessageBean(areaData))
						.orElseThrow(() -> new EngineException(EngineExceptionType.INITIAL_AREA_NOT_FOUND,this.initialArea));
	}
}
