package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.intern.data.beans.Stat;
import com.thegame.server.engine.messages.common.Gender;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NonPlayerData{

	private String id;
	private String name;
	private Gender gender;
	private String race;
	private String singular;
	private String plural;
	private int level;
	private Stat health;
	private Stat magic;
	private Stat stamina;
	@Singular("chat")
	private Set<String> chatter;
}
