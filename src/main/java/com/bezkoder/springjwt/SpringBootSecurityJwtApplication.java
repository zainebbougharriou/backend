package com.bezkoder.springjwt;

import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}


	public static UserDetailsImpl getCurrentUser() {
		Authentication authCtx = SecurityContextHolder.getContext().getAuthentication();
		return (authCtx == null) ? fakeUser() : (UserDetailsImpl) authCtx.getPrincipal();
	}


	private static UserDetailsImpl fakeUser() {
		UserDetailsImpl fake = new UserDetailsImpl();

		return fake;
	}
}
