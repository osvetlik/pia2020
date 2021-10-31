package cz.zcu.fav.pia.jsf.auth.service.impl.method.jpa.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*
 * CREATE TABLE SAMPLE (id UUID PRIMARY KEY)
 */
@Entity
@Table(name = "SAMPLE")
@Data
public class SampleEntity {

	@Id
	private UUID id;

}
