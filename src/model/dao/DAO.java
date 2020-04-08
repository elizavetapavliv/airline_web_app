package model.dao;

import javax.persistence.EntityManagerFactory;

/**Data Access Object class*/
public class DAO {
	
	/**Entity manager factory*/
	protected EntityManagerFactory entityManagerFactory;

	/**
	 * Constructor
	 * @param entityManagerFactory - entity manager factory
	 */
	public DAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	/**
	 * Getter for entity manager factory
	 * @return EntityManagerFactory object
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
