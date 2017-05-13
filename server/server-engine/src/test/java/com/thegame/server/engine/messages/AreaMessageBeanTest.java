package com.thegame.server.engine.messages;

import com.thegame.server.engine.messages.output.AreaMessageBean;
import java.util.Optional;

/**
 *
 * @author afarre
 */
public class AreaMessageBeanTest extends BaseMessageBeanTest{

	public AreaMessageBeanTest() {
		super("area");
	}
	@Override
	public Optional<IsMessageBean> getBean(String _jsonFile) {
		
		Optional<IsMessageBean> reply=Optional.empty();
		
		if("area1.json".equals(_jsonFile)){
			reply=Optional.of(AreaMessageBean.builder()
									.id("beta-room-001")
									.description("roomBeta001Description")
									.title("roomBeta001Title")
									.exit("north","beta-room-002")
									.build());
		}else if("area2.json".equals(_jsonFile)){
			reply=Optional.of(AreaMessageBean.builder()
								.id("beta-room-001")
								.description("roomBeta001Description")
								.title("roomBeta001Title")
								.exit("north","beta-room-002")
								.exit("south","beta-room-002")
								.build());
		} 
 
		return reply;
	}
}
