package com.thegame.server.engine.messages.enums;

/**
 * @author afarre
 */
public enum Gender {

	male('M'),
	FEMALE('F'),
	;

	private final char code;
	
	Gender(final char _code){
		this.code=_code;
	}

	
	public char getCode() {
		return code;
	}
}
