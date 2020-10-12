package cz.zcu.fav.pia.ioc.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.ioc.service.IocService;

@Service("secondImpl")
public class IocSecondImpl implements IocService {

	@Override
	public List<String> getNames() {
		return Collections.emptyList();
	}

}
