package com.thegame.server.data.loader.beans;

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
	@NonNull
	private String race;
	@NonNull
	private int level;
	private int health;
	private int currentHealth;
	private int magic;
	private int currentMagic;
	@Singular
	private Set<String> chatters;
}
