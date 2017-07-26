package com.thegame.server.persistence.entities;

import com.thegame.server.persistence.entities.ids.AreaExitId;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
@Table(name="AREAEXIT",indexes={
	@Index(name = "IDX_EXIT_FKAREA", columnList ="FKAREA",  unique = false),
	@Index(name = "IDX_EXIT_FKTOAREA", columnList ="FKTOAREA",  unique = false),
})
public class AreaExit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AreaExitId id;
	
	@ManyToOne(optional=false,targetEntity=Area.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKTOAREA",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_EXIT_TOAREA"))
	private Area toArea;
}
