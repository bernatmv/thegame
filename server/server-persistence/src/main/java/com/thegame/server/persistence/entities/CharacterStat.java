package com.thegame.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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
@Embeddable
public class CharacterStat implements Serializable{
	
	@Column(name = "MAXSTAT")
	private int max;
	@Column(name = "CURRENTSTAT")
	private int current;
}
