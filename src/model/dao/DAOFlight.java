package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import model.entity.Flight;
import model.exception.DAOException;

/** Flight DAO class */
public class DAOFlight extends DAO {
	
	/**
	 * Constructor
	 * @param entityManagerFactory - entity manager factory
	 */
	public DAOFlight(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Function for getting all flights
	 * @return flights list
	 * @throws DAOException - when connection or query execution aren't successful
	 */
	public List<Flight> readAllFlights() throws DAOException {
		List<Flight> flights = new ArrayList<>();
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Flight> Flight_ = metamodel.entity(Flight.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
			Root<Flight> flight = cq.from(Flight_);
			cq.select(flight);
			
			flights = entityManager.createQuery(cq).getResultList();
		} 
		catch (Exception e) {
			throw new DAOException("Can't obtain flights list", e);
		} 
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return flights;
	}

	/**
	 * Function for getting delayed flights
	 * @return flights list
	 * @throws DAOException - when connection or query execution aren't successful
	 */
	public List<Flight> readDelayedFlights() throws DAOException {
		List<Flight> flights = new ArrayList<>();
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Flight> Flight_ = metamodel.entity(Flight.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
			Root<Flight> flight = cq.from(Flight_);
			cq.select(flight).where(cb.equal(flight.get("delayed"), true));
			
			flights = entityManager.createQuery(cq).getResultList();
		} 
		catch (Exception e) {
			throw new DAOException("Can't obtain delayed flights list", e);
		} 
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return flights;
	}

	/**
	 * Function for getting flight by id
	 * @param id - flight id
	 * @return Flight object
	 * @throws DAOException - when connection or query execution aren't successful
	 */
	public Flight readFlight(int id) throws DAOException {
		Flight flight = null;
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Flight> Flight_ = metamodel.entity(Flight.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
			Root<Flight> flightRoot = cq.from(Flight_);
			cq.select(flightRoot).where(cb.equal(flightRoot.get("id"), id));
			
			flight = entityManager.createQuery(cq).getSingleResult();
		} 
		catch(NoResultException e) {
	        return null;
	    }
		catch (Exception e) {
			throw new DAOException("Can't obtain flight", e);
		} 
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return flight;
	}

	/**
	 * Function for deleting flight
	 * @param flightId - flight id
	 * @throws DAOException - when connection or query execution aren't successful
	 */
	public void deleteFlight(int flightId) throws DAOException {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			
			Metamodel metamodel = entityManager.getMetamodel();		
			EntityType<Flight> Flight_ = metamodel.entity(Flight.class);
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaDelete<Flight> cd = cb.createCriteriaDelete(Flight.class);
			Root<Flight> flight = cd.from(Flight_);
			cd.where(cb.equal(flight.get("id"), flightId));
			
			transaction.begin();
			entityManager.createQuery(cd).executeUpdate();
			transaction.commit();
		}
		catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
			throw new DAOException("Can't delete flight", e);
		}
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	/**
	 * Function for updating flight destination airport
	 * @param id          - flight id
	 * @param toAirportId - airport id
	 * @throws DAOException - when connection or query execution aren't successful
	 */
	public void updateFlightToAirport(int id, int toAirportId) throws DAOException {
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
			cu.set("toAirportId", toAirportId).where(cb.equal(flight.get("id"), id));
			
			transaction.begin();
			entityManager.createQuery(cu).executeUpdate();
			transaction.commit();
		} 
		catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
			throw new DAOException("Can't update flight destination airport", e);
		}
		finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}
}
