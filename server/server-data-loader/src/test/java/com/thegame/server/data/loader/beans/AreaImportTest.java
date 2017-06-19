package com.thegame.server.data.loader.beans;

import com.thegame.server.engine.messages.common.Gender;
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
		}else if("area3.json".equals(_jsonFile)){
			reply=Optional.of(AreaImport.builder()
								.id("beta-room-002")
								.description("roomBeta001Description")
								.title("roomBeta001Title")
								.exit("north","beta-room-002")
								.exit("south","beta-room-002")
								.enemy(NonPlayerCharacterImport.builder()
																.id("goblin-001")
																.name("Big G")
																.gender(Gender.female)
																.race("goblin")
																.level(2)
																.health(CharacterStatImport.builder()
																	.current(25)
																	.max(25)
																	.build())
																.build())
								.enemy(NonPlayerCharacterImport.builder()
																.id("goblin-002")
																.name("Little G")
																.gender(Gender.male)
																.race("goblin")
																.level(1)
																.health(CharacterStatImport.builder()
																	.current(20)
																	.max(20)
																	.build())
																.build())
								.build());
		} 

		return reply;
	}
}
