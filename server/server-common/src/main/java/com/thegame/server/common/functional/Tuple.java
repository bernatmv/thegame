package com.thegame.server.common.functional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 * @param <TYPE1>
 * @param <TYPE2>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tuple<TYPE1,TYPE2> {
	
	private TYPE1 object1;
	private TYPE2 object2;
}
