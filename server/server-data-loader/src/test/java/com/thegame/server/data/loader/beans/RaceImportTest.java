package com.thegame.server.data.loader.beans;

import java.util.Optional;

/**
 *
 * @author afarre
 */
public class RaceImportTest extends BaseImportTest{
	
	public RaceImportTest() {
		super("race");
	}
	@Override
	public Optional<Object> getBean(String _jsonFile) {
		
		Optional<Object> reply=Optional.empty();
		
		if("race.json".equals(_jsonFile)){
			reply=Optional.of(RaceImport.builder()
									.id("goblin")
									.singular("goblin")
									.plural("goblins")
									.health(RaceStatImport.builder().base(15).incrementPerLevel(3.0f).build())
									.magic(RaceStatImport.builder().base(0).incrementPerLevel(0.4f).build())
									.stamina(RaceStatImport.builder().base(10).incrementPerLevel(1).build())
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
