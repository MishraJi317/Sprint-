package com.example.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	//handler after successful login. i.e which page will be displayed to the user based on their role
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		Set<String> roleSet = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		if (roleSet.contains("ROLE_ADMIN")) {
			
			response.sendRedirect("/admin/");
			
		}
		else {
			response.sendRedirect("/user/");
		}
	}

}
