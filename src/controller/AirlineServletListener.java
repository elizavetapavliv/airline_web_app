package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.internal.security.JCEEncryptor;

/**Airline servlet listener*/
@WebListener
public class AirlineServletListener implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent event) {	
	/*	ResourceBundle resource = ResourceBundle.getBundle("database");
		String url = resource.getString("url");
		String driver = resource.getString("driver");
		String user = resource.getString("user");
		String pass = resource.getString("password");
		Map<String, String> map = new HashMap<>();
		map.put(PersistenceUnitProperties.JDBC_DRIVER, driver);
		map.put(PersistenceUnitProperties.JDBC_URL, url);
		map.put(PersistenceUnitProperties.JDBC_USER, user);*/
		/*try {
			pass = new JCEEncryptor().encryptPassword(pass);
		} catch (Exception e) {}
		map.put(PersistenceUnitProperties.JDBC_PASSWORD, pass);*/
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Airline");
		
		try {
			entityManagerFactory.getProperties().put(PersistenceUnitProperties.JDBC_PASSWORD, new JCEEncryptor().encryptPassword((String)entityManagerFactory.getProperties().get(PersistenceUnitProperties.JDBC_PASSWORD)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event.getServletContext().setAttribute("emf", entityManagerFactory);
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory)event.getServletContext().getAttribute("emf");
		entityManagerFactory.close();
    }
}