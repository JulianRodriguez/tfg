package com.proyecto.tfg.controller;

import com.proyecto.tfg.component.mapper.user.UserMapper;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class CheckController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/check_user")
    public boolean checkUser (@RequestParam(required = false) String value) throws NotFoundException {

        boolean valido = true;
        if (!userService.CheckByUsername(value)){
            valido = false;
        }
        return valido;
    }
    @GetMapping("/check_pass")
    public boolean checkPass (@RequestParam(required = false) String email, @RequestParam(required = false) String pass) throws NotFoundException, UnsupportedEncodingException {

        boolean valido = false;
        if (userService.checkBypass(email,pass)){
            valido = true;
        }
        return valido;
    }

    @GetMapping("/check_email")
    public boolean checkEmail (@RequestParam(required = false) String value) throws NotFoundException{

        boolean valido = true;
        if (!userService.CheckByEmail(value)){
            valido = false;
        }
        return valido;
    }
}
