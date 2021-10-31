package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity.RoleEntity;
import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("jpaJpql")
@RequiredArgsConstructor
@Slf4j
public class JpaJpqlAuthServiceImpl implements UserDetailsServiceInternal {

	private final EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Authenticating {}.", username);
		final var q = em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class);
		q.setParameter("username", username);
		try {
			final var userEntity = q.getSingleResult();

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
		catch (Exception e) {
			throw new UsernameNotFoundException("Not found.", e);
		}
	}

}
