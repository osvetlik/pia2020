package cz.zcu.fav.pia.jsf.admin.rest.impl;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.zcu.fav.pia.jsf.admin.domain.User;
import cz.zcu.fav.pia.jsf.admin.dto.UserDto;
import cz.zcu.fav.pia.jsf.admin.rest.api.UserApi;
import cz.zcu.fav.pia.jsf.admin.service.api.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@Secured({
	"ROLE_ADMIN"
})
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final UserService userService;

	@PostMapping
	@Override
	public void registerUser(UserDto user) {
		if (userService.validatePasswords(user.getPassword(), user.getPasswordValidation())) {
			userService.registerUser(User.builder()
					.username(user.getUsername())
					.password(user.getPassword())
					.build());
		}
	}

}
