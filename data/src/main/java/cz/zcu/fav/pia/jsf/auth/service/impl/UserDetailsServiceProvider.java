package cz.zcu.fav.pia.jsf.auth.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("userDetailsServiceProvider")
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceProvider {

	@Getter
	private final Map<String, UserDetailsServiceInternal> services;

	private UserDetailsServiceInternal defaultService;

	public UserDetailsService userDetailService(String authMethod) {
		log.info("Requested {} authentication service.", authMethod);
		return this.services.getOrDefault(authMethod, this.defaultService);
	}

	@PostConstruct
	private void initDefault() {
		this.defaultService = this.services.get("fake");
		this.services
			.entrySet()
			.stream()
			.forEach(e -> log.info("{}", e.getKey()));
	}

}
