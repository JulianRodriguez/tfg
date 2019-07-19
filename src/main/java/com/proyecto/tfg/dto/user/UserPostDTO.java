package com.proyecto.tfg.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserPostDTO{

	private static final long serialVersionUID = 1578658765L;

	private Long idUser;
	private String name;
	private String username;
	private String email;
	private String phone;
	private Long idRole;
	private String password;
}
