package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.ConfigurationService;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author E103880
 */
public class ConfigurationServiceImpl implements ConfigurationService{

	private static final LogStream logger=LogStream.getLogger(EngineServiceFactory.class);
	
	private final Set<Consumer<Map<String, String>>> listeners;
	private final Map<String, String> configuration;
	
	public ConfigurationServiceImpl(){
		this.listeners=new HashSet<>();
		this.configuration=new HashMap<>(recoverConfiguration());
		if(this.configuration.isEmpty()){
			this.configuration.putAll(Configuration.getDefaultConfiguration());
		}
		persistConfiguration();
	}


	private Map<String, String> recoverConfiguration() {
		
		Map<String, String> reply=Collections.emptyMap();
		
		//TODO
		
		return reply;
	}
	private void persistConfiguration() {

		//TODO
	}
	
	@Override
	public Map<String, String> get() {
		return this.configuration;
	}

	@Override
	public Map<String, String> update(Map<String, String> _configuration) {
		logger.trace("configuration::service::update::begin");
		this.configuration.putAll(_configuration);
		logger.trace("configuration::service::update::notify-listeners::begin");
		final Set<Consumer<Map<String, String>>> frozenListeners=new HashSet<>(this.listeners);
		frozenListeners.stream()
				.peek(consumer -> logger.info("configuration::service::update::notifying::{}",consumer))
				.forEach(consumer -> {
					try{
						consumer.accept(Collections.unmodifiableMap(this.configuration));
					}catch(Throwable e){
						logger.error("configuration::service::update::notifying::{}::failed",consumer,e);
					}
				});
		logger.debug("configuration::service::update::notify-listeners::end");
		logger.trace("configuration::service::update::persist::begin");
		persistConfiguration();
		logger.debug("configuration::service::update::persist::end");
		logger.debug("configuration::service::update::end");
		
		return this.configuration;
	}

	
	@Override
	public void registerListener(Consumer<Map<String, String>> _listener) {
		this.listeners.add(_listener);
	}	
}
