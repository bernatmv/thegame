package com.thegame.server.persistence.exceptions;

import com.thegame.server.common.exceptions.ExceptionType;

/**
 * @author e103880
 */
public enum PersistenceExceptionType implements ExceptionType{

	//Area
	AREA_LOAD_FAIL("Area wide load failed, by {}"),
	AREA_CREATION_ALREADY_EXIST("Area creation failed, id {} allready exist in database"),
	AREA_CREATION_FAIL("Area creation {} failed, by {}"),
	//	AreaExit
	AREAEXIT_CREATION_ALREADY_EXIST("AreaExit creation failed, id {} allready exist in database"),
	AREAEXIT_CREATION_FAIL("AreaExit creation {} failed, by {}"),
	;

	private final String description;
	
	
	PersistenceExceptionType(final String _description){
		this.description=_description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
