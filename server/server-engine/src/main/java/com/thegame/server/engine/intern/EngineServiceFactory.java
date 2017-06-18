package com.thegame.server.engine.intern;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.services.DataClonerService;
import com.thegame.server.engine.intern.services.DataMapperService;
import com.thegame.server.engine.intern.services.impl.ChatServiceImpl;
import com.thegame.server.engine.intern.services.impl.ConfigurationServiceImpl;
import com.thegame.server.engine.intern.services.impl.LocationServiceImpl;
import com.thegame.server.engine.intern.services.impl.PlayerServiceImpl;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Stream;
import com.thegame.server.engine.intern.services.MessageMapperService;
import com.thegame.server.engine.intern.services.impl.CharacterServiceImpl;
import com.thegame.server.engine.intern.services.impl.NonPlayerServiceImpl;

/**
 * @author e103880
 */
public enum EngineServiceFactory {
	
	CONFIGURATION(ConfigurationServiceImpl.class,true),
	PLAYER(PlayerServiceImpl.class,true),
	LOCATION(LocationServiceImpl.class,true),
	CHAT(ChatServiceImpl.class,true),
	CHARACTER(CharacterServiceImpl.class,true),
	DATAMAPPER(DataMapperService.instance.getClass(),true),
	DATACLONER(DataClonerService.instance.getClass(),true),
	MESSAGEMAPPER(MessageMapperService.instance.getClass(),true),
	NONPLAYER(NonPlayerServiceImpl.class,true),
	;
	
	private static final LogStream logger=LogStream.getLogger(EngineServiceFactory.class);
	
	private Object instance;
	private final Class<?> implementation;
	private final boolean singleton;
	
	EngineServiceFactory(final Class<?> _implementation){
		this(_implementation,false);
	}
	EngineServiceFactory(final Class<?> _implementation,final boolean _singleton){
		this.implementation=_implementation;
		this.singleton=_singleton;
		this.instance=null;
	}
	
	protected boolean assignableArray(final Class[] _destiny,final Object[] _origin){
	
		boolean reply=true;
	
		for(int ic1=0;ic1<_destiny.length;ic1++){
			reply&=_destiny[ic1].isAssignableFrom(_origin[ic1].getClass());
		}
		
		return reply;
	}
	
	protected Object instantiate(final Object... _attributes){
		
		Object reply=null;
		
		try{
			reply=Arrays.stream(this.implementation.getConstructors())
					.filter(selectedConstructor -> selectedConstructor.getParameterTypes().length==_attributes.length)
					.filter(selectedConstructor -> assignableArray(selectedConstructor.getParameterTypes(),_attributes))
					.findAny()
					.orElseThrow(() -> new EngineException(EngineExceptionType.UNABLE_TO_FIND_SUITABLE_CONSTRUCTOR,this.implementation,Arrays.toString(_attributes),name()))
					.newInstance(_attributes);
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new EngineException(e,EngineExceptionType.UNABLE_TO_INSTANTIATE_SERVICE,name(),this.implementation,Arrays.toString(_attributes));
		}
		
		return reply;
	}
	
	public String getServiceName(){
		return this.name().toLowerCase();
	}
	public Class<?> getImplementation() {
		return implementation;
	}
	public boolean isSingleton() {
		return singleton;
	}
	
	public <T> T getInstance(final Class<T> _class,final Object... _attributes){
		
		Object reply;
		
		if(this.singleton){
			if((reply=this.instance)==null){
				synchronized(EngineServiceFactory.class){
					if((reply=this.instance)==null){
						this.instance=instantiate(_attributes);
						reply=this.instance;
					}
				}
			}
		}else{
			reply=instantiate(_attributes);
		}
				
		return (T)reply;
	}
	
	public static Stream<Object> getSingletons(){
		return Arrays.stream(EngineServiceFactory.values())
				.filter(service -> service.isSingleton())
				.filter(service -> service.instance!=null)
				.map(service -> service.instance);
	}
}
