package com.proyecto.tfg.service.privilege;

import com.proyecto.tfg.dao.PrivilegeDAO;
import com.proyecto.tfg.model.Privilege;
import com.proyecto.tfg.service.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.proyecto.tfg.exception.NotFoundException;

@Service
public class PrivilegeServiceImpl extends AbstractService<Privilege, PrivilegeDAO> implements PrivilegeService {

	@Override
	public Privilege getAndCheck(Long id) throws NotFoundException {
		return findById(id).orElseThrow(() -> new NotFoundException("El privilegio no existe"));
	}

	@Override
	public boolean isEqual(Privilege p1, Privilege p2) {
		return StringUtils.equals(p1.getName(), p2.getName());	
	}

	@Override
	public void setValues(Privilege to, Privilege from) {
		to.setName(from.getName());
	}
}
