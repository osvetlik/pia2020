package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.auth.service.UserDetailsServiceInternal;
import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.repo.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("jpaSpringDataRepo")
@RequiredArgsConstructor
@Slf4j
public class JpaSpringDataRepoAuthServiceImpl implements UserDetailsServiceInternal {

	private final SampleRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Number of sample entities: {}", repo.count());
		throw new UsernameNotFoundException("Not found.");
	}

}
