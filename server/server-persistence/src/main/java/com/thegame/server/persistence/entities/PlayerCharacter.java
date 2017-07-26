package com.thegame.server.persistence.entities;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PLAYERCHARACTER",indexes={
	@Index(name = "IDX_PLAYERCHARACTER_FKRACE", columnList ="FKRACE",  unique = false),
	@Index(name = "IDX_PLAYERCHARACTER_FKAREA", columnList ="FKAREA",  unique = false),
	@Index(name = "IDX_PLAYERCHARACTER_FKUSER", columnList ="FKUSER",  unique = false),
})
public class PlayerCharacter extends Player{

	@ManyToOne(optional=true,targetEntity=Area.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKUSER",referencedColumnName ="ID", nullable = true,foreignKey=@ForeignKey(name="FK_PLAYERCHARACTER_GAMEUSER"))
	private User user;
}
