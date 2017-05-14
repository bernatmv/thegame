package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.data.PlayerData;
import com.thegame.server.engine.intern.data.AreaData;
import com.thegame.server.engine.messages.input.RegisterPlayerMessageBean;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.engine.messages.output.PlayerMessageBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author afarre
 */
@Mapper
public interface MessageMapperService {
	
	public static final MessageMapperService instance=Mappers.getMapper(MessageMapperService.class);

	//TO MESSAGEBEAN
/*	@Mappings({
		@Mapping(target="gender",expression="java( com.thegame.server.engine.messages.common.Gender.valueOf(_entity.getGender()))"),
	})
	public ItemMessageBean toMessageBean(final Item _entity);
	@Mappings({
		@Mapping(target="id",source="item.id"),
		@Mapping(target="name",source="id.name"),
		@Mapping(target="item",source="item"),
	})
	public ItemInstance toMessageBean(final AreaItem _entity);
	@Mappings({
		@Mapping(target="exits",expression="java( _entity.getExits()"
																	+ ".stream()"
																	+ ".collect(java.util.stream.Collectors.toMap("
																								+ "areaExit -> areaExit.getId().getName(),"
																								+ "areaExit -> areaExit.getToArea().getId())))"),
		@Mapping(target="players",ignore=true),
	})
	public AreaMessageBean toMessageBean(final Area _entity);
*/	public AreaMessageBean toMessageBean(final AreaData _data);
	public PlayerMessageBean toMessageBean(final PlayerData _data);
	@Mappings({
		@Mapping(target="name",source="sender"),
		@Mapping(target="area",ignore=true)
	})
	public PlayerMessageBean toMessageBean(final RegisterPlayerMessageBean _message);
}
