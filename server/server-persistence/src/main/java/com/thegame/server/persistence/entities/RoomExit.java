package com.thegame.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author afarre
 */
@Data
@Entity
@Table(name="ROOMEXIT",indexes={
	@Index(name = "IDX_FKROOM", columnList ="FKROOM",  unique = false),
	@Index(name = "IDX_FKTOROOM", columnList ="FKTOROOM",  unique = false),
})
public class RoomExit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RoomExitId id;
	
	@ManyToOne(optional=false,targetEntity=Area.class) 
	@JoinColumn(name="FKTOROOM",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_EXIT_TOROOM"))
	private Area toRoom;
}
