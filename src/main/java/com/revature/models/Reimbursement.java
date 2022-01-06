package com.revature.models;

import java.time.LocalTime;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
//LocalTime time = LocalTime.now();
//System.out.println(time);
public class Reimbursement extends AbstractReimbursement {
	
	private String typee;

	
	
    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(double amount, User author, Status status, String typee) {
        super(amount, author, status);
        this.typee = typee;
    }
    
    public Reimbursement(double amount, User author, User resolver, Status status, String typee) {
        super(amount, author, resolver, status);
        this.typee = typee;
    }
    
    public Reimbursement(int id, double amount, User author, User resolver, Status status, String typee) {
        super(id, status, author, resolver, amount);
        this.typee = typee;
    }

	public String getType() {
		return typee;
	}

	public void setType(String type) {
		this.typee = type;
	}
}
