package com.thegame.server.engine.messages;

import com.thegame.server.engine.messages.enums.Gender;
import com.thegame.server.engine.messages.output.ItemMessageBean;
import java.util.Optional;

/**
 *
 * @author afarre
 */
public class ItemMessageBeanTest extends BaseMessageBeanTest{
	
	public ItemMessageBeanTest() {
		super("item");
	}
	@Override
	public Optional<IsMessageBean> getBean(String _jsonFile) {
		
		Optional<IsMessageBean> reply=Optional.empty();
		
		if("item.json".equals(_jsonFile)){
			reply=Optional.of(ItemMessageBean.builder()
									.id("chicken-001")
									.description("itemChickenDescription")
									.alive(true)
									.gender(Gender.male)
									.singular("itemChickenSingular")
									.plural("itemChickenPlural")
									.chatter("cooooo!")
									.build());
		} 
 
		return reply;
	}
}
