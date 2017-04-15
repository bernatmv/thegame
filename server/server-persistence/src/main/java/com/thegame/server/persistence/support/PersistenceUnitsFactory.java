package com.thegame.server.persistence.support;

import java.util.Collections;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author e103880
 */
public enum PersistenceUnitsFactory {
	
	THEGAME("the-game"),
	;
	
	private final String persistenceUnit;
	private final EntityManagerFactory factory;
	
	
	PersistenceUnitsFactory(final String _persistenceUnit){
		this.persistenceUnit=_persistenceUnit;
		this.factory=createEntityManagerFactory(Collections.emptyMap());
	}
	
	
	public String getPersistenceUnit() {
		return persistenceUnit;
	}

	public EntityManagerFactory createEntityManagerFactory(final Map<String,String> _properties){
		return Persistence.createEntityManagerFactory(this.persistenceUnit,_properties);
	}
	public EntityManagerFactory getEntityManagerFactory(){
		return this.factory;
	}

	public void init(){
		this.factory.isOpen();
	}
	public void close(){
		if(this.factory.isOpen()){
			this.factory.close();
		}
	}
}
