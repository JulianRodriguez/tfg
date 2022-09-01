package com.proyecto.tfg.service.email;

import com.proyecto.tfg.dto.email.EmailDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;


@Service
public class EmailServiceImpl implements EmailService {

    private final Properties properties = new Properties();
    private Session session;
    private String username = "pruebaenvioemailtfg@gmail.com";
    private String toEmail = "jrcarave91@gmail.com";
    private String password = "juliersean";
    private Boolean valido = false;
    private String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendSimpleMail(EmailDTO dto) throws NotFoundException, NoSuchAlgorithmException {
        System.out.println(dto);
        valido = false;
        if (dto.getEmail() != null) {
            if (userService.CheckByEmail(dto.getEmail())) {

                int length = 10;
                Random random = SecureRandom.getInstanceStrong(); // as of JDK 8, this should return the strongest algorithm available to the JVM
                StringBuilder sb = new StringBuilder(length);
                for (int i = 0; i < length; i++) {
                    int indexRandom = random.nextInt(symbols.length);
                    sb.append(symbols[indexRandom]);
                }
                String passwordGenerated = sb.toString();
                System.out.println(passwordGenerated);
                // Try block to check for exceptions
                try {

                    // Creating a simple mail message
                    SimpleMailMessage mailMessage = new SimpleMailMessage();

                    User user = userService.getUserWithEmail(dto.getEmail());
                    String encryptPassword = Optional.ofNullable(passwordGenerated).map(DigestUtils::sha1Hex).orElse(StringUtils.EMPTY);
                    user.setPassword(encryptPassword);
                    userService.update(user);

                    // Setting up necessary details
                    mailMessage.setFrom("pruebaenvioemailtfg@gmail.com");
                    mailMessage.setTo(dto.getEmail());
                    mailMessage.setText("Las contraseña nueva es: " + passwordGenerated);
                    mailMessage.setSubject("Testing from Spring Boot");


                    // Sending the mail
                    javaMailSender.send(mailMessage);
                    return true;
                } catch (Exception e) {
                    e.getMessage();
                    return false;
                }

            }
        }
        return false;
    }
}
