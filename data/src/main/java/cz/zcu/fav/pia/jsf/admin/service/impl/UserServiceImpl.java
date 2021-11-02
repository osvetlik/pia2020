package cz.zcu.fav.pia.jsf.admin.service.impl;

import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.admin.domain.User;
import cz.zcu.fav.pia.jsf.admin.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public void registerUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validatePasswords(String password, String passwordValidation) {
		// TODO Auto-generated method stub
		return false;
	}

}
