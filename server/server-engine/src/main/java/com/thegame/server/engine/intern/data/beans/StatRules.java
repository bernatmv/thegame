package com.thegame.server.engine.intern.data.beans;

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
public class StatRules{
	
	private int base;
	private float incrementPerLevel;
}
