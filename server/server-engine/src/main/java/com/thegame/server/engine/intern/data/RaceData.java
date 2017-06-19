package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.intern.data.beans.StatRules;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceData{

	private String id;
	private String singular;
	private String plural;
	private StatRules health;
	private StatRules magic;
	private StatRules stamina;
	private Set<String> chatter;
}
