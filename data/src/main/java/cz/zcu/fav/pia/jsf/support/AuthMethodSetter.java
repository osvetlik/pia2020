package cz.zcu.fav.pia.jsf.support;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import cz.zcu.fav.pia.jsf.auth.service.impl.RequestAuthMethodProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthMethodSetter implements Filter {

	private static final String AUTH_METHOD = "authMethod";
	private final RequestAuthMethodProvider provider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var authMethod = request.getParameter(AUTH_METHOD);
		if (StringUtils.hasText(authMethod)) {
			((HttpServletResponse)response).addCookie(new Cookie(AUTH_METHOD, authMethod));
		}
		else {
			final var r = (HttpServletRequest) request;
			final var cookies = r.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (AUTH_METHOD.equals(c.getName())) {
						authMethod = c.getValue();
						break;
					}
				}
			}
		}
		if (StringUtils.hasText(authMethod)) {
			this.provider.setAuthMethod(authMethod);
		}
		chain.doFilter(request, response);
	}

}
