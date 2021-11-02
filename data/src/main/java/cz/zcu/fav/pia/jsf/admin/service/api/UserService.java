package cz.zcu.fav.pia.jsf.admin.service.api;

import cz.zcu.fav.pia.jsf.admin.domain.User;

public interface UserService {

	void registerUser(User user);
	boolean validatePasswords(String password, String passwordValidation);

}
