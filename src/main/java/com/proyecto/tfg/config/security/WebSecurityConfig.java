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
	
	private static final String URLCOMPANY = "/company";
	private static final String URLUSER = "/user";
	private static final String URLSTORE = "/store";
	private static final String URLCOMPANYTYPE = "/companyType";
	private static final String URLCONTRACT = "/contract";
	private static final String URLCONTRACTTYPE = "/contractType";
	private static final String URLCONTRACTLINE = "/contractLine";
	private static final String URLEMPLOYEE = "/employee";
	private static final String URLDONGLE = "/dongle";
	private static final String URLDONGLETYPE = "/dongleType";
	private static final String URLPOPULATION = "/population";
	private static final String URLPRIVILEGE = "/privilege";
	private static final String URLPROVINCE = "/province";
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
	    	
	    	.antMatchers(HttpMethod.GET, URLCOMPANY).hasAuthority("GET_COMPANY")
	    	.antMatchers(HttpMethod.POST, URLCOMPANY).hasAuthority("POST_COMPANY")
	    	.antMatchers(HttpMethod.PUT, URLCOMPANY).hasAuthority("PUT_COMPANY")
	    	.antMatchers(HttpMethod.DELETE, URLCOMPANY).hasAuthority("DELETE_COMPANY")
	    	
	    	.antMatchers(HttpMethod.GET, URLUSER).hasAuthority("GET_USER")
	    	.antMatchers(HttpMethod.POST, URLUSER).hasAuthority("POST_USER")
	    	.antMatchers(HttpMethod.PUT, URLUSER).hasAuthority("PUT_USER")
	    	.antMatchers(HttpMethod.DELETE, URLUSER).hasAuthority("DELETE_USER")
	    	
	    	.antMatchers(HttpMethod.GET, URLSTORE).hasAuthority("GET_STORE")
	    	.antMatchers(HttpMethod.POST, URLSTORE).hasAuthority("POST_STORE")
	    	.antMatchers(HttpMethod.PUT, URLSTORE).hasAuthority("PUT_STORE")
	    	.antMatchers(HttpMethod.DELETE, URLSTORE).hasAuthority("DELETE_STORE")
	    	
	    	.antMatchers(HttpMethod.GET, URLCOMPANYTYPE).hasAuthority("GET_COMPANY_TYPE")
	    	.antMatchers(HttpMethod.POST, URLCOMPANYTYPE).hasAuthority("POST_COMPANY_TYPE")
	    	.antMatchers(HttpMethod.PUT, URLCOMPANYTYPE).hasAuthority("PUT_COMPANY_TYPE")
	    	.antMatchers(HttpMethod.DELETE, URLCOMPANYTYPE).hasAuthority("DELETE_COMPANY_TYPE")
	    	
	    	.antMatchers(HttpMethod.GET, URLCONTRACT).hasAuthority("GET_CONTRACT")
	    	.antMatchers(HttpMethod.POST, URLCONTRACT).hasAuthority("POST_CONTRACT")
	    	.antMatchers(HttpMethod.PUT, URLCONTRACT).hasAuthority("PUT_CONTRACT")
	    	.antMatchers(HttpMethod.DELETE, URLCONTRACT).hasAuthority("DELETE_CONTRACT")
	    	
	    	.antMatchers(HttpMethod.GET, URLCONTRACTTYPE).hasAuthority("GET_CONTRACT_TYPE")
	    	.antMatchers(HttpMethod.POST, URLCONTRACTTYPE).hasAuthority("POST_CONTRACT_TYPE")
	    	.antMatchers(HttpMethod.PUT, URLCONTRACTTYPE).hasAuthority("PUT_CONTRACT_TYPE")
	    	.antMatchers(HttpMethod.DELETE, URLCONTRACTTYPE).hasAuthority("DELETE_CONTRACT_TYPE")
	    	
	    	.antMatchers(HttpMethod.GET, URLCONTRACTLINE).hasAuthority("GET_CONTRACT_LINE")
	    	.antMatchers(HttpMethod.POST, URLCONTRACTLINE).hasAuthority("POST_CONTRACT_LINE")
	    	.antMatchers(HttpMethod.PUT, URLCONTRACTLINE).hasAuthority("PUT_CONTRACT_LINE")
	    	.antMatchers(HttpMethod.DELETE, URLCONTRACTLINE).hasAuthority("DELETE_CONTRACT_LINE")
	    	
	    	.antMatchers(HttpMethod.GET, URLEMPLOYEE).hasAuthority("GET_EMPLOYEE")
	    	.antMatchers(HttpMethod.POST, URLEMPLOYEE).hasAuthority("POST_EMPLOYEE")
	    	.antMatchers(HttpMethod.PUT, URLEMPLOYEE).hasAuthority("PUT_EMPLOYEE")
	    	.antMatchers(HttpMethod.DELETE, URLEMPLOYEE).hasAuthority("DELETE_EMPLOYEE")
	    	
	    	.antMatchers(HttpMethod.GET, URLDONGLE).hasAuthority("GET_DONGLE")
	    	.antMatchers(HttpMethod.POST, URLDONGLE).hasAuthority("POST_DONGLE")
	    	.antMatchers(HttpMethod.PUT, URLDONGLE).hasAuthority("PUT_DONGLE")
	    	.antMatchers(HttpMethod.DELETE, URLDONGLE).hasAuthority("DELETE_DONGLE")
	    	
	    	.antMatchers(HttpMethod.GET, URLDONGLETYPE).hasAuthority("GET_DONGLE_TYPE")
	    	.antMatchers(HttpMethod.POST, URLDONGLETYPE).hasAuthority("POST_DONGLE_TYPE")
	    	.antMatchers(HttpMethod.PUT, URLDONGLETYPE).hasAuthority("PUT_DONGLE_TYPE")
	    	.antMatchers(HttpMethod.DELETE, URLDONGLETYPE).hasAuthority("DELETE_DONGLE_TYPE")
	    	
	    	.antMatchers(HttpMethod.GET, URLPOPULATION).hasAuthority("GET_POPULATION")
	    	.antMatchers(HttpMethod.POST, URLPOPULATION).hasAuthority("POST_POPULATION")
	    	.antMatchers(HttpMethod.PUT, URLPOPULATION).hasAuthority("PUT_POPULATION")
	    	.antMatchers(HttpMethod.DELETE, URLPOPULATION).hasAuthority("DELETE_POPULATION")
	    	
	    	.antMatchers(HttpMethod.GET, URLPRIVILEGE).hasAuthority("GET_PRIVILEGE")
	    	.antMatchers(HttpMethod.POST, URLPRIVILEGE).hasAuthority("POST_PRIVILEGE")
	    	.antMatchers(HttpMethod.PUT, URLPRIVILEGE).hasAuthority("PUT_PRIVILEGE")
	    	.antMatchers(HttpMethod.DELETE, URLPRIVILEGE).hasAuthority("DELETE_PRIVILEGE")
	    	
	    	.antMatchers(HttpMethod.GET, URLPROVINCE).hasAuthority("GET_PROVINCE")
	    	.antMatchers(HttpMethod.POST, URLPROVINCE).hasAuthority("POST_PROVINCE")
	    	.antMatchers(HttpMethod.PUT, URLPROVINCE).hasAuthority("PUT_PROVINCE")
	    	.antMatchers(HttpMethod.DELETE, URLPROVINCE).hasAuthority("DELETE_PROVINCE")
	    	
	    	.antMatchers(HttpMethod.GET, URLROLE).hasAuthority("GET_ROLE")
	    	.antMatchers(HttpMethod.POST, URLROLE).hasAuthority("POST_ROLE")
	    	.antMatchers(HttpMethod.PUT, URLROLE).hasAuthority("PUT_ROLE")
	    	.antMatchers(HttpMethod.DELETE, URLROLE).hasAuthority("DELETE_ROLE");
	}
}
