package com.proyecto.tfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Privilege implements Serializable {

	private static final long serialVersionUID = 87657865871L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPrivilege;
	
	@Column(unique=true, nullable=false)
	private String name;
}
