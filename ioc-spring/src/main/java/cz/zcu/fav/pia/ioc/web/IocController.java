package cz.zcu.fav.pia.ioc.web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.zcu.fav.pia.ioc.service.IocService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IocController {

	private final IocService service;

	@RequestMapping("/")
	public List<String> names() {
		return service.getNames();
	}

}
