package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import model.entity.Brigade;
import model.entity.Flight;
import model.exception.DAOException;

/**Brigade DAO Class*/
public class DAOBrigade extends DAO {

	/**
	 * Constructor
	 * @param entityManagerFactory - entity manager factory
	 */
	public DAOBrigade(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}
	
	/**
	 * Function for updating brigade
	 * @param brigadeId - brigade id
	 * @param field - field of brigade entity
	 * @param value - new field value
	 * @throws DAOException - when connection or query execution aren't successful 
	 */
	public void updateBrigade(int brigadeId, String field, String value) throws DAOException {
		EntityManager entityManager = null;
	    EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Brigade> Brigade_ = metamodel.entity(Brigade.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaUpdate<Brigade> cu = cb.createCriteriaUpdate(Brigade.class);
			Root<Brigade> brigade = cu.from(Brigade_);
			cu.set(field, value).where(cb.equal(brigade.get("id"), brigadeId));
		
			transaction.begin();	
			entityManager.createQuery(cu).executeUpdate();
			transaction.commit();
		} 
		catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
			throw new DAOException("Can't update brigade " + field, e);
		}
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}


	/**
	 * Function for getting brigade of flight
	 * @param id - flight id
	 * @return Brigade object
	 * @throws DAOException -  - when connection or query execution aren't successful 
	 */
	public Brigade readBrigadeByFlight(int id) throws DAOException {
		Brigade brigade = new Brigade();
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();	
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Brigade> Brigade_ = metamodel.entity(Brigade.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Brigade> cq = cb.createQuery(Brigade.class);
			Root<Brigade> brigadeRoot = cq.from(Brigade_);
			cq.select(brigadeRoot).where(cb.equal(brigadeRoot.get("flight").get("id"), id));
			
			brigade = entityManager.createQuery(cq).getSingleResult();
		} 
		catch(NoResultException e) {
	        return null;
	    }
		catch (Exception e) {
			throw new DAOException("Can't obtain brigade", e);
		} 
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return brigade.getId() == 0 ? null : brigade;
	}
	
	/**
	 * Function for updating brigade flight
	 * @param flightId - flight id
	 * @throws DAOException - when connection or query execution aren't successful 
	 */
	public void updateBrigadeFlight(int flightId) throws DAOException {
		EntityManager entityManager = null;
	    EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Flight> Flight_ = metamodel.entity(Flight.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaUpdate<Flight> cu = cb.createCriteriaUpdate(Flight.class);
			Root<Flight> flight = cu.from(Flight_);
			cu.set("brigade", null).where(cb.equal(flight.get("id"), flightId));
		
			transaction.begin();	
			entityManager.createQuery(cu).executeUpdate();
			transaction.commit();
		} 
		catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
			throw new DAOException("Can't update brigade flight", e);
		}
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}
}