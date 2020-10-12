package cz.zcu.fav.pia.ioc.web;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

@WebServlet({
	"/hello"
})
public class IocServlet extends HttpServlet {

	private static final long serialVersionUID = 8197401106140947739L;

	/*
	 * This is not possible in regular servlets, but thanks to Spring, we can
	 * use dependency injection even here.
	 *
	 * This annotation tells Spring to inject the value of default-from property
	 * defined in the application.properties.
	 */
	@Value("${default-from}")
	private String defaultFrom;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final var from = Objects.requireNonNullElse(req.getParameter("from"), defaultFrom);
		resp.getWriter()
			.append("Hello World from ")
			.append(from)
			.append("!\n");
	}

}
