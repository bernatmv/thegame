package com.thegame.server.persistence.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="NONPLAYERCHARACTER",indexes={
	@Index(name = "IDX_CHARACTER_FKRACE", columnList ="FKRACE",  unique = false),
	@Index(name = "IDX_CHARACTER_FKAREA", columnList ="FKAREA",  unique = false),
})
public class NonPlayerCharacter implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID",length=16)
	private String id;

	@Column(name = "NAME",length=16)
	private String name;

	@Column(name = "GENDER",length=1)
	private char gender;

	@ManyToOne(optional=false,targetEntity=Race.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKRACE",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_NONPLAYERCHARACTER_RACE"))
	private Race race;

	@ManyToOne(optional=true,targetEntity=Area.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKAREA",referencedColumnName ="ID", nullable = true,foreignKey=@ForeignKey(name="FK_NONPLAYERCHARACTER_AREA"))
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

	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name = "CHATTER",length=64)
	private Set<String> chatter;
}
