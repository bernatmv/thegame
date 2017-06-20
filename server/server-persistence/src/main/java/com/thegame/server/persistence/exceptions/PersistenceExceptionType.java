package com.thegame.server.persistence.exceptions;

import com.thegame.server.common.exceptions.ExceptionType;

/**
 * @author e103880
 */
public enum PersistenceExceptionType implements ExceptionType{

	UNEXPECTED("Unexpected exceptions"),
	//Item
	RACE_LOAD_FAIL("Race wide load failed, by {}"),
	RACE_NOT_EXIST("Race {} not exist in database"),
	RACE_RETRIEVAL_FAIL("Race {} retrieval failed, by {}"),
	RACE_CREATION_ALREADY_EXIST("Race creation failed, id {} already exist in database"),
	RACE_CREATION_FAIL("Race creation {} failed, by {}"),
	//NonPlayerCharacter
	NPCHARACTER_LOAD_FAIL("NonPlayerCharacter wide load failed, by {}"),
	NPCHARACTER_NOT_EXIST("NonPlayerCharacter {} not exist in database"),
	NPCHARACTER_RETRIEVAL_FAIL("NonPlayerCharacter {} retrieval failed, by {}"),
	NPCHARACTER_CREATION_ALREADY_EXIST("NonPlayerCharacter creation failed, id {} already exist in database"),
	NPCHARACTER_CREATION_FAIL("NonPlayerCharacter creation {} failed, by {}"),
	NPCHARACTER_MERGE_FAIL("NonPlayerCharacter merge {} failed, by {}"),
	//Item
	ITEM_LOAD_FAIL("Item wide load failed, by {}"),
	ITEM_CREATION_ALREADY_EXIST("Item creation failed, id {} already exist in database"),
	ITEM_CREATION_FAIL("Item creation {} failed, by {}"),
	//Area
	AREA_LOAD_FAIL("Area wide load failed, by {}"),
	AREA_CREATION_ALREADY_EXIST("Area creation failed, id {} already exist in database"),
	AREA_CREATION_FAIL("Area creation {} failed, by {}"),
	//	AreaExit
	AREAEXIT_CREATION_ALREADY_EXIST("AreaExit creation failed, id {} already exist in database"),
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
