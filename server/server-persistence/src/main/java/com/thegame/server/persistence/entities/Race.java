package com.thegame.server.persistence.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author afarre
 */
@Data
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
	
	@Column(name = "HEALTHBASE")
	private int healthBase;
	@Column(name = "HEALTHPERLEVEL")
	private double healthPerLevel;

	@Column(name = "MAGICBASE",length=64)
	private int magicBase;
	@Column(name = "MAGICPERLEVEL",length=64)
	private double magicPerLevel;

	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name = "CHATTER",length=64)
	private Set<String> chatter;
}
