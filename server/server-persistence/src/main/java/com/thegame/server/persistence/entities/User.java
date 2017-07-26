package com.thegame.server.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * @author afarre
 */
@Data
@Entity
@Table(name="GAMEUSER")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMAIL",length=256,unique=true,nullable=false)
	private String email;

	@Column(name = "NAME",length=512,nullable=true)
	private String name;

	@Column(name = "SURNAME",length=512,nullable=true)
	private String surname;

	@Column(name = "LASTNAME",length=512,nullable=true)
	private String lastName;

	@Column(name = "PASSWORD",length=64,nullable=false)
	private String password;

	@Column(name = "PHONE",length=64,nullable=true)
	private String phone;

	@Column(name = "AUTHTOKEN",nullable=true)
	private String authToken;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUTHTOKEN_EXPIRATION",nullable=true)
	private Date authTokenExpiration;

	
	@Column(name = "VERIFIED",nullable=false)
	private boolean verified;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VERIFIED_DATE",nullable=true)
	private Date verifiedDate;
	
	
	@OneToMany(mappedBy="user",targetEntity=PlayerCharacter.class)
  	private List<PlayerCharacter> characters;
}
