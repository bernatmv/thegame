package com.thegame.server.persistence;

import com.thegame.server.common.persistence.PersistenceDao;
import com.thegame.server.persistence.entities.Item;
import java.util.List;

/**
 *
 * @author afarre
 */
public interface ResourceDao extends PersistenceDao{

	public void saveItem(final Item _item);
	public List<Item> loadItems();
}
