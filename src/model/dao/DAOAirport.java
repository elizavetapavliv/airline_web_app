package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import model.entity.Airport;
import model.exception.DAOException;

/**Airport DAO class */
public class DAOAirport extends DAO {
	
	/**
	 * Constructor
	 * @param entityManagerFactory - entity manager factory
	 */
	public DAOAirport(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Function for getting departure and destination airports
	 * @param fromId - departure airport id
	 * @param toId - destination airport id
	 * @return list with two Airport objects
	 * @throws DAOException - when entityManager or query execution aren't successful 
	 */
	public List<Airport> readAirports(int fromId, int toId) 
			throws DAOException {
		List<Airport> airports = new ArrayList<>();
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Airport> Airport_ = metamodel.entity(Airport.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Airport> cq = cb.createQuery(Airport.class);
			
			Root<Airport> airport = cq.from(Airport_);
			cq.select(airport).where(cb.equal(airport.get("id"), fromId));
			airports.add(entityManager.createQuery(cq).getSingleResult());
			cq.select(airport).where(cb.equal(airport.get("id"), toId));
			airports.add(entityManager.createQuery(cq).getSingleResult());
		}  
		catch (Exception e) {
			throw new DAOException("Can't obtain airports", e);
		} 
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return airports;
	}
	
	/**
	 * Function for getting all airports
	 * @return list of airports
	 * @throws DAOException - when entityManager or query execution aren't successful 
	 */
	public List<Airport> readAllAirports() throws DAOException {
		List<Airport> airports = new ArrayList<>();
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Airport> Airport_ = metamodel.entity(Airport.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Airport> cq = cb.createQuery(Airport.class);
			
			Root<Airport> airport = cq.from(Airport_);
			cq.select(airport);
			airports = entityManager.createQuery(cq).getResultList();
		} 
		catch (Exception e) {
			throw new DAOException("Can't obtain airports list", e);
		} 
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return airports;
	}
}
