package com.thegame.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 * @author afarre
 */
@Data
@Embeddable
public class RoomExitId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne(optional=false,targetEntity=Area.class) 
	@JoinColumn(name="FKROOM",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_EXIT_ROOM"))
	private Area room;
		
	@Column(name = "NAME",length=16)
	private String name;
}
