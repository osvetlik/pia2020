package cz.zcu.fav.pia.ioc.repo.fake;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class FakeRepo {

	public List<String> loadNames() {
		return List.of("Ada", "Pascal", "John", "Žluťoučký", "Koníček");
	}

}
