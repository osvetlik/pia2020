package cz.zcu.fav.pia.jsf.admin.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {

	String username;
	String password;

}
