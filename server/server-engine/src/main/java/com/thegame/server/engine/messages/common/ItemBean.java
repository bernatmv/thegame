package com.thegame.server.engine.messages.common;

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
public class ItemBean{

	@NonNull
	private String id;
	
	private String name;
	private String description;
	private boolean alive;
	private Gender gender;
	private String singular;
	private String plural;
	@Singular("chat")
	private Set<String> chatter;
}
