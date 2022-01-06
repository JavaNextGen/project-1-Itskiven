package com.revature.repositories;

import com.revature.models.Status;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection; //?
import java.sql.PreparedStatement;
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
try(Connection connect = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "SELECT * FROM ers_users INNER JOIN user_roles ON roles_id = role_id WHERE username = ?;";
			
			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
			
			//insert the methods argument (int id) as the first (and only) variable in our SQL query
			ps.setString(1, username); //the 1 here is referring to the first parameter (?) found in our SQL String
			
			rs = ps.executeQuery();
			
			//create an empty List to be filled with the data from the database
			Optional<User> resultuser = Optional.empty();
			
	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
			while(rs.next()) { //while there are results in the result set...
				
				String r=rs.getString("role");
    			Role o = Role.valueOf(r);
    			
			//Use the all args Constructor to create a new Employee object from each returned row...
			User e = new User(
					//we want to use rs.getXYZ for each column in the record
					rs.getInt("user_id"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("fname"),
					rs.getString("lname"),
					rs.getString("email"),
					o
					);
			
			//and populate the ArrayList with each new Employee object
			resultuser = Optional.of(e); //e is the new Employee object we created above
			}
			
			//when there are no more results in the ResultSet the while loop will break...
			//return the populated List of Employees
			return resultuser;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database!"); 
			e.printStackTrace();
		}
		return null;
	}
	
