package cz.zcu.fav.pia.jsf.repo;

import java.util.List;

import cz.zcu.fav.pia.jsf.domain.User;

public interface UserRepo {

	public User findUser(String login);

	public void saveUser(User user);

	public List<User> listUsers();

	public boolean hasUser(String login);

	public void deleteUser(User user);

}
