package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import javax.persistence.EntityManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity.SampleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("jpaJpql")
@RequiredArgsConstructor
@Slf4j
public class JpaJpqlAuthServiceImpl implements UserDetailsServiceInternal {

	private final EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Querying.");
		final var q = em.createQuery("SELECT s FROM SampleEntity s", SampleEntity.class);
		final var result = q.getResultList();
		result.stream().forEach(r -> log.info("Found: {}", r));
		throw new UsernameNotFoundException("Not found.");
	}

}
