package com.example.plan.config;

import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class InjectAuditor implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor () {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		if (securityContext == null) {
			return null;
		}
		if (securityContext.getAuthentication() == null) {
			return null;
		}

		String loginUserName = securityContext.getAuthentication().getName();
		Optional<String> name = Optional.ofNullable(loginUserName);

		return name;
	}
}
