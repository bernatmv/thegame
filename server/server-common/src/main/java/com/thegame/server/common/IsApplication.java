package com.thegame.server.common;

import java.util.Collection;
import java.util.Collections;
import java.util.EventListener;
import java.util.Set;

/**
 * @author afarre
 */
public interface IsApplication {

	public String getName();
	public Set<Class<?>> getEndpoints();

	public default String getContext(){
		return "/";
	}
	public default String getWelcomePage() {
		return "index.html";
	}
	public default String getResources(){
		return "site";
	}
	public default Collection<Class<? extends EventListener>> getListeners(){
		return Collections.emptySet();
	};
}
