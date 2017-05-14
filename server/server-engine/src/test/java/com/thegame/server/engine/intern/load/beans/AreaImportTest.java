package com.thegame.server.engine.intern.load.beans;

import java.util.Optional;

/**
 *
 * @author afarre
 */
public class AreaImportTest extends BaseImportTest{

	public AreaImportTest() {
		super("area");
	}
	@Override
	public Optional<Object> getBean(String _jsonFile) {
		
		Optional<Object> reply=Optional.empty();
		
		if("area1.json".equals(_jsonFile)){
			reply=Optional.of(AreaImport.builder()
									.id("beta-room-001")
									.description("roomBeta001Description")
									.title("roomBeta001Title")
									.exit("north","beta-room-002")
									.item(ItemInstanceImport.builder().id("chicken-001").name("Pascual").build())
									.build());
		}else if("area2.json".equals(_jsonFile)){
			reply=Optional.of(AreaImport.builder()
								.id("beta-room-002")
								.description("roomBeta001Description")
								.title("roomBeta001Title")
								.exit("north","beta-room-002")
								.exit("south","beta-room-002")
								.build());
		} 
 
		return reply;
	}
}
