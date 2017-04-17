package com.thegame.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author afarre
 */
@Data
@Entity
@Table(name="PLAYER")
public class Player implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID",length=16)
	private String id;

	@Column(name = "NAME",length=64)
	private String name;
}
