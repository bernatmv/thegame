package com.thegame.server.persistence;

import com.thegame.server.persistence.entities.Item;
import com.thegame.server.persistence.support.JPAPersistenceDao;
import java.util.List;

/**
 *
 * @author afarre
 */
public interface ResourceDao extends JPAPersistenceDao{

	public void saveItem(final Item _item);
	public List<Item> loadItems();
}
