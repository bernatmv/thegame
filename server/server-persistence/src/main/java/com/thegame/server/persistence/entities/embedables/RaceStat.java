package com.thegame.server.persistence.entities.embedables;

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
public class RaceStat implements Serializable{
	
	@Column(name = "BASE")
	private int base;
	@Column(name = "INCREMENTPERLEVEL")
	private float incrementPerLevel;
}
