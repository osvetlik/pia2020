package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
		try (final var connection = ds.getConnection();) {
			final var statement = connection.prepareStatement("select 1");
			final var result = statement.executeQuery();
			log.info("Result: {}", result);
		} catch (SQLException e) {
			throw new UsernameNotFoundException("Not found", e);
		}
		throw new UsernameNotFoundException("Not found");
	}

}
