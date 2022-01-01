package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	UserDAO uDAO = new UserDAO(); //Use methods in the UserDAO
	
	public Optional<User> getByUsername(String username) {
		return Optional.empty();
	}
	
	public User getUsersByUsername(String username) {
		
		return uDAO.getUsersByUsername(username);
	}
	
	//WHOLE METHOD (KEVIN)
	public List<User> getAllUsers() {
		
		//get the list of users by calling the DAO method that selects them from the database
		List<User> users = uDAO.getAllUsers();
		
		//returns list of users
		return users;
	}
	
	//making a new user object
	public void createUser (User userToBeRegistered) {
		//take in the user object sent from the menu and send it to the userDAO to be inserted into the database
		
		//calling the userDAO method that inserts the new User object
		uDAO.create(userToBeRegistered);
	}
	
	public User getUserById (int idInput) {
		
		return uDAO.getUserById(idInput);
	}
	
	public Role getUserRole(String username, String password) {
		return uDAO.getUserRole(username, password);
	}
	
	public int getUserId(String username, String password) {
		return uDAO.getUserId(username, password);
	}
	
	public String getUserAuthor(String username, String password) {
		return uDAO.getUserAuthor(username, password);
	}
	
	public String getUserResolver(String username, String password) {
		return uDAO.getUserResolver(username, password);
	}
	
	
}
