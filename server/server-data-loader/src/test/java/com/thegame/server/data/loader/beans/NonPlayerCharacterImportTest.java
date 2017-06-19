package com.thegame.server.data.loader.beans;

import com.thegame.server.engine.messages.common.Gender;
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
									.gender(Gender.herm)
									.level(3)
									.health(CharacterStatImport.builder()
														.max(20)
														.current(15)
														.build())
									.magic(CharacterStatImport.builder()
														.max(5)
														.current(2)
														.build())
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
