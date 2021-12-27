package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection; //?
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; //NOT SURE
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
        return userToBeRegistered;
    }
    //(WHOLE METHOD KEVIN)
    public List<User> getUsers(){
    	try (Connection connect = ConnectionFactory.getConnection()){
    		
    		//Initialize an empty ResultSet object that will store the results of our SQL query
    		ResultSet rs = null;
    
    		//Write the query that we want to send to the database and assign it to a String
    		String sql = "SELECT * FROM employees;";
    		
    		//Put the SQL query into a Statement object (The Connection object has a method for this!! implicit?)
    		Statement statement = connect.createStatement();
    		
    		//Execute the Query by putting the results of the query into our ResultSet object (rs)
    		//The Statement object has a method that takes Strings to execute as a SQL query
    		rs = statement.executeQuery(sql);
    		
    		//ALL THE CODE ABOVE MAKES A CALL TO OUR DATABASE. Now we need to store the data in an ArrayList.
    		
    		//create an empty ArrayList to be filled with the data from the database
    		List<User> userList = new ArrayList<>(); //Upcasting, we are instantiating an ArrayList
    		
    		
    		//while there are results in the resultset
    		while(rs.next()) {	
    			
    			//Use the all args constructor to create a new User object from each returned row from the DB
    			User e = new User(
    				//we want to use rs.get for each column in the record
    					rs.getInt("user_id"),
    					rs.getString("f_name"),
    					rs.getString("l_name"),
    					rs.getRole()
    					);
    			//and populate the ArrayList with each new Employee object
    			userList.add(e); //e is the new User object we created above
    		}
    		
    		//when there are no more results in rs, the while loop will break
    		//then, return the populated ArrayList of Users
    		return userList;
    		
    	} catch (SQLException e) {
    		System.out.println("Something went wrong selecting employees!");
    		e.printStackTrace();
    	}
    	
    	return null; //we add this after the try/catch block, so Java won't yell
    	//(Since there's no guarantee that the try will run)
    }
    
}
