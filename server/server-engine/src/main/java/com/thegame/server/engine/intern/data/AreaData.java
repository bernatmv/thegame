package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.messages.IsMessageBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaData {
	
	private String id;
	private String title;
	private String description;
	@Builder.Default
	private Map<String,String> exits=new HashMap<>();
	@Builder.Default
	private List<String> players=new CopyOnWriteArrayList<>();
	@Builder.Default
	private List<Consumer<IsMessageBean>> listeners=new CopyOnWriteArrayList<>();
	
	public AreaData addPlayer(final String _player){
		this.players.add(_player);
		return this;
	}
	public AreaData removePlayer(final String _player){
		this.players.remove(_player);
		return this;
	}

	public AreaData addListener(final Consumer<IsMessageBean> _listener){
		this.listeners.add(_listener);
		return this;
	}
	public AreaData removeListener(final Consumer<IsMessageBean> _listener){
		this.listeners.remove(_listener);
		return this;
	}
}
