package com.example.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.config.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
//	@Autowired
//	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestHeader = request.getHeader("Authorization");
		logger.info("Header : {}", requestHeader);
		String username = null;
		String jwttoken = null;
		if (requestHeader!= null && requestHeader.startsWith("Bearer"))
		{
			jwttoken = requestHeader.substring(7);
			try {
				username = this.jwtHelper.getUsernameFromToken(jwttoken);
				
			} catch (IllegalArgumentException e) {
				logger.info("Illegale arguments");
				e.printStackTrace();
			}catch (ExpiredJwtException e) {
				
				logger.info("Token has been expired");
				e.printStackTrace();
			}
		}
		else {
			logger.info("Invalid header");
		}
		
		if (username!= null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			Boolean validateToken = this.jwtHelper.validateToken(jwttoken, userDetails);
			
			if (validateToken) {
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else {
				logger.info("Validation Failed");
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
