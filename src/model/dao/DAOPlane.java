package model.dao;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import model.entity.Flight;
import model.exception.DAOException;

/**Plane DAO class*/
public class DAOPlane{
	
	/**Entity manager*/
	private EntityManager entityManager;
	
	/**
	 * Constructor
	 * @param entityManager - entity manager
	 * @throws NamingException 
	 */
	public DAOPlane(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Function for updating plane flight
	 * @param flightId - flight id
	 * @throws DAOException - when connection or query execution aren't successful 
	 */
	public void updatePlaneFlight(int flightId) throws DAOException {
	/*	EntityManager entityManager = null;
		EntityTransaction transaction = null;	*/
		try {
		//	entityManager = entityManagerFactory.createEntityManager();
			//transaction = entityManager.getTransaction();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Flight> Flight_ = metamodel.entity(Flight.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaUpdate<Flight> cu = cb.createCriteriaUpdate(Flight.class);
			Root<Flight> flight = cu.from(Flight_);
			cu.set("plane", null).where(cb.equal(flight.get("id"), flightId));
		
		//	transaction.begin();	
			entityManager.createQuery(cu).executeUpdate();
			//transaction.commit();
		} 
		catch (Exception e) {
		/*	if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}*/
			throw new DAOException("Can't update plane flight to null", e);
		}
	/*	finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}*/
	}
}
