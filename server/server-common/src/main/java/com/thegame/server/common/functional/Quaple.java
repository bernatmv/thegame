package com.thegame.server.common.functional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 * @param <TYPE1>
 * @param <TYPE2>
 * @param <TYPE3>
 * @param <TYPE4>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quaple<TYPE1,TYPE2,TYPE3,TYPE4> {
	
	private TYPE1 object1;
	private TYPE2 object2;
	private TYPE3 object3;
	private TYPE4 object4;
}
