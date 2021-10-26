package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("plainJdbc")
@RequiredArgsConstructor
@Slf4j
public class PlainJdbcAuthServiceImpl implements UserDetailsServiceInternal {

	private final DataSource ds;
	private final PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) {
		try (
				final var connection = ds.getConnection();
				final var statement =
						connection.prepareStatement("select * from users where username = ?");
			) {

			statement.setString(1, username);
			final var result = statement.executeQuery();
			if (!result.next()) {
				throw new UsernameNotFoundException("Not found");
			}
			return User.builder()
					.username(username)
					.password(encoder.encode(result.getString(2)))
					.authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
					.build();
		} catch (SQLException e) {
			throw new UsernameNotFoundException("Not found", e);
		}
	}

}
