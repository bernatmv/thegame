package com.thegame.server.account.persistence;

import com.thegame.server.account.persistence.impl.UserDaoImpl;
import java.lang.reflect.InvocationTargetException;

/**
 * @author e103880
 */
public enum PersistenceServiceFactory {
	
	USERDAO(UserDaoImpl.class),
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
