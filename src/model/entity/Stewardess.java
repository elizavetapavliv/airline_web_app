package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**Stewardess class*/
@Entity
@Table(name="stewardess")
public class Stewardess implements Serializable {

	/**Serial version UID*/
	private static final long serialVersionUID = 1L;

	/**Stewardess id*/
	@Id
	private int id;
	
	/**Stewardess name*/
	@Column(name = "stewardess_name")
	private String name;
	
	@ManyToOne
    @JoinColumn(name="brigade_id")
	private Brigade brigade;
	
	/**
	 * Constructor
	 * @param id - stewardess id
	 * @param name - stewardess name
	 * @param brigade - stewardess brigade
	 */
	public Stewardess (int id, String name, Brigade brigade) {
		this.id = id;
		this.name = name;
		this.brigade = brigade;
	}
	
	public Stewardess() {}
	
	/**
	 * Getter for id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for id
	 * @param id - stewardess id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for stewardess name
	 * @return stewardess name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for stewardess name
	 * @param name - stewardess name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public Brigade getBrigade() {
		return brigade;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
