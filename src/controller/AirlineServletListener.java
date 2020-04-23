package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**Airline servlet listener*/
@WebListener
public class AirlineServletListener implements ServletContextListener {

	 @PersistenceContext(unitName = "Airline")  
     private EntityManager ctxManager;  
	
	@Override
    public void contextInitialized(ServletContextEvent event) {	
		event.getServletContext().log("CTX: " + ctxManager);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Airline");
		event.getServletContext().log("CTXEMF: " + entityManagerFactory);
		event.getServletContext().setAttribute("emf", entityManagerFactory);
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory)event.getServletContext().getAttribute("emf");
		entityManagerFactory.close();
    }
}