package com.zodiacfiesta.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
	
	@Bean
	SecurityFilterChain config(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());
		
		http.authorizeHttpRequests(a -> a.requestMatchers("/", "/home", "/login", "/register", "/current-runs").permitAll());
		
		http.formLogin(l -> l.loginPage("/login"));
		http.logout((logout) -> logout.logoutSuccessUrl("/home")
										.logoutUrl("/logout")
										.invalidateHttpSession(true)
										.deleteCookies("JESSIONID"));
		
		return http.build();
	}
}
