package org.sinhala.wordnet.wordnetDB.model;

public class User {
	 
public String username;
 public String password;
 public String firstName;
 public String lastName;
 public String email;
 public int role;
 public boolean verified = false;
 public String key;
 
public User() {
 }
 
public int getRole() {
 return role;
 }
 
public void setRole(int role) {
 this.role = role;
 }
 
public User(String username, String password, String firstName,
 String lastName, String email) {
 this.username = username;
 this.password = password;
 this.firstName = firstName;
 this.lastName = lastName;
 this.email = email;
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
 
public String getEmail() {
 return email;
 }
 
public void setEmail(String email) {
 this.email = email;
 }

public boolean isVerified() {
	return verified;
}

public void setVerified(boolean verified) {
	this.verified = verified;
}

public String getKey() {
	return key;
}

public void setKey(String key) {
	this.key = key;
}

@Override
 public String toString(){
 return "First Name:" + this.firstName + " Last Name:" + this.lastName + " Username:" + this.username ;
 }
 
}