package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Qualifier(value = "customer")
	@Autowired
	CustomerDetailService userDetailService;
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailService);
		return auth;
	}
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		
		http
			.csrf()
				.disable()
					.authorizeRequests()
	                .requestMatchers("/admin/**").hasRole("ADMIN")
	                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
	                .requestMatchers("/", "/home").permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                	.loginPage("/login")
	                	.permitAll()
	                .and()
	                .logout()
	                .permitAll();
		return http.build();
	                
	}
	

}
