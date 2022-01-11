package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;

public class AuthDAO {

	public boolean login2(String username, String password) {
		try(Connection connect = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "SELECT username, password FROM ers_users WHERE username = ? AND password = ?;";
			
			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
			
			//insert the methods argument (int id) as the first (and only) variable in our SQL query
			ps.setString(1, username); //the 1 here is referring to the first parameter (?) found in our SQL String
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			//create an empty List to be filled with the data from the database
			String[] resultuser = new String[2];
			boolean endresult = false;
	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
			while(rs.next()) { //while there are results in the result set...
				
				resultuser [0] = rs.getString("username");
				resultuser [1] = rs.getString("password");
			
			
			//and populate the ArrayList with each new Employee object
				if (resultuser[0].equals(username) && resultuser[1].equals(password)) {
					endresult = true;
				} else {
					endresult = false;
				}
			
			}
			
			//when there are no more results in the ResultSet the while loop will break...
			//return the populated List of Employees
			return endresult;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database!"); 
			e.printStackTrace();
			return false;
		}
	
	}

	public boolean compareUsername(String username) {
try(Connection connect = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "SELECT username FROM ers_users WHERE username = ?;";
			
			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
			
			//insert the methods argument (int id) as the first (and only) variable in our SQL query
			ps.setString(1, username); //the 1 here is referring to the first parameter (?) found in our SQL String
			
			rs = ps.executeQuery();
			
			//create an empty List to be filled with the data from the database
			String resultuser = new String();
			boolean endresult = false;
	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
			while(rs.next()) { //while there are results in the result set...
				
				resultuser = rs.getString("username");
			
			
			//and populate the ArrayList with each new Employee object
				if (resultuser.equals(username)) {
					endresult = true;
				} else {
					endresult = false;
				}
			
			}
			
			//when there are no more results in the ResultSet the while loop will break...
			//return the populated List of Employees
			return endresult;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database!"); 
			e.printStackTrace();
			return false;
		}
	}
	
	
}
