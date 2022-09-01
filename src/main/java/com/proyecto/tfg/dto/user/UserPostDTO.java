package com.proyecto.tfg.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserPostDTO extends UserDTO{

	private static final long serialVersionUID = 1578658765L;

	private Long idUser;
	private String password;
}
