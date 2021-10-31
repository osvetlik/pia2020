package cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

	Optional<UserEntity> findByUsername(String username);

}
