package cz.zcu.fav.pia.jsf.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.zcu.fav.pia.jsf.service.DataService;

@Service("data")
public class DataServiceImpl implements DataService {

	@Override
	public List<String> names() {
		return List.of("Karel", "Václav", "Preis");
	}

}
