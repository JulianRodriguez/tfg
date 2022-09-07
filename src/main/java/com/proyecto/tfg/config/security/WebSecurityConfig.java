package com.proyecto.tfg.config.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor_={@Autowired})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String URLRESTAURANT = "/restaurant";
	private static final String URLUSER = "/user";
	private static final String URLPRIVILEGE = "/privilege";
	private static final String URLROLE = "/role";
	private static final String URLPRODUCT = "/product";
	private static final String URLCHECKUSER = "/check_user";
	private static final String URLCHECKEMAIL = "/check_email";
	private static final String URLCHECKPASS = "/check_pass";
	private static final String URLEMAIL = "/email";
	public static final String ID = "/{^[\\d]$}";

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
			.logout()
			.logoutUrl("/exit");

//		http
//			.cors().and();

		http
	    	.csrf().disable()
	    	.exceptionHandling()
	    	.authenticationEntryPoint(restAuthenticationEntryPoint);

		http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/logout").permitAll()

                .antMatchers(HttpMethod.GET, URLRESTAURANT).hasAuthority("GET_RESTAURANT")
                .antMatchers(HttpMethod.GET, URLRESTAURANT + ID).hasAuthority("GET_RESTAURANT")
                .antMatchers(HttpMethod.POST, URLRESTAURANT).hasAuthority("POST_RESTAURANT")
                .antMatchers(HttpMethod.PUT, URLRESTAURANT).hasAuthority("PUT_RESTAURANT")
                .antMatchers(HttpMethod.PUT, URLRESTAURANT + ID).hasAuthority("PUT_RESTAURANT")
                .antMatchers(HttpMethod.DELETE, URLRESTAURANT).hasAuthority("DELETE_RESTAURANT")

                .antMatchers(HttpMethod.GET, URLPRODUCT).hasAuthority("GET_PRODUCT")
                .antMatchers(HttpMethod.GET, URLPRODUCT + ID).hasAuthority("GET_PRODUCT")

                .antMatchers(HttpMethod.GET, URLCHECKUSER).permitAll()
                .antMatchers(HttpMethod.GET, URLCHECKEMAIL).permitAll()
                .antMatchers(HttpMethod.GET, URLCHECKPASS).permitAll()

                .antMatchers(HttpMethod.PUT, URLEMAIL).hasAuthority("PUT_EMAIL")

                .antMatchers(HttpMethod.GET, URLUSER).hasAuthority("GET_USER")
                .antMatchers(HttpMethod.POST, URLUSER).hasAuthority("POST_USER")
                .antMatchers(HttpMethod.PUT, URLUSER).hasAuthority("PUT_USER")
                .antMatchers(HttpMethod.DELETE, URLUSER).hasAuthority("DELETE_USER")
                .antMatchers(HttpMethod.GET, URLUSER + ID ).hasAuthority("GET_USER")
			.antMatchers(HttpMethod.GET, URLUSER + URLCHECKPASS ).hasAuthority("GET_USER")

                .antMatchers(HttpMethod.POST, URLUSER + ID + URLRESTAURANT).hasAuthority("POST_RESTAURANT_USER")
                .antMatchers(HttpMethod.GET, URLUSER + ID + URLRESTAURANT + ID).hasAuthority("GET_RESTAURANT_USER")
                .antMatchers(HttpMethod.DELETE, URLUSER + ID + URLRESTAURANT + ID).hasAuthority("DELETE_RESTAURANT_USER")
                .antMatchers(HttpMethod.GET, URLUSER + ID + URLRESTAURANT).hasAuthority("GET_RESTAURANT_USER")


                .antMatchers(HttpMethod.GET, URLRESTAURANT + ID + URLPRODUCT + ID).hasAuthority("GET_PRODUCT_RESTAURANT")
                .antMatchers(HttpMethod.GET, URLRESTAURANT + ID + URLPRODUCT).hasAuthority("GET_PRODUCT_RESTAURANT")
                .antMatchers(HttpMethod.POST, URLRESTAURANT + ID + URLPRODUCT).hasAuthority("POST_PRODUCT_RESTAURANT")

                .antMatchers(HttpMethod.GET, URLPRIVILEGE).hasAuthority("GET_PRIVILEGE")
                .antMatchers(HttpMethod.POST, URLPRIVILEGE).hasAuthority("POST_PRIVILEGE")
                .antMatchers(HttpMethod.PUT, URLPRIVILEGE).hasAuthority("PUT_PRIVILEGE")
                .antMatchers(HttpMethod.DELETE, URLPRIVILEGE).hasAuthority("DELETE_PRIVILEGE")

                .antMatchers(HttpMethod.GET, URLROLE).hasAuthority("GET_ROLE")
                .antMatchers(HttpMethod.POST, URLROLE).hasAuthority("POST_ROLE")
                .antMatchers(HttpMethod.PUT, URLROLE).hasAuthority("PUT_ROLE")
                .antMatchers(HttpMethod.DELETE, URLROLE).hasAuthority("DELETE_ROLE");
	}

//    @Bean
//    public HttpFirewall defaultHttpFirewall() {
//        return new DefaultHttpFirewall();
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
//    }

	@Bean
	public JavaMailSender javaMailSender() {
		return new JavaMailSenderImpl();
	}

}
