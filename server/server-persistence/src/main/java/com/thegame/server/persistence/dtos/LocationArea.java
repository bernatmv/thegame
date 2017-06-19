package com.thegame.server.persistence.dtos;

import java.util.List;
import java.util.Map;
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
public class LocationArea{

	private String id;
	private String title;
	private String description;
	@Singular
  	private Map<String,String> exits;
	@Singular
  	private List<LocationItem> items;
	@Singular
  	private List<String> enemies;
}
