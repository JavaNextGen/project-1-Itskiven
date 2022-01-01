package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return Collections.emptyList();
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
			
			String sql = "INSERT INTO reimbursement (id, status, author, amount)"
						+ "VALUES (?, ?, ?, ?)";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			//use the PreparedStatement objects' methods to insert values into the query's ?s
			//the values will come from the Employee object we send in.
			ps.setInt(1, reimbursementToBeSubmitted.getId()); //1 is the first ?, 2 is the second, etc.
			ps.setString(2, reimbursementToBeSubmitted.getStatus().name());
			ps.setString(3, reimbursementToBeSubmitted.getAuthor());
			ps.setDouble(4, reimbursementToBeSubmitted.getAmount());
			
			
							
			//this executeUpdate() method actually sends and executes the SQL command we built
			ps.executeUpdate(); //we use executeUpdate() for inserts, updates, and deletes. 
			//we use executeQuery() for selects
			
			//send confirmation to the console if successul.
			System.out.println("Reimbursement created by " + reimbursementToBeSubmitted.getAuthor() + "submitted.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
