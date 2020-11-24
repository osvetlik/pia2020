package cz.zcu.fav.pia.jsf.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.repo.SampleRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
		SampleRepository.class
})
@EnableTransactionManagement
public class JpaConfiguration {

}
