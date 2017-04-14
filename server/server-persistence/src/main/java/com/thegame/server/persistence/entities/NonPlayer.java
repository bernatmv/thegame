package com.thegame.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author afarre
 */
@Data
@Entity
@Table(schema ="THEGAME",name="NONPLAYER")
public class NonPlayer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NONPLAYER_GEN",allocationSize = 1,initialValue = 1,sequenceName = "NONPLAYER_SEQ")
	@GeneratedValue(generator = "NONPLAYER_GEN" ,strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "NAME",length=64)
	private String name;
}
