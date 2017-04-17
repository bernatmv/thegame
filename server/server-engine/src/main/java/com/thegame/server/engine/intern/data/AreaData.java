package com.thegame.server.engine.intern.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaData {
	
	private String id;
	private String title;
	private String shortDescription;
	private String description;
}
