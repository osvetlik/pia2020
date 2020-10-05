package cz.zcu.fav.pia;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({
	"/hello"
})
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 2882987200487771166L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final var from = req.getParameter("from");
		final var sb = new StringBuilder("Hello world");
		if (from != null) {
			sb.append(" from ").append(from);
		}
		sb.append('!');

		final var writer = resp.getWriter();
		writer.write(sb.toString());
		writer.write("\nContext started at: ");
		writer.write(req.getServletContext().getAttribute(HelloWorldListener.CONTEXT_STARTUP_ATTRIBUTE_NAME).toString());
	}

}
