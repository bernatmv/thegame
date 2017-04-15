package com.thegame.server.persistence;

import com.thegame.server.persistence.support.PersistenceUnitsFactory;
import com.thegame.server.persistence.support.JPAPersistenceDao;
import com.thegame.server.persistence.impl.LocationDaoImpl;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.EntityManagerFactory;

/**
 * @author e103880
 */
public enum PersistenceServiceFactory {
	
	LOCATIONDAO(PersistenceUnitsFactory.THEGAME,LocationDaoImpl.class),
	;
	
	private final Optional<PersistenceUnitsFactory> persistenceUnit;
	private final Class<?> implementation;
	
	
	PersistenceServiceFactory(final PersistenceUnitsFactory _persistenceUnit,final Class<?> _implementation){
		this.persistenceUnit=Optional.ofNullable(_persistenceUnit);
		this.implementation=_implementation;
	}
	
	
	public String getPersistenceUnit() {
		return this.persistenceUnit
				.map(unit -> unit.getPersistenceUnit())
				.orElse(null);
	}

	public EntityManagerFactory createEntityManagerFactory(final Map<String,String> _properties){
		return this.persistenceUnit
				.map(unit -> unit.createEntityManagerFactory(_properties))
				.orElse(null);
	}
	public EntityManagerFactory getEntityManagerFactory(){
		return this.persistenceUnit
				.map(unit -> unit.getEntityManagerFactory())
				.orElse(null);
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

	public static void init(){
		for(PersistenceServiceFactory persistenceFactory:PersistenceServiceFactory.values()){
			persistenceFactory.persistenceUnit
					.ifPresent(unit -> unit.init());
		}
	}
	public static void close(){
		for(PersistenceServiceFactory persistenceFactory:PersistenceServiceFactory.values()){
			persistenceFactory.persistenceUnit
					.ifPresent(unit -> unit.close());
		}
	}
	public static Optional<PersistenceServiceFactory> forImplementation(final JPAPersistenceDao _persistenceDao){
		return Stream.of(PersistenceServiceFactory.values())
				.filter(serviceFactory -> serviceFactory.implementation.equals(_persistenceDao.getClass()))
				.findFirst();
	}
}
