package com.thegame.server.persistence;

import com.thegame.server.common.persistence.PersistenceDao;
import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.dtos.LocationArea;
import java.util.List;

/**
 *
 * @author afarre
 */
public interface LocationDao extends PersistenceDao{

	public void saveArea(final Area _area);
	public void saveAreaExit(final AreaExit _areaExit);
	public List<LocationArea> loadAreas();
}
