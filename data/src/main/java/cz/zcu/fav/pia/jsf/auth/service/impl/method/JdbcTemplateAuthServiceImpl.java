package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
		final var result = jdbcTemplate.queryForList(
				"SELECT au.password, ar.rolename"
				+ " FROM AUTH_USER au"
				+ " LEFT JOIN AUTH_USER_ROLE aur ON aur.user_id = au.id"
				+ " LEFT JOIN AUTH_ROLE ar ON aur.role_id = ar.id"
				+ " WHERE au.username = ?",
				username);

		if (result.isEmpty()) {
			throw new UsernameNotFoundException("Not found.");
		}

		final var password = result.get(0).get("password");

		final var userBuilder = User.builder()
				.username(username)
				.password((String) password);

		userBuilder.authorities(result.stream()
				.map(u -> new SimpleGrantedAuthority("ROLE_" + (String) u.get("rolename")))
				.collect(Collectors.toList()));

		return userBuilder.build();

	}

}
