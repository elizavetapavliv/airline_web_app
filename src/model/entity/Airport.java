package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**Airport class*/
@Entity
@Table(name="airport")
public class Airport implements Serializable {
	
	/**Serial version UID*/
	private static final long serialVersionUID = 1L;

	/**Airport id*/
	@Id
	private int id;
	
	/**Flag for good weather*/
	@Column(name="weather_good")
	private boolean weatherGood;
	
	@Column(name="technic_good")
	/**Flag for good technic*/
	private boolean technicGood;
	
	/**Airport name*/
	private String name;
	
	/**
	 * Getter for id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for id
	 * @param id - airport id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for weather condition
	 * @return - weather flag
	 */
	public boolean isWeatherGood() {
		return weatherGood;
	}
	
	/**
	 * Setter for weather condition
	 * @param weatherIsGood - weather flag
	 */
	public void setWeatherGood(boolean weatherIsGood) {
		this.weatherGood = weatherIsGood;
	}
	
	/**
	 * Getter for technic condition
	 * @return - technic flag
	 */
	public boolean isTechnicGood() {
		return technicGood;
	}
	
	/**
	 * Setter for technic condition
	 * @param technicIsGood - technic flag
	 */
	public void setTechnicGood(boolean technicIsGood) {
		this.technicGood = technicIsGood;
	}
	
	/**
	 * Getter for airport name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for airport name
	 * @param name - airport name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String result = String.format("Airport id = %d\nname = %s\nweather is good = %b\n"
				+ "technic is good = %b\n", id, name, weatherGood, 
				technicGood);
		return result;
	}	
}
