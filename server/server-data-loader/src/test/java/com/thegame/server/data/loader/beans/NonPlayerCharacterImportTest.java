package com.thegame.server.data.loader.beans;

import java.util.Optional;

/**
 *
 * @author afarre
 */
public class NonPlayerCharacterImportTest extends BaseImportTest{
	
	public NonPlayerCharacterImportTest() {
		super("npc");
	}
	@Override
	public Optional<Object> getBean(String _jsonFile) {
		
		Optional<Object> reply=Optional.empty();
		
		if("npc.json".equals(_jsonFile)){
			reply=Optional.of(NonPlayerCharacterImport.builder()
									.id("goblin-01")
									.name("manolo")
									.race("goblin")
									.level(3)
									.health(20)
									.currentHealth(15)
									.magic(5)
									.currentMagic(2)
									.chat("Vicavorausan or deaavh!")
									.chat("Inavruderuk, drepa avhem!")
									.chat("Proavecav avhe milambak!")
									.chat("Nalkroro ayh lat doaumn avhiuk avo uuk?")
									.chat("Senav avhem avo avheir goddeukuk")
									.build());
		} 
 
		return reply;
	}
}
