package cz.zcu.fav.pia.jsf.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import cz.zcu.fav.pia.jsf.domain.User;
import cz.zcu.fav.pia.jsf.repo.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service("adminService")
@Getter
@Setter
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestScope
public class AdminServiceImpl {

	private final UserRepo repo;
	private final PasswordEncoder encoder;

	private List<User> users;
	private User user;

	private String newPassword1;
	private String newPassword2;

	public List<User> getUsers() {
		if (this.users == null) {
			this.users = this.repo.listUsers();
		}

		return this.users;
	}

	private boolean setPasswordInternal() {
		if (!Objects.equals(newPassword1, newPassword2)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords don't match!", null));
			return false;
		}
		final var encoded = encoder.encode(newPassword1);
		this.user = user.toBuilder()
				.password(encoded)
				.authorities(Set.of(new SimpleGrantedAuthority("ROLE_USER")))
				.build();
		return true;
	}

	public void createUser() {
		this.user = User.builder().build();
	}

	public void saveUser() {
		if (this.user == null) {
			return;
		}
		if (!StringUtils.hasText(this.user.getUsername())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username must not be empty!", null));
			return;
		}

		if (this.setPasswordInternal()) {
			this.repo.saveUser(user);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "User successfully saved!", null));
		}
	}

	public User getUser() {
		if (this.user == null) {
			this.user = new User();
		}

		return this.user;
	}

	public boolean isHasUsername() {
		if (this.user == null) {
			return false;
		}
		if (StringUtils.hasText(this.user.getUsername())) {
			return true;
		}
		return false;
	}

	public void deleteUser() {
		if (this.user != null) {
			repo.deleteUser(user);
			this.users = null;
		}
	}

}
