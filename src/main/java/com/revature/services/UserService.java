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
		
		Optional<User> user = uDAO.getByUsername(username);
		
			if(user.isPresent()) {
			System.out.println("Username Exists!");
//			uDAO.getByUsername(username).get();
			return user;
		
	} else {
		System.out.println("Username Does Not Exists!");
		return user;                               
	}
//		Optional<User> users = uDAO.getByUsername(username);
//		
//		//returns list of users
//		return users;
		
	}
	
//	public User getUsersByUsername(String username) {
//		
//		return uDAO.getUsersByUsername(username);
//	}
	
	//WHOLE METHOD (KEVIN)
	public List<User> getAllUsers() {
		
		//get the list of users by calling the DAO method that selects them from the database
		List<User> users = uDAO.getAllUsers();
		
		//returns list of users
		return users;
	}
	
	public Optional<User> getUserById (int idInput) {
		
		if(uDAO.getUserById(idInput).isPresent()) {
			System.out.println("ID Exists!");
			uDAO.getUserById(idInput).get();
			return uDAO.getUserById(idInput);
		
	} else {
		System.out.println("ID Does Not Exists!");
		return null;
	}
	}
	
	public String getUserRole(String username, String password) {
		return uDAO.getUserRole(username, password);
	}
	
	public int getAuthor(String username, String password) {
		return uDAO.getAuthor(username, password);
	}
	

	}

	
	