//    public User getUsersByUsername(String username) {
//		try(Connection connect = ConnectionFactory.getConnection()) {
//			
//			ResultSet rs = null;
//			
//			String sql = "SELECT * FROM ers_users INNER JOIN user_roles ON roles_id = role_id WHERE username = ?;";
//			
//			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
//			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
//			
//			//insert the methods argument (int id) as the first (and only) variable in our SQL query
//			ps.setString(1, username); //the 1 here is referring to the first parameter (?) found in our SQL String
//			
//			rs = ps.executeQuery();
//			
//			//create an empty List to be filled with the data from the database
//			User resultuser = new User();
//			
//	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
//			while(rs.next()) { //while there are results in the result set...
//				
//				String r=rs.getString("role");
//    			Role o = Role.valueOf(r);
//    			
//			//Use the all args Constructor to create a new Employee object from each returned row...
//			User e = new User(
//					//we want to use rs.getXYZ for each column in the record
//					rs.getInt("user_id"),
//					rs.getString("username"),
//					rs.getString("password"),
//					rs.getString("fname"),
//					rs.getString("lname"),
//					rs.getString("email"),
//					o
//					);
//			
//			//and populate the ArrayList with each new Employee object
//			resultuser = e; //e is the new Employee object we created above
//			}
//			
//			//when there are no more results in the ResultSet the while loop will break...
//			//return the populated List of Employees
//			return resultuser;
//			
//		} catch (SQLException e) {
//			System.out.println("Something went wrong with the database!"); 
//			e.printStackTrace();
//		}
//		return null;
//	}

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
		try(Connection conn = ConnectionFactory.getConnection()){
			
			//we'll create a SQL statement using parameters to insert a new Employee
			String sql = "INSERT INTO ers_users (username, password, fname, lname, email, role_id) " //creating a line break for readability
					    + "VALUES (?, ?, ?, ?, ?, ?); "; //these are parameters!! We have to specify the value of each "?"
			
			PreparedStatement ps = conn.prepareStatement(sql); //we use PreparedStatements for SQL commands with variables
			
			//use the PreparedStatement objects' methods to insert values into the query's ?s
			//the values will come from the Employee object we send in.
			ps.setString(1, userToBeRegistered.getUsername()); //1 is the first ?, 2 is the second, etc.
			ps.setString(2, userToBeRegistered.getPassword());
			ps.setString(3, userToBeRegistered.getFname());
			ps.setString(4, userToBeRegistered.getLname()); //1 is the first ?, 2 is the second, etc.
			ps.setString(5, userToBeRegistered.getEmail());
			if (userToBeRegistered.getRole().name().equalsIgnoreCase("employee")) {
				ps.setInt(6, 1);
			} else {
				ps.setInt(6, 2);
			}
									
			//this executeUpdate() method actually sends and executes the SQL command we built
			ps.executeUpdate(); //we use executeUpdate() for inserts, updates, and deletes. 
			//we use executeQuery() for selects
			
			//send confirmation to the console if successul.
			System.out.println("Employee " + userToBeRegistered.getUsername() + " created. Welcome aboard!");
			
			
		} catch(SQLException e) {
			System.out.println("User creation failed");
			e.printStackTrace();
		} 
		return userToBeRegistered;
    }
    
    
    public List<User> getAllUsers(){
    	try (Connection connect = ConnectionFactory.getConnection()){
    		
    		//Initialize an empty ResultSet object that will store the results of our SQL query
    		ResultSet rs = null;
    
    		//Write the query that we want to send to the database and assign it to a String
    		String sql = "SELECT * FROM ers_users INNER JOIN user_roles ON roles_id = role_id;";
    		
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
    			
    			String r= rs.getString("role");
    			Role o = Role.valueOf(r);
    			
    			
    			//Use the all args constructor to create a new User object from each returned row from the DB
    			User e = new User(
    				//we want to use rs.get for each column in the record
    					rs.getInt("user_id"),
    					rs.getString("username"),
    					rs.getString("password"),
    					rs.getString("fname"),
    					rs.getString("lname"),
    					rs.getString("email"),
    					o
    					);
    			//and populate the ArrayList with each new Employee object
    			userList.add(e); //e is the new User object we created above
    		}
    		
    		//when there are no more results in rs, the while loop will break
    		//then, return the populated ArrayList of Users
    		return userList;
    		
    	} catch (SQLException e) {
    		System.out.println("Something went wrong obtaining the users!");
    		e.printStackTrace();
    	}
    	
    	return null; //we add this after the try/catch block, so Java won't yell
    	//(Since there's no guarantee that the try will run)
    }
    
    public Optional<User> getUserById(int id) {
		try(Connection connect = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "SELECT * FROM ers_users INNER JOIN user_roles ON roles_id = role_id WHERE user_id = ?";
			
			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
			
			//insert the methods argument (int id) as the first (and only) variable in our SQL query
			ps.setInt(1, id); //the 1 here is referring to the first parameter (?) found in our SQL String
			
			rs = ps.executeQuery();
			
			//create an empty List to be filled with the data from the database
			Optional<User> resultuser = Optional.empty();
			
	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
			while(rs.next()) { //while there are results in the result set...
				
				String r=rs.getString("role");
				Role o = Role.valueOf(r);
    			
			//Use the all args Constructor to create a new Employee object from each returned row...
			User e = new User(
					//we want to use rs.getXYZ for each column in the record
					rs.getInt("user_id"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("fname"),
					rs.getString("lname"),
					rs.getString("email"),
					o
					);
			
			//and populate the ArrayList with each new Employee object
			resultuser = Optional.of(e); //e is the new Employee object we created above
			}
			
			//when there are no more results in the ResultSet the while loop will break...
			//return the populated List of Employees
			return resultuser;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database!"); 
			e.printStackTrace();
		}
		return null;
	}
    
    public String getUserRole(String username, String password) {
		try(Connection connect = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "SELECT role FROM ers_users INNER JOIN user_roles ON roles_id = role_id WHERE username = ? AND password = ?;";
			
			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
			
			//insert the methods argument (int id) as the first (and only) variable in our SQL query
			ps.setString(1, username); //the 1 here is referring to the first parameter (?) found in our SQL String
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			//create an empty List to be filled with the data from the database
			String resultuser = null;
			
	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
			while(rs.next()) { //while there are results in the result set...
				
				String r=rs.getString("role");
    			
			
			
			//and populate the ArrayList with each new Employee object
			resultuser = r; //e is the new Employee object we created above
			}
			
			//when there are no more results in the ResultSet the while loop will break...
			//return the populated List of Employees
			return resultuser;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database!"); 
			e.printStackTrace();
		}
		return null;
	}
    
    public String getUserPass(String username) {
		try(Connection connect = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "SELECT * FROM ers_users WHERE username = ?;";
			
			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
			
			//insert the methods argument (int id) as the first (and only) variable in our SQL query
			ps.setString(1, username); //the 1 here is referring to the first parameter (?) found in our SQL String
			
			
			rs = ps.executeQuery();
			
			//create an empty List to be filled with the data from the database
			String resultpass = null;
			
	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
			while(rs.next()) { //while there are results in the result set...
				
				String r=rs.getString("password");
    			
			
			
			//and populate the ArrayList with each new Employee object
			resultpass = r; //e is the new Employee object we created above
			}
			
			//when there are no more results in the ResultSet the while loop will break...
			//return the populated List of Employees
			return resultpass;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database!"); 
			e.printStackTrace();
		}
		return null;
	}
    
    @SuppressWarnings("null")
	public int getAuthor(String username, String password) {
		try(Connection connect = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sql = "SELECT user_id FROM ers_users INNER JOIN user_roles ON roles_id = role_id WHERE username = ? AND password = ?;";
			
			//when we need parameters we need to use a PREPARED Statement, as opposed to a Statement (seen above)
			PreparedStatement ps = connect.prepareStatement(sql); //prepareStatment() as opposed to createStatment()
			
			//insert the methods argument (int id) as the first (and only) variable in our SQL query
			ps.setString(1, username); //the 1 here is referring to the first parameter (?) found in our SQL String
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			//create an empty List to be filled with the data from the database
			int resultuser = 0;
			
	//we technically don't need this while loop since we're only getting one result back... see if you can refactor :)
			while(rs.next()) { //while there are results in the result set...
				
				int r = rs.getInt("user_id");
			
			//and populate the ArrayList with each new Employee object
			resultuser = r; //e is the new Employee object we created above
			}
			
			//when there are no more results in the ResultSet the while loop will break...
			//return the populated List of Employees
			return resultuser;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database!"); 
			e.printStackTrace();
		}
		return (Integer) null;
	}
   

    
}


