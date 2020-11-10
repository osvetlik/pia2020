package cz.zcu.fav.pia.jsf.service.impl;

import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import cz.zcu.fav.pia.jsf.repo.UserRepo;
import cz.zcu.fav.pia.jsf.service.ChangePasswordService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service("changePasswordService")
@Getter
@Setter
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestScope
public class ChangePasswordServiceImpl implements ChangePasswordService {

	private final UserRepo repo;

	private String oldPassword;
	private String newPassword1;
	private String newPassword2;

	@Override
	public void changePassword(String old, String new1, String new2) {
		if (!Objects.equals(new1, new2)) {
			FacesContext.getCurrentInstance().addMessage(":changePasswordForm:newPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "New passwords don't match!", null));
		}
	}

	public void changePassword() {
		this.changePassword(oldPassword, newPassword1, newPassword2);
	}

}
