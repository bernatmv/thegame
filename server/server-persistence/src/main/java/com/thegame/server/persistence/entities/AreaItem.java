package com.thegame.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@Table(name="AREAITEM",indexes={
	@Index(name = "IDX_ITEM_FKAREA", columnList ="FKAREA",  unique = false),
	@Index(name = "IDX_ITEM_FKITEM", columnList ="FKITEM",  unique = false),
})
public class AreaItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AreaItemId id;
	
}
