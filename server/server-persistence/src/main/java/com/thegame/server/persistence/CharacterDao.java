package com.thegame.server.persistence;

import com.thegame.server.persistence.entities.NonPlayerCharacter;
import com.thegame.server.persistence.entities.Race;
import com.thegame.server.persistence.support.JPAPersistenceDao;
import java.util.List;

/**
 *
 * @author afarre
 */
public interface CharacterDao extends JPAPersistenceDao{

	public void saveRace(final Race _race);
	public Race getRace(final String _raceId);
	public List<Race> loadRaces();

	public void saveCharacter(final NonPlayerCharacter _character);
	public void mergeCharacter(final NonPlayerCharacter _character);
	public NonPlayerCharacter getCharacter(final String _characterId);
	public List<NonPlayerCharacter> loadCharacters();
}
