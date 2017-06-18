package com.thegame.server.persistence.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name="RACE")
public class Race implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID",length=16)
	private String id;

	@Column(name = "SINGULAR",length=64)
	private String singular;

	@Column(name = "PLURAL",length=64)
	private String plural;
	
	@AttributeOverrides({
		@AttributeOverride(name="base",column=@Column(name="HEALTHBASE")),
		@AttributeOverride(name="incrementPerLevel",column=@Column(name="HEALTHPERLEVEL")),
    })
	private RaceStat health;

	@AttributeOverrides({
		@AttributeOverride(name="base",column=@Column(name="MAGICBASE")),
		@AttributeOverride(name="incrementPerLevel",column=@Column(name="MAGICPERLEVEL")),
    })
	private RaceStat magic;
	
	@AttributeOverrides({
		@AttributeOverride(name="base",column=@Column(name="STAMINABASE")),
		@AttributeOverride(name="incrementPerLevel",column=@Column(name="STAMINAPERLEVEL")),
    })
	private RaceStat stamina;

	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name = "CHATTER",length=64)
	private Set<String> chatter;
}
