package com.thegame.server.engine.messages;

import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author afarre
 */
public class AreaMessageBeanTest {
	
	private Genson genson;

	@Before
	public void setup(){
		this.genson=new GensonBuilder()
						//.useRuntimeType(true)
						.create();				
	}
	
	/**
	 * Test of json-serialize method, of class AreaMessageBean.
	 */
	@Test
	public void testJSONSerialize() {
		System.out.println("json-serialize");
		String expResult="{\"description\":\"roomBeta001Description\",\"exits\":{\"north\":\"beta-room-002\"},\"id\":\"beta-room-001\",\"shortDescription\":\"roomBeta001ShortDescription\",\"title\":\"roomBeta001Title\"}";

		AreaMessageBean instance=AreaMessageBean.builder()
												.id("beta-room-001")
												.shortDescription("roomBeta001ShortDescription")
												.description("roomBeta001Description")
												.title("roomBeta001Title")
												.exit("north","beta-room-002")
												.build();
		String result=this.genson.serialize(instance);
		Assert.assertEquals(expResult,result);

		expResult="{\"description\":\"roomBeta001Description\",\"exits\":{\"north\":\"beta-room-002\",\"south\":\"beta-room-002\"},\"id\":\"beta-room-001\",\"shortDescription\":\"roomBeta001ShortDescription\",\"title\":\"roomBeta001Title\"}";
		instance=AreaMessageBean.builder()
								.id("beta-room-001")
								.shortDescription("roomBeta001ShortDescription")
								.description("roomBeta001Description")
								.title("roomBeta001Title")
								.exit("north","beta-room-002")
								.exit("south","beta-room-002")
								.build();
		result=this.genson.serialize(instance);
		Assert.assertEquals(expResult,result);
	}

	/**
	 * Test of json-deserialize method, of class AreaMessageBean.
	 */
	@Test
	public void testJSONDeserialize() {
		System.out.println("json-deserialize");
		String instance="{\"id\":\"beta-room-001\",\"title\":\"roomBeta001Title\",\"shortDescription\":\"roomBeta001ShortDescription\",\"description\":\"roomBeta001Description\",\"exits\":{\"north\":\"beta-room-002\"}}";
		AreaMessageBean expResult=AreaMessageBean.builder()
												.id("beta-room-001")
												.shortDescription("roomBeta001ShortDescription")
												.description("roomBeta001Description")
												.title("roomBeta001Title")
												.exit("north","beta-room-002")
												.build();
		AreaMessageBean result=this.genson.deserialize(instance,AreaMessageBean.class);
		Assert.assertEquals(expResult,result);

		instance="{\"id\":\"beta-room-001\",\"title\":\"roomBeta001Title\",\"shortDescription\":\"roomBeta001ShortDescription\",\"description\":\"roomBeta001Description\",\"exits\":{\"north\":\"beta-room-002\",\"south\":\"beta-room-002\"}}";
		expResult=AreaMessageBean.builder()
								.id("beta-room-001")
								.shortDescription("roomBeta001ShortDescription")
								.description("roomBeta001Description")
								.title("roomBeta001Title")
								.exit("north","beta-room-002")
								.exit("south","beta-room-002")
								.build();
		result=this.genson.deserialize(instance,AreaMessageBean.class);
		Assert.assertEquals(expResult,result);
	}
}
