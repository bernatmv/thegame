package com.thegame.server.persistence.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name="ITEM")
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID",length=16)
	private String id;

	
	@Column(name = "DESCRIPTION",length=2048)
	private String description;

	@Column(name = "ALIVE")
	private boolean alive;

	@Column(name = "GENDER",length=1)
	private char gender;

	@Column(name = "SINGULAR",length=64)
	private String singular;

	@Column(name = "PLURAL",length=64)
	private String plural;

	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name = "CHATTER",length=64)
	private Set<String> chatters;

	@OneToMany(mappedBy="id.item",targetEntity=AreaItem.class,fetch=FetchType.LAZY)
  	private List<AreaItem> areas;
}
