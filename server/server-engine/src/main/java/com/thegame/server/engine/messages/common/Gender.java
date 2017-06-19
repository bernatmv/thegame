package com.thegame.server.engine.messages.common;

/**
 * @author afarre
 */
public enum Gender {

	male('M'),
	female('F'),
	herm('H'),
	neutral('T'),
	none('N'),
	other('X'),
	;

	private final char code;
	
	Gender(final char _code){
		this.code=_code;
	}

	
	public char getCode() {
		return code;
	}

	public static final Gender valueOf(final char _code) {

		Gender reply=null;
		
		for(Gender gender:Gender.values()){
			if(gender.getCode()==_code){
				reply=gender;
			}
		}

		return reply;
	}
}
