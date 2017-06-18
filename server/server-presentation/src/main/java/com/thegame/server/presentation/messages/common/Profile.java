package com.thegame.server.presentation.messages.common;

import com.thegame.server.engine.intern.data.beans.Stat;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.engine.messages.common.Gender;
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
public class Profile implements IsMessageBean{

	private String name;
	private Gender gender;
	private String race;
	private int level;
	private Stat health;
	private Stat magic;
	private Stat stamina;
}
