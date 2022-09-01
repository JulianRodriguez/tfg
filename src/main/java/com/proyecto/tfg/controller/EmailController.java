package com.proyecto.tfg.controller;

import com.proyecto.tfg.dto.email.EmailDTO;
import com.proyecto.tfg.dto.user.UserDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.email.EmailService;
import com.proyecto.tfg.service.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@RestController
@RequestMapping(value= "/email")
public class EmailController {

    private final Properties properties = new Properties();

    private Session session;

    private String username= "pruebaenvioemailtfg@gmail.com";
    private String toEmail= "jrcarave91@gmail.com";

    private String password= "juliersean";

    private Boolean valido = false;

    private String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSender javaMailSender;


    @PutMapping("/cambio")
    public boolean sendEmail3(@RequestBody EmailDTO dto) throws NotFoundException, NoSuchAlgorithmException {

        System.out.println(dto);
        boolean result = emailService.sendSimpleMail(dto);
        return result;
    }

}

