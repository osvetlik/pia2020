package cz.zcu.fav.pia.ioc.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.ioc.repo.fake.FakeRepo;
import cz.zcu.fav.pia.ioc.service.IocService;
import lombok.RequiredArgsConstructor;

/*
 * Spring Service bean, implementation of the IocService interface. Spring finds
 * this implementation when scanning the project, creates a singleton instance
 * and injects it wherever the service (through the interface) is needed.
 */
@Service
@RequiredArgsConstructor // Lombok annotation to create a required args constructor
public class IocServiceImpl implements IocService {

	/*
	 * Repository to access the data. We use the implementation class here just to show
	 * that it is also possible. However, this means we don't have the possibility
	 * to use different implementations activated for example by profiles.
	 */
	private final FakeRepo repo;

	@Override
	public List<String> getNames() {
		return Collections.unmodifiableList(repo.loadNames());
	}

}
