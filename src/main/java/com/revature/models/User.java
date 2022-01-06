package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {

	private String fname;
	private String lname;
	private String email;
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    
    public User(String username, String password, String fname, String lname, String email , Role role) {
        super(username, password, role);
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    
    public User(int id, String username, String password, String fname, String lname, String email , Role role) {
        super(id, username, password, role);
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

	public User(int id) {
		super(id);
	}

	
}
