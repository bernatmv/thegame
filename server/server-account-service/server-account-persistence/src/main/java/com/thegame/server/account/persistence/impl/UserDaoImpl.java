package com.thegame.server.account.persistence.impl;

import com.thegame.server.account.persistence.PersistenceUnit;
import com.thegame.server.account.persistence.UserDao;
import com.thegame.server.account.persistence.dtos.UserDto;
import com.thegame.server.common.persistence.PersistenceSessionFactory;

/**
 * @author afarre
 */
public class UserDaoImpl implements UserDao{

	@Override
	public PersistenceSessionFactory getSessionFactory() {
		return PersistenceUnit.THEGAMEACCOUNTS;
	}
	
	@Override
	public void createUser(UserDto _userDto) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public UserDto getUser(String _email) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void updateUser(UserDto _userDto) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void deleteUser(String _userDto) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}	
}
