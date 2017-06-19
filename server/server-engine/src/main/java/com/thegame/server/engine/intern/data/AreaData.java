package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.messages.IsMessageBean;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

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

	@Singular
	private Map<String,String> exits;
	@Singular
	private List<ItemData> items;
	@Singular
	private List<String> players;
	@Singular
	private List<String> enemies;
	@Singular
	private List<Consumer<IsMessageBean>> listeners;

	
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
