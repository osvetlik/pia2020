package cz.zcu.fav.kiv.pia2021.graphql;

import java.lang.Runtime.Version;

import org.springframework.stereotype.Service;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Service
public class Query implements GraphQLQueryResolver {

	public String helloWorld() {
		return "Hello World!";
	}

	public Version javaVersion() {
		return Runtime.version();
	}

}
