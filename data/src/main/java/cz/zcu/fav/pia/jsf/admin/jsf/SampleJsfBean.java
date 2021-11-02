package cz.zcu.fav.pia.jsf.admin.jsf;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Service
@RequestScope
@Data
public class SampleJsfBean {

	private String name;

	public String helloWorld() {
		return "Hello World from " + name;
	}

	public void submit() {
		// Nothing here
	}

}
