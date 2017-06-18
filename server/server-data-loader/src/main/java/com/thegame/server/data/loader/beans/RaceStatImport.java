package com.thegame.server.data.loader.beans;

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
public class RaceStatImport{
	
	private int base;
	private float incrementPerLevel;
}
