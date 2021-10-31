package cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "AUTH_ROLE")
@Data
public class RoleEntity {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false)
	private String rolename;

}
