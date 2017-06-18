package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.services.impl.PlayerServiceImpl;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author afarre
 */
public class PlayerServiceTest{
	
	private PlayerService instance;
	private Queue<IsMessageBean> messages;
	private Consumer<IsMessageBean> playerChannel;
	
	@Before
	public void setUp(){
		this.instance=new PlayerServiceImpl();
		this.messages=new LinkedList<>();
		this.playerChannel=messageBean -> this.messages.add(messageBean);
	}

	private PlayerMessageBean createPlayer(final String _name){
		return PlayerMessageBean.builder()
							.name(_name)
							.area("startup-area")
							.channel(this.playerChannel)
							.build();
	}

	/**
	 * Test of registerPlayer method, of class PlayerService.
	 */
	@Test
	public void testRegisterPlayer() {
		System.out.println("registerPlayer");
		PlayerMessageBean _player=createPlayer("test");
		PlayerMessageBean expResult=createPlayer("test");
		PlayerMessageBean result=instance.registerPlayer(_player,"startup-area");
		Assert.assertEquals(expResult, result);
		Assert.assertEquals(0,this.messages.size());
	}
	/**
	 * Test of registerPlayer method, of class PlayerService.
	 */
	@Test(expected=EngineException.class)
	public void testRegisterPlayer_alreadyRegistered() {
	
		try{
			System.out.println("registerPlayer_alreadyRegistered");
			PlayerMessageBean _player=createPlayer("test");
			PlayerMessageBean _player2=createPlayer("test");
			instance.registerPlayer(_player,"startup-area");
			Assert.assertEquals(0,this.messages.size());
			instance.registerPlayer(_player2,"startup-area");
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.PLAYER_ALREADY_REGISTERED,e.getExceptionType());
			throw e;
		}
	}
	
	/**
	 * Test of existPlayer method, of class PlayerService.
	 */
	@Test
	public void testExistPlayer_true() {
		System.out.println("existPlayer_true");
		PlayerMessageBean _player=createPlayer("test");
		instance.registerPlayer(_player,"startup-area");
		Assert.assertEquals(0,this.messages.size());
		Assert.assertTrue(instance.existPlayer("test"));
	}

	/**
	 * Test of existPlayer method, of class PlayerService.
	 */
	@Test
	public void testExistPlayer_false() {
		System.out.println("existPlayer_false");
		PlayerMessageBean _player=createPlayer("test");
		instance.registerPlayer(_player,"startup-area");
		Assert.assertEquals(0,this.messages.size());
		Assert.assertFalse(instance.existPlayer("test2"));
	}

	/**
	 * Test of getPlayer method, of class PlayerService.
	 */
	@Test
	public void testGetPlayer() {
		System.out.println("getPlayer");
		PlayerMessageBean _player=createPlayer("test");
		PlayerMessageBean expResult=createPlayer("test");
		instance.registerPlayer(_player,"startup-area");
		PlayerMessageBean result=instance.getPlayer("test");
		Assert.assertEquals(expResult, result);
	}

	/**
	 * Test of getPlayer method, of class PlayerService.
	 */
	@Test(expected=EngineException.class)
	public void testGetPlayer_notexist() {
		try{
			System.out.println("getPlayer_notexist");
			PlayerMessageBean _player=createPlayer("test");
			instance.getPlayer("test2");
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.PLAYER_NOT_REGISTERED,e.getExceptionType());
			throw e;
		}
	}

	/**
	 * Test of unregisterPlayer method, of class PlayerService.
	 */
	@Test
	public void testUnregisterPlayer() {
		System.out.println("unregisterPlayer");
		PlayerMessageBean _player=createPlayer("test");
		PlayerMessageBean expResult=createPlayer("test");
		PlayerMessageBean result=instance.registerPlayer(_player,"startup-area");
		Assert.assertEquals(expResult, result);
		Assert.assertEquals(0,this.messages.size());
		Assert.assertTrue(instance.existPlayer("test"));
		result=instance.unregisterPlayer("test");
		Assert.assertEquals(expResult, result);
		Assert.assertFalse(instance.existPlayer("test"));
		Assert.assertEquals(0,this.messages.size());
	}

	/**
	 * Test of unregisterPlayer method, of class PlayerService.
	 */
	@Test(expected=EngineException.class)
	public void testUnregisterPlayer_notexist() {
		try{
			System.out.println("unregisterPlayer_notexist");
			PlayerMessageBean _player=createPlayer("test");
			PlayerMessageBean expResult=createPlayer("test");
			PlayerMessageBean result=instance.registerPlayer(_player,"startup-area");
			Assert.assertEquals(expResult, result);
			Assert.assertEquals(0,this.messages.size());
			Assert.assertTrue(instance.existPlayer("test"));
			instance.unregisterPlayer("test2");
		}catch(EngineException e){
			Assert.assertEquals(EngineExceptionType.PLAYER_NOT_REGISTERED,e.getExceptionType());
			throw e;
		}
	}

	/**
	 * Test of listPlayers method, of class PlayerService.
	 */
	@Test
	public void testListPlayers() {
		System.out.println("listPlayers");
		PlayerMessageBean _player=createPlayer("test");
		PlayerMessageBean _player2=createPlayer("test2");
		instance.registerPlayer(_player,"startup-area");
		instance.registerPlayer(_player2,"startup-area");
		Assert.assertEquals(0,this.messages.size());
		Collection<PlayerMessageBean> expResult=Stream.of(_player,_player2)
													.collect(Collectors.toSet());
		Collection<PlayerMessageBean> result=instance.listPlayers();
		Assert.assertEquals(0,this.messages.size());
		Assert.assertEquals(2,result.size());
		Assert.assertEquals(expResult, result);
	}	
	
	/**
	 * Test of movePlayer method, of class PlayerService.
	 */
	@Test
	public void testMovePlayer() {
		System.out.println("movePlayer");
		PlayerMessageBean _player=createPlayer("test");
		PlayerMessageBean result=instance.registerPlayer(_player,"startup-area");
		Assert.assertEquals(_player, result);
		Assert.assertEquals(0,this.messages.size());
		Assert.assertTrue(instance.existPlayer("test"));

		PlayerMessageBean expResult=createPlayer("test");
		expResult.setArea("go-area");
		result=instance.movePlayer(_player,"go-area");
		Assert.assertEquals(expResult, result);
		Assert.assertTrue(instance.existPlayer("test"));
		Assert.assertEquals(0,this.messages.size());
	}
}
