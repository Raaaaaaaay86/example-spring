package com.example.plan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler customDeniedHandler;

	@Override
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder bcPasswordEncoder = new BCryptPasswordEncoder();

		auth.inMemoryAuthentication().passwordEncoder(bcPasswordEncoder).withUser("admin")
				.password(bcPasswordEncoder.encode("eee333")).roles("ADMIN").and().withUser("ken")
				.password(bcPasswordEncoder.encode("eee333")).roles("MEMBER");
	}

	@Override
	public void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/course/**").hasRole("ADMIN").anyRequest().authenticated().and()
				.httpBasic().and().exceptionHandling().accessDeniedHandler(customDeniedHandler).and().csrf().disable();
	}
}
