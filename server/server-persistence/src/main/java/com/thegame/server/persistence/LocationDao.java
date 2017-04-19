package com.thegame.server.persistence;

import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.support.JPAPersistenceDao;
import java.util.List;

/**
 *
 * @author afarre
 */
public interface LocationDao extends JPAPersistenceDao{

	public void saveArea(final Area _room);
	public List<Area> loadAreas();
}
