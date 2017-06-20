package com.thegame.server.persistence.impl;

import com.thegame.server.persistence.ResourceDao;
import com.thegame.server.persistence.entities.Item;
import com.thegame.server.persistence.entities.Race;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author afarre
 */
public class ResourceDaoImpl implements ResourceDao{

	@Override
	public void saveItem(final Item _item) {
		
		transactional(entityManager -> {
				Optional.ofNullable(entityManager.find(Race.class, _item.getId()))
					.ifPresent(item -> {throw new PersistenceException(PersistenceExceptionType.ITEM_CREATION_ALREADY_EXIST,item.getId());});
				entityManager.persist(_item);
			});
	}
	
	@Override
	public List<Item> loadItems() {

		List<Item> reply;
		
		reply=transactional(
					entityManager -> Optional.ofNullable(
								entityManager.createQuery("select selected from Item as selected",Item.class)
												.getResultList())
													.orElse(Collections.emptyList())
													//Hibernate can not recover eagerly more than one bag, we perform the recovery manuallly
													.stream()
													.collect(Collectors.toList())
					,List.class)
				.orElse(Collections.emptyList());

		return reply;
	}
}
