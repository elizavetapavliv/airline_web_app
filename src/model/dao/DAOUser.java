package model.dao;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import model.entity.User;
import model.exception.DAOException;

/**User DAO class*/
public class DAOUser extends DAO {
	
	/**
	 * Constructor
	 * @param entityManagerFactory - entity manager factory
	 */
	public DAOUser(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	 /**
	 * Function for creating new user
	 * @param user - user to create
	 * @throws DAOException - when connection or query execution aren't successful 
	 */
	public void createUser(User user) throws DAOException {
		EntityManager entityManager = null;
	    EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
		} 
		catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
			throw new DAOException("Can't insert user", e);
		}
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}
	
	/**
	 * Function for getting user by email
	 * @param email - user email
	 * @return User object
	 * @throws DAOException - when connection or query execution aren't successful
	 */
	public User readUser(String email) throws DAOException {
		User user = null;
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<User> User_ = metamodel.entity(User.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> userRoot = cq.from(User_);
			cq.select(userRoot).where(cb.equal(userRoot.get("email"), email));
			
			user = entityManager.createQuery(cq).getSingleResult();
		} 
		catch(NoResultException e) {
	        return null;
	    }
		catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			throw new DAOException(/* "Can't obtain user" */exceptionAsString + "\nPassword: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.password"), e);
		} 
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return user;
	}
}
