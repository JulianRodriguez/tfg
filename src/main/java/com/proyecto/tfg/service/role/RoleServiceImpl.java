package com.proyecto.tfg.service.role;

import com.proyecto.tfg.dao.RoleDAO;
import com.proyecto.tfg.model.Role;
import com.proyecto.tfg.service.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.proyecto.tfg.exception.NotFoundException;

@Service
public class RoleServiceImpl extends AbstractService<Role, RoleDAO> implements RoleService {

	@Override
	public Role getAndCheck(Long id) throws NotFoundException {
		return findById(id).orElseThrow(() -> new NotFoundException("El role no existe"));
	}

	@Override
	public boolean isEqual(Role r1, Role r2) {
		return StringUtils.equals(r1.getName(), r2.getName()) &&
				r1.getPrivilege().equals(r2.getPrivilege());
	}

	@Override
	public void setValues(Role to, Role from) {
		to.setName(from.getName());
		to.setPrivilege(from.getPrivilege());
	}
}
