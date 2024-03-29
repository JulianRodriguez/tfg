package com.proyecto.tfg.component.mapper.user;

import com.proyecto.tfg.component.mapper.AbstractMapper;
import com.proyecto.tfg.dto.user.UserDTO;
import com.proyecto.tfg.dto.user.UserPostDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Role;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl extends AbstractMapper<User, UserDTO> implements UserMapper {

	@Autowired
    RoleService roleService;
	
	
	@Override
	public Class<? extends UserDTO> dtoClazz() {
		return UserDTO.class;
	}

	@Override
	public Class<? extends User> modelClazz() {
		return User.class;
	}
	
	@Override
	public User dtoToModel(UserPostDTO dto) throws NotFoundException {

		System.out.println("En un lugar de la mancha");
        System.out.println("id rol es:"+dto.getIdRole());
		Role role;
		if(dto.getIdRole() != 0){
			role = longToRole(dto.getIdRole());
		}else{
			role = null;
		}
		return map(dto, role);
	}

	@Override
	public User dtoToModel(UserDTO dto) throws NotFoundException {

		System.out.println("En un lugar de la mancha");
		Role role;
		System.out.println("id rol es:"+dto.getIdRole());
		if(dto.getIdRole() != 0){
			role = longToRole(dto.getIdRole());
		}else{
			role = null;
		}
		return map(dto, role);
	}


	private Role longToRole(Long idRole) throws NotFoundException {
			return roleService.findById(idRole)
					.orElseThrow(() -> new NotFoundException("El rol no existe"));	
	}
	
	private User map(UserPostDTO dto, Role role) {
		User user = dozer.map(dto, modelClazz());
		user.setRole(role);
		return user;
	}
	
	private User map(UserDTO dto, Role role) {
		User user = dozer.map(dto, modelClazz());
		user.setRole(role);
		return user;
	}

	@Override
	public UserDTO modelToDto(User model) {
		UserDTO dto = dozer.map(model, dtoClazz());
		dto.setIdRole(model.getRole().getIdRole());
		return dto;
	}
}
