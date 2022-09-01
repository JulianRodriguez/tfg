package com.proyecto.tfg.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Collectors;

import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import com.proyecto.tfg.dto.user.ConnectedDTO;
import com.proyecto.tfg.exception.InvalidRequestException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

	private static final Integer LONGTEXTBASIC = 5;

	@Autowired
	UserService userService;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ConnectedDTO login(@RequestHeader("Authorization") String auth) throws UnsupportedEncodingException, InvalidRequestException, NotFoundException {

		System.out.println("Estoy en login");
		System.out.println(auth);

		if (auth != null && auth.startsWith("Basic")) {
			String credentials = new String(Base64.getDecoder().decode(auth.substring(LONGTEXTBASIC).trim()), "UTF-8");
			final String[] values = credentials.split(":",2);

	        
	        if(values.length == 2) { 
	        	final Authentication token = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(values[0], values[1]));
	        	final String idSession = RequestContextHolder.currentRequestAttributes().getSessionId();
				User u = userService.getAndCheckByUsername(values[0]);

	        	return ConnectedDTO.builder()
						.idUser(u.getIdUser())
						.name(u.getName())
						.email(u.getEmail())
						.phone(u.getPhone())
						.idRole(u.getRole().getIdRole())
	    				.username(values[0])
	    				.grantedAuthorities(token.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
						.rolename(u.getRole().getName())
						.idSession(idSession)
	    				.build();
	        } else {
	        	throw new InvalidRequestException("Petición de autorización incorrecto");
	        }
	    }
	    else {
	    	throw new InvalidRequestException("Se debe incluir la autenticación en la petición.");
	    }
	}

	
	@GetMapping("/connection") 
	public boolean connected(Authentication auth) {
		
		final Boolean isConected = Optional.ofNullable(auth).map(Authentication::getPrincipal).isPresent();
		final HttpStatus statusConnection = isConected ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		
		return isConected;
	}

	@PostMapping("/logout")
	public void logout(Authentication auth) {
		if(auth != null) {
			RequestContextHolder.resetRequestAttributes();
			SecurityContextHolder.clearContext();
			System.out.println("Estoy en logout dentro");

		}
		System.out.println("Estoy en logout fuera");
	}
}
