package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {
	
	static ReimbursementDAO rDAO = new ReimbursementDAO();

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
//    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
//    	Reimbursement reimbursement = rDAO.process(unprocessedReimbursement, finalStatus, resolver);
//		
//		return reimbursement;
//    }

    public Reimbursement process(Reimbursement unprocessedReimbursement) {
    	Reimbursement reimbursement = rDAO.process(unprocessedReimbursement);
		
		return reimbursement;
    }
    
    /**
     * Should retrieve all reimbursements with the correct status.
     */
    
    
    public List<Reimbursement> getReimbursementsByStatus() {
       List<Reimbursement> reimbursement = rDAO.getByStatus();
    	return reimbursement;
    }
    
    public List<Reimbursement> getPendingReimbursements() {
        List<Reimbursement> reimbursement = rDAO.getPendingReimbursements();
     	return reimbursement;
     }
    
    public List<Reimbursement> getOwnReimbursement(String username) {
    	List<Reimbursement> reimbursement = rDAO.getOwnReimbursement(username);
    	return reimbursement;
    }
    
	public List<Reimbursement> getResolvedReimbursement(String username) {
		List<Reimbursement> reimbursement = rDAO.getResolvedReimbursements(username);
     	return reimbursement;
	}
    
    public void submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
    	rDAO.submit(reimbursementToBeSubmitted);
    }

	public Reimbursement update(Reimbursement unprocessedReimbursement) {
		Reimbursement reimbursement = rDAO.update(unprocessedReimbursement);
		return reimbursement;
	}
	
	public int getIntAuthor(int reimb_id) {
		return rDAO.getIntAuthor(reimb_id);
	}

	public String getCurrentStatus(int id) {
		return rDAO.getCurrentStatus(id);
	}
	
	public Optional<Reimbursement> getById(int id) {
		
		Optional<Reimbursement> ID = rDAO.getById(id);
		
		if(ID.isPresent()) {
			System.out.println("ID Exists!");
//			uDAO.getByUsername(username).get();
			return ID;
		
	} else {
		System.out.println("ID Does Not Exist!");
		return ID;         
		
	}
//		Optional<Reimbursement> reimbursementId = rDAO.getById(id);
//		
//		//returns list of users
//		return reimbursementId;
    }
	
	public boolean ifIdExist (int id) {
		
		boolean endresult = rDAO.ifIdExist(id);
		
		return endresult;
				
	}
	
	public boolean reimbursementIdResolved (int id) {
		
		boolean endresult = rDAO.reimbursementIdResolved(id);
		
		return endresult;
	}

}
