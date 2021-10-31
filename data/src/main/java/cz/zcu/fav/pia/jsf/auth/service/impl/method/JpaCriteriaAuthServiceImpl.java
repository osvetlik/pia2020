package cz.zcu.fav.pia.jsf.auth.service.impl.method;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;

import org.springframework.beans.factory.InitializingBean;
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

@Service("jpaCriteria")
@RequiredArgsConstructor
@Slf4j
public class JpaCriteriaAuthServiceImpl implements UserDetailsServiceInternal, InitializingBean {

	private final EntityManager em;

	private CriteriaQuery<UserEntity> queryTemplate;
	private ParameterExpression<String> param;

	@Override
	public void afterPropertiesSet() throws Exception {
		final var cb = em.getCriteriaBuilder();
		this.param = cb.parameter(String.class);
		this.queryTemplate = cb.createQuery(UserEntity.class);
		final var root = queryTemplate.from(UserEntity.class);
		this.queryTemplate.select(root).where(cb.equal(root.get("username"), param));
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Authenticating {}.", username);

		try {
			final var query = em.createQuery(this.queryTemplate);
			query.setParameter(this.param, username);

			final var userEntity = query.getSingleResult();

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
			log.error("Error authenticating {}!", username, e);
			throw new UsernameNotFoundException("Not found.", e);
		}
	}

}
