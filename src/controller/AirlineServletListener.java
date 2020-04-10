package controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.eclipse.persistence.internal.security.JCEEncryptor;

/**Airline servlet listener*/
@WebListener
public class AirlineServletListener implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent event) {
	
		ResourceBundle resource = ResourceBundle.getBundle("database");
		String url = resource.getString("url");
		String driver = resource.getString("driver");
		String user = resource.getString("user");
		String pass = resource.getString("password");
		Map map = new HashMap();
		map.put("javax.persistence.jdbc.url",url);
		map.put("javax.persistence.jdbc.user", user);
		map.put("javax.persistence.jdbc.driver", driver);
		String a = "A: ";
		try {
			map.put("javax.persistence.jdbc.password", new JCEEncryptor().encryptPassword(pass));
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			a += sw.toString();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("airline", map);
		event.getServletContext().setAttribute("emf", entityManagerFactory);
		a+="\nPassword: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.password")+
				"\nUser: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.user")+
				"\nUrl: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.url")+
				"\nDriver: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.driver") + "; ";
		event.getServletContext().setAttribute("a", a);
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory)event.getServletContext().getAttribute("emf");
		entityManagerFactory.close();
    }
}