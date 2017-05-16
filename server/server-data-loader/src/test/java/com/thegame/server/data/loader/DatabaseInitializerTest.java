package com.thegame.server.data.loader;

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
		DataLoader instance=DataLoader.getInstance();
		instance.initialize();
	}
	
}
