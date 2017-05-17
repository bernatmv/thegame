package com.thegame.server.persistence.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author afarre
 */
@Data
@Entity
@Table(name="NONPLAYERCHARACTER")
public class NonPlayerCharacter implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID",length=16)
	private String id;

	@Column(name = "NAME",length=16)
	private String name;

	@ManyToOne(optional=false,targetEntity=Race.class,fetch=FetchType.EAGER) 
	@JoinColumn(name="FKRACE",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_NONPLAYERCHARACTER_RACE"))
	private Race race;

	@Column(name = "PLAYERLEVEL")
	private int level;
	
	@Column(name = "HEALTH")
	private int health;
	@Column(name = "CURRENTHEALTH")
	private int currentHealth;

	@Column(name = "MAGIC")
	private int magic;
	@Column(name = "MAGICHEALTH")
	private int currentMagic;

	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name = "CHATTER",length=64)
	private Set<String> chatter;
}
