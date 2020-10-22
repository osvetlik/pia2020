package cz.zcu.fav.pia.jsf.servlet;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TimeListener implements ServletRequestListener, ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("applicationTime", new Date());
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		sre.getServletRequest().setAttribute("requestTime", new Date());
	}



}
