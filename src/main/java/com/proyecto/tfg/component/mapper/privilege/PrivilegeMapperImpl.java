package com.proyecto.tfg.component.mapper.privilege;

import com.proyecto.tfg.component.mapper.AbstractMapper;
import com.proyecto.tfg.dto.privilege.PrivilegeDTO;
import com.proyecto.tfg.model.Privilege;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeMapperImpl extends AbstractMapper<Privilege, PrivilegeDTO> implements PrivilegeMapper {

	@Override
	public Class<? extends PrivilegeDTO> dtoClazz() {
		return PrivilegeDTO.class;
	}

	@Override
	public Class<? extends Privilege> modelClazz() {
		return Privilege.class;
	}

}
