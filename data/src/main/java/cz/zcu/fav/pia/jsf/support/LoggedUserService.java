package cz.zcu.fav.pia.jsf.support;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service("loggedUser")
public class LoggedUserService {

	public User getUser() {
		final var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (User) authentication.getPrincipal();
		}

		return null;
	}

}
