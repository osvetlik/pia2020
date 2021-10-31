package cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity.SampleEntity;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, UUID>{

}
