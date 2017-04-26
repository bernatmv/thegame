package com.thegame.server.persistence.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="AREA")
public class Area implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID",length=16)
	private String id;

	@Column(name = "TITLE",length=64)
	private String title;

	@Column(name = "SHORTDESCRIPTION",length=128)
	private String shortDescription;

	@Column(name = "DESCRIPTION",length=2048)
	private String description;

	@OneToMany(mappedBy="id.area",targetEntity=AreaExit.class,fetch=FetchType.EAGER)
  	private List<AreaExit> exits;
	
	//private List<String> players;
	//private List<String> enemies;
	//private List<String> npcs;
	//private List<String> items;				
}
