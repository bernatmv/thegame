package com.thegame.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Embeddable
public class AreaExitId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne(optional=false,targetEntity=Area.class) 
	@JoinColumn(name="FKAREA",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_EXIT_AREA"))
	private Area area;
		
	@Column(name = "NAME",length=16)
	private String name;
}
