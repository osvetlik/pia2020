package cz.zcu.fav.pia.ioc.service;

import java.util.List;

// A service interface. This is being used as a dependency wherever the service is needed.
public interface IocService {

	List<String> getNames();

}
