package com.thegame.server.engine.messages.output;

import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.enums.Gender;
import java.util.Set;
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
public class ItemMessageBean implements IsMessageBean{

	@NonNull
	private String id;
	
	private String name;
	private String description;
	private boolean alive;
	private Gender gender;
	private String singular;
	private String plural;
	@Singular
	private Set<String> chatters;
}
