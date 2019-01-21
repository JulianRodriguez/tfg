package com.proyecto.tfg.dto.privilege;

import java.io.Serializable;

import lombok.Data;

@Data
public class PrivilegeDTO implements Serializable {
	
	private static final long serialVersionUID = 18765786434L;

	private Long idPrivilege;
	private String name;
}
