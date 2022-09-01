package com.proyecto.tfg.component.mapper.user;

import com.proyecto.tfg.component.mapper.Mapper;
import com.proyecto.tfg.dto.user.UserDTO;
import com.proyecto.tfg.dto.user.UserPostDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.User;

public interface UserMapper extends Mapper<User, UserDTO> {
	
	User dtoToModel(UserPostDTO dto) throws NotFoundException;
}
