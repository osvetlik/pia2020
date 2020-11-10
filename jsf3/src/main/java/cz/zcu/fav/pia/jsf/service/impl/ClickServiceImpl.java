package cz.zcu.fav.pia.jsf.service.impl;

import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.service.ClickService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service(value = "clickService")
@Slf4j
public class ClickServiceImpl implements ClickService {

	@Getter
	@Setter
	private int count = 0;

	@Override
	public void click() {
		count++;
	}

	@Override
	public void set() {
		// Nothing needed here - the property was set already
		// You can do validation and other business stuff here
		log.info("Clicked: {}", count);
	}

	@Override
	public int setAndGet(int value) {
		this.count = value;
		return this.count;
	}

	@Override
	public int clickAndGet() {
		return ++count;
	}

}
