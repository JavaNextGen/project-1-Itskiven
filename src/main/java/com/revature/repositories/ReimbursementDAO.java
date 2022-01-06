package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import sun.net.www.protocol.http.AuthCacheValue.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
    	try (Connection connect = ConnectionFactory.getConnection()){
    		
    		//Initialize an empty ResultSet object that will store the results of our SQL query
    		ResultSet rs = null;
    
    		//Write the query that we want to send to the database and assign it to a String
    		String sql = "SELECT * FROM reimbursement INNER JOIN ers_users ON user_id = author INNER JOIN reimbursement_status ON status_id = status INNER JOIN reimbursement_type on type_id = typee;"
    				;
    		
    		//Put the SQL query into a Statement object (The Connection object has a method for this!! implicit?)
    		Statement statement = connect.createStatement();
    		
    		//Execute the Query by putting the results of the query into our ResultSet object (rs)
    		//The Statement object has a method that takes Strings to execute as a SQL query
    		rs = statement.executeQuery(sql);
    		
    		//ALL THE CODE ABOVE MAKES A CALL TO OUR DATABASE. Now we need to store the data in an ArrayList.
    		
    		//create an empty ArrayList to be filled with the data from the database
    		List<Reimbursement> reimbursementList = new ArrayList<>(); //Upcasting, we are instantiating an ArrayList
    		
    		
    		//while there are results in the resultset
    		while(rs.next()) {	
    			
    			String a = rs.getString("currentstatus");
    			Status b = Status.valueOf(a);
    			String c = rs.getString("type");
    			ReimbursementType d = ReimbursementType.valueOf(c);
    			
    			//Use the all args constructor to create a new User object from each returned row from the DB
    			Reimbursement r = new Reimbursement(
    				//we want to use rs.get for each column in the record
    					rs.getInt("reimb_id"),
    					rs.getDouble("amount"),
    					rs.getInt("author"),
    					rs.getInt("resolver"),
    					b,
    					d
    					);
    			//and populate the ArrayList with each new Employee object
    			reimbursementList.add(r); //e is the new User object we created above
    		}
    		
    		//when there are no more results in rs, the while loop will break
    		//then, return the populated ArrayList of Users
    		return reimbursementList;
    		
    	} catch (SQLException e) {
    		System.out.println("Something went wrong obtaining the reimbursements!");
    		e.printStackTrace();
    	}
    	
    	return null; //we add this after the try/catch block, so Java won't yell
    	//(Since there's no guarantee that the try will run)
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }

	public void submit(Reimbursement reimbursementToBeSubmitted) {
		try (Connection connect = ConnectionFactory.getConnection()) {
			
			String sql = "INSERT INTO reimbursement (amount, author, status, typee)"
						+ "VALUES (?, ?, ?, ?)";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			//use the PreparedStatement objects' methods to insert values into the query's ?s
			//the values will come from the Employee object we send in.
			ps.setDouble(1, reimbursementToBeSubmitted.getAmount()); //1 is the first ?, 2 is the second, etc.
			ps.setInt(2, reimbursementToBeSubmitted.getAuthor());

			
			if (reimbursementToBeSubmitted.getStatus().name().equalsIgnoreCase("pending")) {
				ps.setInt(3, 1);
			}
			
			if (reimbursementToBeSubmitted.getType().name().equalsIgnoreCase("lodging")) {
				ps.setInt(4, 1);
			} else if (reimbursementToBeSubmitted.getType().name().equalsIgnoreCase("travel")) {
				ps.setInt(4, 2);
			} else if (reimbursementToBeSubmitted.getType().name().equalsIgnoreCase("food")) {
				ps.setInt(4, 3);
			} else if (reimbursementToBeSubmitted.getType().name().equalsIgnoreCase("other")) {
				ps.setInt(4, 4);
			} 
			
			
							
			//this executeUpdate() method actually sends and executes the SQL command we built
			ps.executeUpdate(); //we use executeUpdate() for inserts, updates, and deletes. 
			//we use executeQuery() for selects
			
			//send confirmation to the console if successul.
			System.out.println("Reimbursement created by user_id = " + reimbursementToBeSubmitted.getAuthor() + " submitted.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
