package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity.RoleEntity;
import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("jpaSpringDataRepo")
@RequiredArgsConstructor
@Slf4j
public class JpaSpringDataRepoAuthServiceImpl implements UserDetailsServiceInternal {

	private final UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Authenticating {}.", username);
		final var userEntity = repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Not found."));

		final var userBuilder = User.builder()
				.username(username)
				.password(userEntity.getPassword());

		userBuilder.authorities(userEntity.getRoles().stream()
				.map(RoleEntity::getRolename)
				.map(r -> "ROLE_" + r)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toSet()));

		return userBuilder.build();
	}

}
