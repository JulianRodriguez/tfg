package com.proyecto.tfg.service.email;

import com.proyecto.tfg.dto.email.EmailDTO;
import com.proyecto.tfg.exception.NotFoundException;

import java.security.NoSuchAlgorithmException;

public interface  EmailService {

    public boolean sendSimpleMail(EmailDTO dto) throws NotFoundException, NoSuchAlgorithmException;
}
