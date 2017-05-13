package com.thegame.server.engine;

import org.junit.Test;

/**
 *
 * @author afarre
 */
public class DatabaseInitializerTest {
	
	public DatabaseInitializerTest() {
	}


	/**
	 * Test of initialize method, of class DatabaseInitializer.
	 */
	@Test
	public void testInitialize() {
		System.out.println("initialize");
		DatabaseInitializer instance=DatabaseInitializer.getInstance();
		instance.initialize();
	}
	
}
