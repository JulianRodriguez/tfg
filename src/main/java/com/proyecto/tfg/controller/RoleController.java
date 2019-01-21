package com.proyecto.tfg.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.tfg.dto.role.RoleDTO;
import com.proyecto.tfg.model.Role;

@RestController
@RequestMapping("/role")
public class RoleController extends AbstractController<Role, RoleDTO>{

}
