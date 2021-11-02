package cz.zcu.fav.pia.jsf.admin.jsf;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import cz.zcu.fav.pia.jsf.admin.domain.User;
import cz.zcu.fav.pia.jsf.admin.service.api.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
@Data
public class UserJsfBean {

	private final UserService userService;

	private String username;
	private String password;
	private String passwordValidation;

	public void registerUser() {
		if (!userService.validatePasswords(password, passwordValidation)) {

			userService.registerUser(User.builder()
					.username(username)
					.password(password)
					.build());
		}
	}

}
