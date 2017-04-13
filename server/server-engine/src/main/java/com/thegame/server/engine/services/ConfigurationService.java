package com.thegame.server.engine.services;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author E103880
 */
public interface ConfigurationService {
	
	public Map<String,String> get();
	public Map<String,String> update(Map<String,String> _configuration);

	public void registerListener(Consumer<Map<String,String>> _listener);
}
