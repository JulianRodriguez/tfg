package com.proyecto.tfg.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.tfg.dto.privilege.PrivilegeDTO;
import com.proyecto.tfg.model.Privilege;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController extends AbstractController<Privilege, PrivilegeDTO>{
	
}
