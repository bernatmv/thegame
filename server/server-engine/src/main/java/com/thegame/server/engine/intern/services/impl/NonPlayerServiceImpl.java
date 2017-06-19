package com.thegame.server.engine.intern.services.impl;

import com.thegame.server.common.functional.Tuple;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.data.NonPlayerData;
import com.thegame.server.engine.intern.data.beans.Stat;
import com.thegame.server.engine.intern.data.beans.StatRules;
import com.thegame.server.engine.intern.services.CharacterService;
import com.thegame.server.engine.intern.services.DataClonerService;
import com.thegame.server.engine.intern.services.DataMapperService;
import com.thegame.server.engine.intern.services.NonPlayerService;
import com.thegame.server.persistence.CharacterDao;
import com.thegame.server.persistence.PersistenceServiceFactory;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author afarre
 */
public class NonPlayerServiceImpl implements NonPlayerService{

	private static final LogStream logger=LogStream.getLogger(NonPlayerServiceImpl.class);

	private CharacterDao characterDao;
	private DataMapperService dataMapperService;
	private CharacterService characterService;
	private DataClonerService dataClonerService;
	private final Map<String,NonPlayerData> nonPlayers;
	
	
	public NonPlayerServiceImpl(){
		this(PersistenceServiceFactory.CHARACTERDAO.getInstance(CharacterDao.class),
			EngineServiceFactory.DATAMAPPER.getInstance(DataMapperService.class),
			EngineServiceFactory.DATACLONER.getInstance(DataClonerService.class),
			EngineServiceFactory.CHARACTER.getInstance(CharacterService.class));
	}
	public NonPlayerServiceImpl(final CharacterDao _characterDao,final DataMapperService _dataMapperService,final DataClonerService _dataClonerService,final CharacterService _characterService){
		logger.trace("non-player::service::initialization::begin");
		this.characterDao=_characterDao;
		this.dataMapperService=_dataMapperService;
		this.characterService=_characterService;
		this.dataClonerService=_dataClonerService;
		this.nonPlayers=this.characterDao.loadCharacters().stream()
			.peek(nonPlayerCharacter -> logger.debug("non-player::service::initialization::character::{}::found",nonPlayerCharacter.getId()))
			.map(nonPlayerData -> dataMapperService.toData(nonPlayerData))
			.peek(nonPlayerData -> logger.debug("non-player::service::initialization::character::{}::converted::{}",nonPlayerData.getId()))
			.map(nonPlayerData -> consolidateCharacter(nonPlayerData))
			.peek(nonPlayerData -> logger.debug("non-player::service::initialization::character::{}::consolidated::{}",nonPlayerData.getId()))
			.collect(Collectors.toMap(nonPlayerData -> nonPlayerData.getId(),nonPlayerData -> nonPlayerData));
		logger.debug("non-player::service::initialization::end");
	}
	
	protected Stat consolidateStat(final String _id,final String _statName,final Stat _stat,final StatRules _rule,final int _level) {
		
		Stat reply;
		
		logger.trace("non-player::service::consolidate::{}::stat::{}::begin::data::{}",_id,_statName,_stat);
		reply=Optional.ofNullable(_stat)
				.orElseGet(() -> Stat.builder()
									.max(_rule.getBase()+(int)_rule.getIncrementPerLevel()*_level)
									.current(_rule.getBase()+(int)_rule.getIncrementPerLevel()*_level)
									.build());
		logger.debug("non-player::service::consolidate::{}::stat::{}::end::max::{}::current:{}",_id,_statName,reply.getMax(),reply.getCurrent());
		
		return reply;
	}
	protected NonPlayerData consolidateCharacter(final NonPlayerData _nonPlayerData) {
		return Stream.of(_nonPlayerData)
					.map(nonPlayerData -> new Tuple<>(nonPlayerData,this.characterService.getRace(nonPlayerData.getRace())))
					.map(nonPlayerTuple -> nonPlayerTuple.mutateFirst(
												NonPlayerData.builder()
													.id(nonPlayerTuple.getObject1().getId())
													.name(nonPlayerTuple.getObject1().getName())
													.gender(nonPlayerTuple.getObject1().getGender())
													.race(nonPlayerTuple.getObject1().getRace())
													.singular(nonPlayerTuple.getObject2().getSingular())
													.plural(nonPlayerTuple.getObject2().getPlural())
													.level(nonPlayerTuple.getObject1().getLevel())
													.health(consolidateStat(nonPlayerTuple.getObject1().getId(),"health",nonPlayerTuple.getObject1().getHealth(),nonPlayerTuple.getObject2().getHealth(),nonPlayerTuple.getObject1().getLevel()))
													.magic(consolidateStat(nonPlayerTuple.getObject1().getId(),"magic",nonPlayerTuple.getObject1().getMagic(),nonPlayerTuple.getObject2().getMagic(),nonPlayerTuple.getObject1().getLevel()))
													.stamina(consolidateStat(nonPlayerTuple.getObject1().getId(),"stamina",nonPlayerTuple.getObject1().getStamina(),nonPlayerTuple.getObject2().getStamina(),nonPlayerTuple.getObject1().getLevel()))
													.chatter(((nonPlayerTuple.getObject1().getChatter()!=null)||(nonPlayerTuple.getObject1().getChatter().isEmpty()))? 
																	nonPlayerTuple.getObject1().getChatter() : 
																	nonPlayerTuple.getObject2().getChatter())
													.build()))
					.map(nonPlayerTuple -> nonPlayerTuple.getObject1())
					.findAny()
					.orElseThrow(() -> new EngineException(EngineExceptionType.NONPLAYER_UNCONSOLIDABLE,_nonPlayerData.getId()));

	}	

	protected Optional<NonPlayerData> getOptionalNonPlayer(final String _id) {
		return Optional.of(Optional.ofNullable(_id)
					.map(npcId -> this.nonPlayers.get(npcId))
					.orElseThrow(() -> new EngineException(EngineExceptionType.NONPLAYER_NOT_EXIST,_id)));

	}	

	@Override
	public NonPlayerData getNonPlayer(final String _id) {
		return getOptionalNonPlayer(_id)
					.map(nonPlayer -> this.dataClonerService.clone(nonPlayer))
					.orElseThrow(() -> new EngineException(EngineExceptionType.NONPLAYER_NOT_EXIST,_id));
	}	
}
