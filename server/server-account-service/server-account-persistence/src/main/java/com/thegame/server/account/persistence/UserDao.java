package com.thegame.server.account.persistence;

import com.thegame.server.account.persistence.dtos.UserDto;
import com.thegame.server.common.persistence.PersistenceDao;

/**
 *
 * @author afarre
 */
public interface UserDao extends PersistenceDao{

	public void createUser(final UserDto _userDto);

	public UserDto getUser(final String _email);

	public void updateUser(final UserDto _userDto);

	public void deleteUser(final String _userDto);
}
