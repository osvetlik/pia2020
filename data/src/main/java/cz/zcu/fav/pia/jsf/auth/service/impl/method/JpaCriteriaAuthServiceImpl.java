package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import javax.persistence.EntityManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("jpaCriteria")
@RequiredArgsConstructor
@Slf4j
public class JpaCriteriaAuthServiceImpl implements UserDetailsServiceInternal {

	private final EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) {
		em.getCriteriaBuilder(); // Follow the tutorial
		log.info("Have the criteria builder.");
		throw new UsernameNotFoundException("Not found.");
	}

}
