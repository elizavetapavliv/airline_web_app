package controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**Airline servlet listener*/
@WebListener
public class AirlineServletListener implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent event) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Airline");
		event.getServletContext().setAttribute("emf", entityManagerFactory);
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory)event.getServletContext().getAttribute("emf");
		entityManagerFactory.close();
    }
}