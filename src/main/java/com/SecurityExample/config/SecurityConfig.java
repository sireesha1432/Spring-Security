package com.SecurityExample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTFilter jwtFilter;

	@Bean
	SecurityFilterChain defauSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeHttpRequests().requestMatchers("/Account","/Users").authenticated()
//		.requestMatchers("/Contact").permitAll()
//		.and().formLogin()
//		.and().httpBasic();
//		
//		return httpSecurity.build();

		httpSecurity.csrf(Customizer -> Customizer.disable());
		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/register", "/Login", "/Contact")
				.permitAll().anyRequest().authenticated());
		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
    
	/*
	 * Authentication manger 
	 * used to Authenticate using Authrntication Object (From Username&PasswordAuthentication Method())
	 */	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	/*  
	 * Authentocation provider
	 * used to provide the user Credentials from DB.
	 * Usig UserdetailsService Method  it is implementing user object (userIMPL CLASS)
	 */

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

		return provider;

	}
	
	

}
