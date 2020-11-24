package cz.zcu.fav.pia.jsf.auth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Service("authMethodProvider")
@RequestScope
@Data
public class RequestAuthMethodProvider {

	private String authMethod = "fake";

}
