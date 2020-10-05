package cz.zcu.fav.pia.ioc.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.ioc.repo.fake.FakeRepo;
import cz.zcu.fav.pia.ioc.service.IocService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IocServiceImpl implements IocService {

	private final FakeRepo repo;

	@Override
	public List<String> getNames() {
		return Collections.unmodifiableList(repo.loadNames());
	}

}
