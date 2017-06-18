package com.thegame.server.persistence;

import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.dtos.LocationArea;
import com.thegame.server.persistence.support.JPAPersistenceDao;
import java.util.List;

/**
 *
 * @author afarre
 */
public interface LocationDao extends JPAPersistenceDao{

	public void saveArea(final Area _area);
	public void saveAreaExit(final AreaExit _areaExit);
	public List<LocationArea> loadAreas();
}
