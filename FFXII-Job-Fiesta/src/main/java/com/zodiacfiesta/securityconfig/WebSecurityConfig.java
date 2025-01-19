package com.zodiacfiesta.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zodiacfiesta.repositories.UserRepository;
import com.zodiacfiesta.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer{
	
	private UserRepository userRepo;
	
	@Bean
	UserDetailsService userDetailsService(UserRepository userRepo) {
		return new UserService(userRepo);
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService(userRepo));
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	
}
