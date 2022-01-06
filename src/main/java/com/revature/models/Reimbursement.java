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
	
	private ReimbursementType typee;

	
	
    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(double amount, int author, Status status, ReimbursementType typee ) {
        super(amount, author, status);
        this.typee = typee;
    }
    
    public Reimbursement(double amount, int author, int resolver, Status status, ReimbursementType typee) {
        super(amount, author, resolver, status);
        this.typee = typee;
    }
    
    public Reimbursement(int id, double amount, int author, int resolver, Status status, ReimbursementType typee) {
        super(id, status, author, resolver, amount);
        this.typee = typee;
    }

	public ReimbursementType getType() {
		return typee;
	}

	public void setType(ReimbursementType type) {
		this.typee = type;
	}
}
