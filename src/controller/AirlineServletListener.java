package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
	
		/*ResourceBundle resource = ResourceBundle.getBundle("database");
		String url = resource.getString("url");
		String driver = resource.getString("driver");
		String user = resource.getString("user");
		String pass = resource.getString("password");*/
		Map map = new HashMap();
		map.put("javax.persistence.jdbc.url","jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/data9uq0cgo999?sslmode=require" );
		map.put("javax.persistence.jdbc.user", "apugtfccgqifho");
		map.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		map.put("javax.persistence.jdbc.password", "8c48dac59ebe215e4d67521b3758302a29bf6a6ac07eb969472fa64b12cb81dc");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Airline", map);
		event.getServletContext().setAttribute("emf", entityManagerFactory);
		event.getServletContext().setAttribute("a", "a: \nPassword: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.password")+
				"\nUser: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.user")+
				"\nUrl: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.url")+
				"\nDriver: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.driver") + "; ");
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory)event.getServletContext().getAttribute("emf");
		entityManagerFactory.close();
    }
}