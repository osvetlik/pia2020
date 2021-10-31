package cz.zcu.fav.pia.jsf.auth.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("requestAuthService")
@Primary
@RequiredArgsConstructor
@Slf4j
public class RequestAuthService implements UserDetailsService {

	private final UserDetailsServiceProvider provider;
	private final RequestAuthMethodProvider authMethodProvider;

	@Override
	public UserDetails loadUserByUsername(String username) {
		final var authMethod = authMethodProvider.getAuthMethod();
		log.info("Authenticating using {}.", authMethod);
		final var user = provider.userDetailService(authMethod).loadUserByUsername(username);
		log.info("Found user {}.", user);
		return user;
	}

}
