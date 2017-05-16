package com.thegame.server.data.loader.beans;

import com.thegame.server.engine.messages.common.Gender;
import java.util.Optional;

/**
 *
 * @author afarre
 */
public class ItemImportTest extends BaseImportTest{
	
	public ItemImportTest() {
		super("item");
	}
	@Override
	public Optional<Object> getBean(String _jsonFile) {
		
		Optional<Object> reply=Optional.empty();
		
		if("item.json".equals(_jsonFile)){
			reply=Optional.of(ItemImport.builder()
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
