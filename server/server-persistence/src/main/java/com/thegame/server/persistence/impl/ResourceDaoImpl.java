package com.thegame.server.persistence.impl;

import com.thegame.server.persistence.ResourceDao;
import com.thegame.server.persistence.entities.Item;
import com.thegame.server.persistence.exceptions.PersistenceException;
import com.thegame.server.persistence.exceptions.PersistenceExceptionType;
import com.thegame.server.persistence.support.JPASessionManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;

/**
 * @author afarre
 */
public class ResourceDaoImpl implements ResourceDao{

	@Override
	public void saveItem(final Item _item) {
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				Optional.ofNullable(entityManager.find(Item.class, _item.getId()))
					.ifPresent(item -> {throw new PersistenceException(PersistenceExceptionType.ITEM_CREATION_ALREADY_EXIST,item.getId());});
				entityManager.persist(_item);
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(PersistenceExceptionType.ITEM_CREATION_FAIL,_item);
			}
		}
	}
	
	@Override
	public List<Item> loadItems() {

		List<Item> reply;
		
		try(JPASessionManager entityManager=createEntityManager()){
			try{
				entityManager.getTransaction().begin();
				final TypedQuery<Item> query=entityManager.createQuery("select selected from Item as selected",Item.class);
				reply=Optional.ofNullable(query.getResultList())
						.orElse(Collections.emptyList());
				entityManager.getTransaction().commit();
			}catch(PersistenceException e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw e;
			}catch(Throwable e){
				if(entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new PersistenceException(PersistenceExceptionType.ITEM_LOAD_FAIL);
			}
		}
		
		return reply;
	}
}
