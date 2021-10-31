package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("plainJdbc")
@RequiredArgsConstructor
@Slf4j
public class PlainJdbcAuthServiceImpl implements UserDetailsServiceInternal {

	private final DataSource ds;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Logging in as {}", username);
		try (
				final var connection = ds.getConnection();
				final var statement =
						connection.prepareStatement(
								"SELECT au.password, ar.rolename"
								+ " FROM AUTH_USER au"
								+ " LEFT JOIN AUTH_USER_ROLE aur ON aur.user_id = au.id"
								+ " LEFT JOIN AUTH_ROLE ar ON aur.role_id = ar.id"
								+ " WHERE au.username = ?");
		) {
			statement.setString(1, username);
			final var result = statement.executeQuery();
			if (!result.next()) {
				throw new UsernameNotFoundException("Not found");
			}

			final var userBuilder = User.builder()
					.username(username)
					.password(result.getString(1));

			userBuilder.authorities(getRoles(result));

			return userBuilder.build();
		} catch (SQLException e) {
			throw new UsernameNotFoundException("Not found", e);
		}
	}

	private List<GrantedAuthority> getRoles(ResultSet rs) throws SQLException {
		final var roles = new ArrayList<GrantedAuthority>();

		do {
			final var role = rs.getString(2);
			if (StringUtils.hasText(role)) {
				roles.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
		} while (rs.next());

		return roles;
	}

}
