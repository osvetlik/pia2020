package cz.zcu.fav.pia.jsf.admin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UserDto {

	private String username;
	private String password;
	private String passwordValidation;

}
