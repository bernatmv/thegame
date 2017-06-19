package com.thegame.server.data.loader.beans;

import com.thegame.server.engine.messages.common.Gender;
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
public class ItemImport{

	@NonNull
	private String id;
	private String description;
	private boolean alive;
	private Gender gender;
	private String singular;
	private String plural;
	@Singular("chat")
	private Set<String> chatter;
}
