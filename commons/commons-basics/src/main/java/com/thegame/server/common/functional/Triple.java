package com.thegame.server.common.functional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 * @param <TYPE1>
 * @param <TYPE2>
 * @param <TYPE3>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Triple<TYPE1,TYPE2,TYPE3> {
	
	private TYPE1 object1;
	private TYPE2 object2;
	private TYPE3 object3;
}
