package cz.zcu.fav.pia.jsf.repo;

import cz.zcu.fav.pia.jsf.domain.User;

public interface UserRepo {

	public User findUser(String login);

	public void saveUser(User user);

}
