package com.thegame.server.persistence.entities.ids;

import com.thegame.server.persistence.entities.Area;
import com.thegame.server.persistence.entities.Item;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
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
public class AreaItemId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne(optional=false,targetEntity=Area.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKAREA",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_ITEM_AREA"))
	private Area area;

	@ManyToOne(optional=false,targetEntity=Item.class,fetch=FetchType.LAZY) 
	@JoinColumn(name="FKITEM",referencedColumnName ="ID", nullable = false,foreignKey=@ForeignKey(name="FK_AREA_ITEM"))
	private Item item;
		
	@Column(name = "NAME",length=16)
	private String name;
}
