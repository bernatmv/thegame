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
	private RaceStatImport health;
	private RaceStatImport magic;
	private RaceStatImport stamina;
	@Singular("chat")
	private Set<String> chatter;
}
