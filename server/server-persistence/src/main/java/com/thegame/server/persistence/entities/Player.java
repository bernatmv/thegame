package com.thegame.server.persistence.entities;

import com.thegame.server.persistence.entities.embedables.CharacterStat;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 * @author afarre
 */
@Data
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Player implements Serializable{

	@Id
	@Column(name = "ID",length=16)
	private String id;

	@Column(name = "NAME",length=16)
	private String name;

	@Column(name = "GENDER",length=1)
	private char gender;

	@ManyToOne(optional=false,targetEntity=Race.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKRACE",referencedColumnName ="ID", nullable = false)
	private Race race;

	@ManyToOne(optional=true,targetEntity=Area.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKAREA",referencedColumnName ="ID", nullable = true)
	private Area area;

	@Column(name = "PLAYERLEVEL")
	private int level;
	
	@AttributeOverrides({
		@AttributeOverride(name="max",column=@Column(name="MAXHEALTH")),
		@AttributeOverride(name="current",column=@Column(name="CURRENTHEALTH")),
    })
	private CharacterStat health;

	@AttributeOverrides({
		@AttributeOverride(name="max",column=@Column(name="MAXMAGIC")),
		@AttributeOverride(name="current",column=@Column(name="CURRENTMAGIC")),
    })
	private CharacterStat magic;

	@AttributeOverrides({
		@AttributeOverride(name="max",column=@Column(name="MAXSTAMINA")),
		@AttributeOverride(name="current",column=@Column(name="CURRENTSTAMINA")),
    })
	private CharacterStat stamina;
}
