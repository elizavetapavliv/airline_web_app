package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**Brigade class*/
@Entity
@Table(name="brigade")
public class Brigade implements Serializable {
	
	/**Serial version UID*/
	private static final long serialVersionUID = 1L;

	/**Brigade id*/
	@Id
	private Integer id;
	
	/**Pilot1 name*/
	private String pilot1;
	
	/**Pilot2 name*/
	private String pilot2;
	
	/**Navigator name*/
	private String navigator;
	
	/**RadioOperator*/
	@Column(name="radio_operator")
	private String radioOperator;
	
	@OneToOne(mappedBy="brigade")
	private Flight flight;
	
	/**List of stewardesses*/
    @OneToMany(mappedBy = "brigade", cascade = CascadeType.PERSIST)
	private List<Stewardess> stewardesses;
	
	/**Constructor*/
	public Brigade() {
		stewardesses = new ArrayList<>();
	}

	/**
	 * Function for adding new stewardess to brigade
	 * @param stewardess - new stewardess
	 */
	public void addStewardess(Stewardess stewardess) {
		stewardesses.add(stewardess);
	}

	/**
	 * Getter for id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for id
	 * @param id - brigade id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for pilot1
	 * @return pilot1
	 */
	public String getPilot1() {
		return pilot1;
	}

	/**
	 * Setter for pilot1
	 * @param pilot1 - pilot1
	 */
	public void setPilot1(String pilot1) {
		this.pilot1 = pilot1;
	}

	/**
	 * Getter for pilot2
	 * @return pilot2
	 */
	public String getPilot2() {
		return pilot2;
	}

	/**
	 * Setter for pilot2
	 * @param pilot2 - pilot2
	 */
	public void setPilot2(String pilot2) {
		this.pilot2 = pilot2;
	}

	/**
	 * Getter for navigator
	 * @return navigator
	 */
	public String getNavigator() {
		return navigator;
	}

	/**
	 * Setter for navigator
	 * @param navigator - navigator
	 */
	public void setNavigator(String navigator) {
		this.navigator = navigator;
	}
	
	/**
	 * Getter for radio operator
	 * @return radio operator
	 */
	public String getRadioOperator() {
		return radioOperator;
	}

	/**
	 * Setter for radio operator
	 * @param radioOperator - radio operator
	 */
	public void setRadioOperator(String radioOperator) {
		this.radioOperator = radioOperator;
	}
	
	/**
	 * Getter for stewardesses list
	 * @return stewardesses list
	 */
	public List<Stewardess> getStewardesses() {
		return stewardesses;
	}

	/**
	 * Setter for stewardesses list
	 * @param stewardesses - stewardesses list
	 */
	public void setStewardesses(List<Stewardess> stewardesses) {
		this.stewardesses = stewardesses;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	@Override
	public String toString() {
		String result = String.format("Brigade id = %d; pilot1: %s; pilot2: %s; "
				+ "navigator: %s; radioOperator: %s\nstewardesses:", 
				id, pilot1, pilot2, navigator, radioOperator);
		for (int i = 0; i < stewardesses.size(); i++) {
			result += String.format("\n%s", stewardesses.get(i));
		}
		return result;
	}
}
