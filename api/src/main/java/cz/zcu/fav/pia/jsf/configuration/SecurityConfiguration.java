package cz.zcu.fav.pia.jsf.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // enables @PreAuthorize annotations for endpoint restrictions
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_URI = "/login";

	private final UserDetailsService uds;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(auth -> auth
					.antMatchers("/webjars/**").permitAll()
					.antMatchers("/js/**").permitAll()
					.antMatchers(LOGIN_URI).permitAll()
					.anyRequest().authenticated())
			.userDetailsService(this.uds)
			.formLogin()
			.loginPage(LOGIN_URI)
			.defaultSuccessUrl("/")
			.failureUrl(LOGIN_URI)
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}

}
