package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Cannot name this bean jdbcTemplate as Spring Data already uses this name
@Service("jdbcTemplateAuth")
@RequiredArgsConstructor
@Slf4j
public class JdbcTemplateAuthServiceImpl implements UserDetailsServiceInternal {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Querying.");
		final var result = jdbcTemplate.queryForList("SELECT 1 WHERE 1=?", 1);

		result.stream().forEach(r -> log.info("Found: {}", r));

		throw new UsernameNotFoundException("Not found.");
	}

}
