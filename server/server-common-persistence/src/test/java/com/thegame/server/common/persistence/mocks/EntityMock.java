package com.thegame.server.common.persistence.mocks;

/**
 * @author afarre
 */
public class EntityMock {

	private String id;
	private String value;

	public EntityMock(final String _id,final String _value){
		this.id=_id;
		this.value=_value;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String _id) {
		this.id=_id;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String _value) {
		this.value=_value;
	}
}
