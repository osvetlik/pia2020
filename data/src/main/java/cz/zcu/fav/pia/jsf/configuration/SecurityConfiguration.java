package cz.zcu.fav.pia.jsf.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cz.zcu.fav.pia.jsf.auth.service.impl.RequestAuthMethodProvider;
import cz.zcu.fav.pia.jsf.support.AuthMethodSetter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		jsr250Enabled = true,
		prePostEnabled = true,
		securedEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_URI = "/login";

	private final UserDetailsService uds;
	private final RequestAuthMethodProvider provider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(new AuthMethodSetter(provider), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests(auth -> auth
					.antMatchers("/webjars/**").permitAll()
					.antMatchers("/js/**").permitAll()
					.antMatchers(LOGIN_URI).permitAll()
					.antMatchers("/javax.faces.resource/**").permitAll()
					.antMatchers("/public**").permitAll()
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
