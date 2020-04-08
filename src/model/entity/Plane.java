package model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**Plane class*/
@Entity
@Table(name="plane")
public class Plane implements Serializable {
	
	/**Serial version UID*/
	private static final long serialVersionUID = 1L;

	/**Plane id*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/**Plane capacity*/
	private int capacity;
	
	/**Plane max distance*/
	@Column(name="max_distance")
	private int maxDistance;
	
	
	@OneToOne(mappedBy="plane", cascade = CascadeType.ALL)
	private Flight flight;
	/**
	 * Getter for id
	 * @return plane id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for id
	 * @param id - plane id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for capacity
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Setter for capacity
	 * @param capacity - plane capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Getter for plane max distance
	 * @return plane max distance
	 */
	public int getMaxDistance() {
		return maxDistance;
	}
	
	/**
	 * Setter for plane max distance
	 * @param maxDistance - plane max distance
	 */
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
	@Override
	public String toString() {
		String result = String.format("Plane id = %d\ncapacity = %d\n"
				+ "max distance = %d\n", id, capacity, maxDistance);
		return result;
	}
}
