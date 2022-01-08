package com.revature.models;

//Data Transfer Object. A DTO is juse a model of /some/ data coming from the client (front end)
//HTTP handler will parse a JSON object sent by the user, containing their username and password
//this then gets sent to the controller to get turned into a Java object (using Gson)
//The username and password will then be put into the DTO as fields (see fields below), which get checked by the service
//You NEVER store a DTO in the database. It;s purely for data transfer... we're transferring the username/password

public class LoginDTO {

	//This Class models ONLY the username/password of the user
	
	private String username;
	private String password;
	
	
	//Create constructors to instantiate the class and transfer data with it.
	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	//Getters&Setters to access fields
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

	
	//toString Just incase we want to print out the object (could be helpful for debugging)
	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + "]";
	}
	
	

	
	
}
