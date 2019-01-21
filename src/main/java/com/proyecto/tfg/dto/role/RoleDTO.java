package com.proyecto.tfg.dto.role;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RoleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idRole;
	private String name;
	private List<Long> privileges;
}
