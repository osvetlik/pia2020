package cz.zcu.fav.pia;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HelloWorldListener implements ServletRequestListener, ServletContextListener {

	public static final String CONTEXT_STARTUP_ATTRIBUTE_NAME = "applicationStart";
	private static final String REQUEST_START_ATTRIBUTE_NAME = "requestStartedDateTime";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute(CONTEXT_STARTUP_ATTRIBUTE_NAME, LocalDateTime.now());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		final var request = sre.getServletRequest();
		final var started = (LocalDateTime) request.getAttribute(REQUEST_START_ATTRIBUTE_NAME);
		final var now = LocalDateTime.now();

		final var millis = ChronoUnit.MILLIS.between(started, now);
		sre.getServletContext().log("Request took " + millis + " ms.");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		sre.getServletRequest().setAttribute(REQUEST_START_ATTRIBUTE_NAME, LocalDateTime.now());
	}

}
