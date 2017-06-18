package com.thegame.server.engine.messages.output;

import com.thegame.server.engine.messages.common.ItemBean;
import com.thegame.server.engine.messages.IsMessageBean;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaMessageBean implements IsMessageBean{

	@NonNull
	private String id;
	private String title;
	private String description;
	
	@Singular
	private Map<String,String> exits;
	@Singular
	private List<String> players;
	@Singular
	private List<NonPlayerMessageBean> enemies;
	@Singular
	private List<ItemBean> items;
}
