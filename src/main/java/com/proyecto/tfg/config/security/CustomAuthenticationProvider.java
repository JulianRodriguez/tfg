package com.proyecto.tfg.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Role;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.user.UserService;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
//	@Transactional
	public Authentication authenticate(Authentication authentication) {
		
		val username = Optional.ofNullable(authentication).map(Authentication::getName);
		val password = Optional.ofNullable(authentication).map(Authentication::getCredentials).map(Object::toString);

		if (username.isPresent() && password.isPresent()) {
			Authentication token;
			try {
				token = validateUserLogin(username.get(), password.get());
				
				SecurityContextHolder.getContext().setAuthentication(token);
				return token;
			} catch (NotFoundException e) {
				log.error("No existe ningún usuario con este alias");
			}
		}

		throw new UsernameNotFoundException("Username and password must not be empty");
	}

	private UsernamePasswordAuthenticationToken validateUserLogin(String username, String password) throws NotFoundException {

		final User user = userService.getAndCheckByUsername(username);
		String encryptPassword = Optional.ofNullable(password).map(DigestUtils::sha1Hex).orElse(StringUtils.EMPTY);
		val userPasswordIsTheSameLoginPassword = user.getPassword().equals(encryptPassword);

		if (userPasswordIsTheSameLoginPassword) {

			final List<SimpleGrantedAuthority> authorities = Optional.ofNullable(user.getRole()).map(getGrantedAuthoritiesFromRol)
					.orElse(new ArrayList<>());
			final UserDetails userAuthenticated = Optional.ofNullable(createUserDetails(user)).orElseThrow(
					() -> new UsernameNotFoundException("It cannot extracts the user from the body of request"));
			
			return new UsernamePasswordAuthenticationToken(userAuthenticated, encryptPassword, authorities);
		} else { 
			throw new UsernameNotFoundException("The credentials introduced is not correct");
		}
	}

	final Function<Role, List<SimpleGrantedAuthority>> getGrantedAuthoritiesFromRol = rol -> rol.getPrivilege()
			.stream().map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private UserDetails createUserDetails(final User user) {
		final List<SimpleGrantedAuthority> authorities = Optional.ofNullable(user).map(User::getRole)
				.map(getGrantedAuthoritiesFromRol).orElse(new ArrayList<>());
//		return new ConnectedUser(user.getUsername(), user.getPassword(), authorities, user.getPassword());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
}
