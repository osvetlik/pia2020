package cz.zcu.fav.pia.jsf.service.configuration;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.DispatchType;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.SendStatus;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration extends HttpConfigurationProvider {

	@Override
	public org.ocpsoft.rewrite.config.Configuration getConfiguration(ServletContext context) {
		return ConfigurationBuilder.begin()
				.addRule().when(DispatchType.isRequest().and(Path.matches("/{path}")))
				.perform(SendStatus.code(404))
				.where("path").matches(".*\\.xhtml$")
				.addRule(Join.path("/").to("/index.xhtml"));
	}

	@Override
	public int priority() {
		return 10;
	}

}
