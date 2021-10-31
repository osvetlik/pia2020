package cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "AUTH_USER")
@Data
public class UserEntity {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "AUTH_USER_ROLE",
			joinColumns = {
					@JoinColumn(name = "user_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "role_id")
			}
	)
	private Set<RoleEntity> roles = Collections.emptySet();

}
