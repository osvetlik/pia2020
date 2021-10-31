package cz.zcu.fav.pia.jsf.api.impl;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cz.zcu.fav.pia.api.generated.UserApi;
import cz.zcu.fav.pia.model.generated.UpdatableUserInfoDTO;
import cz.zcu.fav.pia.model.generated.UserDetailDTO;
import cz.zcu.fav.pia.model.generated.UsersListDTO;

@RestController
public class UserApiController implements UserApi {

	@Override
	public ResponseEntity<UserDetailDTO> getUser(UUID userId) {
		// TODO Auto-generated method stub
		return UserApi.super.getUser(userId);
	}

	@Override
	public ResponseEntity<UsersListDTO> listUsers(@Min(0) @Valid Long offset, @Min(10) @Max(100) @Valid Integer limit) {
		// TODO Auto-generated method stub
		return UserApi.super.listUsers(offset, limit);
	}

	@Override
	public ResponseEntity<Void> updateUser(UUID userId, @Valid UpdatableUserInfoDTO updatableUserInfoDTO) {
		// TODO Auto-generated method stub
		return UserApi.super.updateUser(userId, updatableUserInfoDTO);
	}

}
