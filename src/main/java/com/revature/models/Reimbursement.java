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
	private int id2;

	
	
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


	public Reimbursement(int id, double amount, ReimbursementType typee) {
		super(id, amount);
        this.typee = typee;
	}

	public Reimbursement(int id, double amount, ReimbursementType typee, int id2) {
		super(id, amount);
        this.typee = typee;
        this.setId2(id2);
	}

	public Reimbursement(int id, Status status, int resolver) {
		super (id, status, resolver);
	}

	public Reimbursement(int i, Status pending, User gENERIC_EMPLOYEE_1, Object object, double d) {
		// TODO Auto-generated constructor stub
	}

	public ReimbursementType getType() {
		return typee;
	}

	public void setType(ReimbursementType type) {
		this.typee = type;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}
}
