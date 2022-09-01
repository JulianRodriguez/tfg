package com.proyecto.tfg.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1587687345L;
	
	private static final String FIELD_IDROLE = "idRole";
	private static final String FIELD_IDPRIVILEGE = "idPrivilege";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRole;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="role_privilege",
			joinColumns=@JoinColumn(name=FIELD_IDROLE, referencedColumnName=FIELD_IDROLE),
			inverseJoinColumns=@JoinColumn(name=FIELD_IDPRIVILEGE, referencedColumnName=FIELD_IDPRIVILEGE))
	private List<Privilege> privilege;
}
