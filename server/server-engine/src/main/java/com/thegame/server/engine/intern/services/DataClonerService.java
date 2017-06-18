package com.thegame.server.engine.intern.services;

import com.thegame.server.engine.intern.data.NonPlayerData;
import com.thegame.server.engine.intern.data.RaceData;
import com.thegame.server.engine.intern.data.beans.Stat;
import com.thegame.server.engine.intern.data.beans.StatRules;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author afarre
 */
@Mapper
public interface DataClonerService {
	
	public static final DataClonerService instance=Mappers.getMapper(DataClonerService.class);

	public StatRules clone(final StatRules _origin);
	public Stat clone(final Stat _origin);
	public RaceData clone(final RaceData _origin);
	public NonPlayerData clone(final NonPlayerData _origin);
}
