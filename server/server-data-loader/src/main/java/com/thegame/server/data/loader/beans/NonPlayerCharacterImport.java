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
public class NonPlayerCharacterImport{

	@NonNull
	private String id;
	private String name;
	private Gender gender;
	private String area;
	@NonNull
	private String race;
	private int level;
	private CharacterStatImport health;
	private CharacterStatImport magic;
	private CharacterStatImport stamina;
	@Singular("chat")
	private Set<String> chatter;
}
