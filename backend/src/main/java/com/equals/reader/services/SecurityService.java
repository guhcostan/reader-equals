package com.equals.reader.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}

	public void autoLogin(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext()
				.setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("Auto login %s successfully!", username));
		}
	}

	public void login(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext()
				.setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("Auto login %s successfully!", username));
		}
	}

	public void isLogged() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails != null) {
			throw new AuthorizationServiceException("Usuario não está logado");
		}

	}

	public void logout() {
		SecurityContextHolder.clearContext();
	}
}
