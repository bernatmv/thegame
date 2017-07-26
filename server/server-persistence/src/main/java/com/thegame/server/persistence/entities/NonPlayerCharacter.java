package com.thegame.server.persistence.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
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
@Table(name="NONPLAYERCHARACTER",indexes={
	@Index(name = "IDX_NONPLAYERCHARACTER_FKRACE", columnList ="FKRACE",  unique = false),
	@Index(name = "IDX_NONPLAYERCHARACTER_FKAREA", columnList ="FKAREA",  unique = false),
})
public class NonPlayerCharacter extends Player{

	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name = "CHATTER",length=64)
	private Set<String> chatter;
}
