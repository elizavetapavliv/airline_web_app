package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**Flight class*/
@Entity
@Table(name="flight")
public class Flight implements Serializable {
	
	/**Serial version UID*/
	private static final long serialVersionUID = 1L;

	/**Registrated passengers number*/
	@Column(name="passengers_num")
	private int passengersNum;
	
	/**Flight distance*/
	private int distance;
	
	/**Flight id*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**Departure airport id*/
	@Column(name="from_airport_id")
	private int fromAirportId;
	
	/**Destination airport id*/
	@Column(name="to_airport_id")
	private int toAirportId;
	
	/**Flag for checking if flight is delayed*/
	@Column(name="is_delayed")
	private boolean delayed;
	
    @OneToOne
	private Brigade brigade;
	
	
	@OneToOne
	private Plane plane;
	
	/**
	 * Getter for passengers number
	 * @return - passengers number
	 */
	public int getPassengersNum() {
		return passengersNum;
	}
	
	/**
	 * Setter for passengers number
	 * @param passengersNum - passengers number
	 */
	public void setPassengersNum(int passengersNum) {
		this.passengersNum = passengersNum;
	}
	
	/**
	 * Getter for distance
	 * @return - distance
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Setter for distance
	 * @param distance - flight distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	/**
	 * Getter for id
	 * @return - id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for id
	 * @param id - flight id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for departure airport id
	 * @return - departure airport id
	 */
	public int getFromAirportId() {
		return fromAirportId;
	}
	
	/**
	 * Setter for departure airport id
	 * @param fromAirportId - departure airport id
	 */
	public void setFromAirportId(int fromAirportId) {
		this.fromAirportId = fromAirportId;
	}
	
	/**
	 * Getter for destination airport id
	 * @return -  destination airport id
	 */
	public int getToAirportId() {
		return toAirportId;
	}
	
	/**
	 * Setter for destination airport id
	 * @param toAirportId - destination airport id
	 */
	public void setToAirportId(int toAirportId) {
		this.toAirportId = toAirportId;
	}
	
	/**
	 * Getter for delayed flag
	 * @return - delayed flag
	 */
	public boolean isDelayed() {
		return delayed;
	}
	
	public void setBrigade(Brigade brigade) {
		this.brigade = brigade;
	}
	
	public Brigade getBrigade() {
		return brigade;
	}
	
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	
	public Plane getPlane() {
		return plane;
	}
	
	/**
	 * Setter for delayed flag
	 * @param isDelayed - delayed flag
	 */
	public void setDelayed(boolean isDelayed) {
		this.delayed = isDelayed;
	}
	
	@Override
	public String toString() {
		String result = "Flight";
		String brigadeInfo = brigade == null ? "is not assigned" : "id = " + brigade.getId();
		String planeInfo = plane == null ? "is not assigned" : "id = " + plane.getId();
		
		result += String.format(" id = %d\npassengers number = %d\n"
				+ "distance = %d\nbrigade %s\n"
				+ "from airport id = %d\nto airport id = %d\nplane %s\n"
				+ "is delayed = %b\n", 
				id, passengersNum, distance, brigadeInfo, fromAirportId,  toAirportId, 
				planeInfo, delayed);
		return result;
	}

}
