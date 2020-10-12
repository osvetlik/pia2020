package cz.zcu.fav.pia.ioc.service.impl;

import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.ioc.service.IocService2;

@Service
public class IocService2Impl implements IocService2 {

	@Override
	public String helloWorld() {
		return "Hello World!";
	}

}
