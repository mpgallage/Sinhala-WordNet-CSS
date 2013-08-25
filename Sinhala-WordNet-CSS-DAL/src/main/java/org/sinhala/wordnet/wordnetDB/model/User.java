package org.sinhala.wordnet.wordnetDB.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String id;

	String username;

	String password;
	String firstName;
	String lastName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
		public String getFirstName() {
		 return firstName;
		 }
		 
		public void setFirstName(String firstName) {
		 this.firstName = firstName;
		 }
		 
		public String getLastName() {
		 return lastName;
		 }
		 
		public void setLastName(String lastName) {
		 this.lastName = lastName;
		 }

	public User(String username, String password,String firstName,String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}