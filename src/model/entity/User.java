package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**User class*/
@Entity
@Table(name="client")
public class User {
	
	/**Serial version UID*/
	//private static final long serialVersionUID = 1L;
	
	/**Secret admin code*/
	public static final String ADMIN_CODE = "admin";
	
	/**User email*/
	@Id
	private String email;
	
	/**User password*/
	@Column
	private String password;	
	
	/**User type*/
	@Column
	private String type;

	/**
	 * Constructor 
	 * @param email - user email
	 * @param password - user password
	 */
	public User (String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	/**Constructor*/
	public User() {}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}	
}
