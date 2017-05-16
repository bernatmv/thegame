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
public class RaceImport{

	@NonNull
	private String id;
	private String singular;
	private String plural;
	private int healthBase;
	private double healthPerLevel;
	private int magicBase;
	private double magicPerLevel;
	@Singular
	private Set<String> chatters;
}
