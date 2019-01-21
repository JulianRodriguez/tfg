package com.proyecto.tfg.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.proyecto.tfg.dto.user.ConnectedDTO;
import com.proyecto.tfg.exception.InvalidRequestException;

@RestController
public class LoginController {

	private static final Integer LONGTEXTBASIC = 5;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ConnectedDTO login(@RequestHeader("Authorization") String auth) throws UnsupportedEncodingException, InvalidRequestException {
		
	    if (auth != null && auth.startsWith("Basic")) {
	        String credentials = new String(Base64.getDecoder().decode(auth.substring(LONGTEXTBASIC).trim()), "UTF-8");
	        final String[] values = credentials.split(":",2);
	        
	        if(values.length == 2) { 
	        	final Authentication token = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(values[0], values[1]));
	        	final String idSession = RequestContextHolder.currentRequestAttributes().getSessionId();
	        	
	        	return ConnectedDTO.builder()
	    				.username(values[0])
	    				.grantedAuthorities(token.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
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
	public ResponseEntity<ConnectedDTO> connected(Authentication auth) {
		
		final Boolean isConected = Optional.ofNullable(auth).map(Authentication::getPrincipal).isPresent();
		final HttpStatus statusConnection = isConected ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		
		return new ResponseEntity<>(statusConnection);
	}
}
