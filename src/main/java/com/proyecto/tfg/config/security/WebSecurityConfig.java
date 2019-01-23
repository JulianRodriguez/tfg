package com.proyecto.tfg.config.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor_={@Autowired})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String URLRESTAURANT = "/restaurant";
	private static final String URLUSER = "/user";
	private static final String URLSTORE = "/store";
	private static final String URLPRIVILEGE = "/privilege";
	private static final String URLROLE = "/role";

	private AuthenticationEntryPoint restAuthenticationEntryPoint;
	private AuthenticationProvider customAuthenticationProvider;
	  	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(customAuthenticationProvider);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
	    	.csrf().disable()
	    	.exceptionHandling()
	    	.authenticationEntryPoint(restAuthenticationEntryPoint);
	        
		http
	    	.authorizeRequests()    
	    	.antMatchers(HttpMethod.POST, "/login").permitAll()
	    	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	    	
	    	.antMatchers(HttpMethod.GET, URLRESTAURANT).hasAuthority("GET_RESTAURANT")
	    	.antMatchers(HttpMethod.POST, URLRESTAURANT).hasAuthority("POST_RESTAURANT")
	    	.antMatchers(HttpMethod.PUT, URLRESTAURANT).hasAuthority("PUT_RESTAURANT")
	    	.antMatchers(HttpMethod.DELETE, URLRESTAURANT).hasAuthority("DELETE_RESTAURANT")
	    	
	    	.antMatchers(HttpMethod.GET, URLUSER).hasAuthority("GET_USER")
	    	.antMatchers(HttpMethod.POST, URLUSER).hasAuthority("POST_USER")
	    	.antMatchers(HttpMethod.PUT, URLUSER).hasAuthority("PUT_USER")
	    	.antMatchers(HttpMethod.DELETE, URLUSER).hasAuthority("DELETE_USER")
	    	
	    	.antMatchers(HttpMethod.GET, URLSTORE).hasAuthority("GET_STORE")
	    	.antMatchers(HttpMethod.POST, URLSTORE).hasAuthority("POST_STORE")
	    	.antMatchers(HttpMethod.PUT, URLSTORE).hasAuthority("PUT_STORE")
	    	.antMatchers(HttpMethod.DELETE, URLSTORE).hasAuthority("DELETE_STORE")
	    	
	    	.antMatchers(HttpMethod.GET, URLPRIVILEGE).hasAuthority("GET_PRIVILEGE")
	    	.antMatchers(HttpMethod.POST, URLPRIVILEGE).hasAuthority("POST_PRIVILEGE")
	    	.antMatchers(HttpMethod.PUT, URLPRIVILEGE).hasAuthority("PUT_PRIVILEGE")
	    	.antMatchers(HttpMethod.DELETE, URLPRIVILEGE).hasAuthority("DELETE_PRIVILEGE")
	    	
	    	.antMatchers(HttpMethod.GET, URLROLE).hasAuthority("GET_ROLE")
	    	.antMatchers(HttpMethod.POST, URLROLE).hasAuthority("POST_ROLE")
	    	.antMatchers(HttpMethod.PUT, URLROLE).hasAuthority("PUT_ROLE")
	    	.antMatchers(HttpMethod.DELETE, URLROLE).hasAuthority("DELETE_ROLE");
	}
}
