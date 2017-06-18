package com.thegame.server.persistence.dtos;

import java.util.Set;
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
public class LocationItem {

	private String id;
	private String name;
	private char gender;
	private boolean alive;
	private String description;
	private String plural;
	private String singular;
	@Singular("chat")
  	private Set<String> chatter;
}
