package cz.zcu.fav.pia.jsf.rest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.zcu.fav.pia.jsf.service.ClickService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClickController {

	private final ClickService service;

	@PutMapping("/click")
	public int click() {
		return service.clickAndGet();
	}

	@PutMapping("/set")
	public int set(@RequestBody int value) {
		return service.setAndGet(value);
	}

}
