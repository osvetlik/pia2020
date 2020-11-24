package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import lombok.RequiredArgsConstructor;

@Service("fake")
@RequiredArgsConstructor
public class FakeAuthServiceImpl implements UserDetailsServiceInternal {

	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";
	private static final String PASSWORD = "heslo";

	private final Map<String, UserDetails> users = new HashMap<>();

	private final PasswordEncoder encoder;

	private void addUser(String username, String password, String... roles) {
		Set<GrantedAuthority> authorities = new HashSet<>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		}

		final var user = User.builder()
				.username(username)
				.password(encoder.encode(password))
				.authorities(authorities)
				.build();
		this.users.put(username, user);
	}

	@PostConstruct
	private void setup() {
		addUser("user", PASSWORD, USER);
		addUser("admin", PASSWORD, ADMIN);
		addUser("god", PASSWORD, ADMIN, USER);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!this.users.containsKey(username)) {
			throw new UsernameNotFoundException(username);
		}
		return User.withUserDetails(this.users.get(username)).build();
	}

}
