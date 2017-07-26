package com.thegame.server.persistence;

import com.thegame.server.persistence.impl.LocationDaoImpl;
import com.thegame.server.persistence.impl.CharacterDaoImpl;
import com.thegame.server.persistence.impl.ResourceDaoImpl;
import java.lang.reflect.InvocationTargetException;

/**
 * @author e103880
 */
public enum PersistenceServiceFactory {
	
	LOCATIONDAO(LocationDaoImpl.class),
	CHARACTERDAO(CharacterDaoImpl.class),
	RESOURCEDAO(ResourceDaoImpl.class),
	;
	
	private final Class<?> implementation;
	
	
	PersistenceServiceFactory(final Class<?> _implementation){
		this.implementation=_implementation;
	}
	

	public <T> T getInstance(final Class<T> _class){
		return (T)getInstance();
	}
	public Object getInstance(){
		
		Object reply=null;
		
		try{
			reply=this.implementation.getConstructor().newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Unable to instance service "+this.name(),e);
		}
				
		return reply;
	}
	
	public static final void init(){

		for(PersistenceUnit persistenceUnit:PersistenceUnit.values()){
			persistenceUnit.init();
		}
	}
	public static final void close(){
		
		for(PersistenceUnit persistenceUnit:PersistenceUnit.values()){
			persistenceUnit.close();
		}
	}
}
