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

/*
 * A WebSecurityConfig class that is used to check with the database to make sure credentials are correct. 
 * It also lists which pages are available for all users without having to log in.
 * What pages are available when logged in with specific roles.
 * Where to take the user when they login successfully.
 * Where the user is directed after they log out successfully.
 * 
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer{
	
	private UserRepository userRepo;
	
	/*
	 * Creates a UserService object used to retrieve user information from the db
	 * @param userRepo: a UserRepository object userRepo which is used to retrieve the user information
	 * @return: returns a new UserService object that uses the userRepo object
	 */
	@Bean
	UserDetailsService userDetailsService(UserRepository userRepo) {
		return new UserService(userRepo);
	}
	
	/*
	 * Sets the password encoder to be used when storing the password
	 * @param: no parameters
	 * @return: returns a new BCryptPasswordEncoder object
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * Sets the authentication provider to be used when checking if the user is valid
	 * @param: no parameters
	 * @return authProvider: returns the authentication provider that will be used for thie application
	 */
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService(userRepo));
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	/*
	 * Configuration method using the authentication provider created earlier in the class to see what pages are ok to view for the user
	 * Lists the pages that are viewable depending on if the user is logged in, logged out, and depending on role when logged in
	 * @param http: an HttpSecurity object for configuring http requests
	 * @return http: returns a built http object that will authorize or deny requests depending on the user
	 */
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
