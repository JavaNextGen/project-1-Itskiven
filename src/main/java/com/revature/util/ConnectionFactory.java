package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p>
 */

//This class contains the codes that gets a connection to our database (Kevin)
public class ConnectionFactory {

    private static ConnectionFactory instance;

    private ConnectionFactory() {
        super();
    }

    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactory.getInstance()}
     */
    public static ConnectionFactory getInstance() {
        if(instance == null) {
            instance = new ConnectionFactory();
        }

        return instance;
    }

    /**
     * <p>The {@link ConnectionFactory#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link java.sql.Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     * @throws SQLException 
     */
    
    //This method will return a Connection object in our repository package to interact with our database
    public static Connection getConnection() throws SQLException {
        
    	//For compatibility with other technologies, we need to register our PostgreSQL Driver
    	//This process makes the application aware of what database Driver (SQL dialect) we're using 
    	try {
    		Class.forName("org.postgresql.Driver"); //try to find and return the postgresql Driver Class
    	} catch (ClassNotFoundException e) {
    		System.out.println("Class wasn't found");
    		e.printStackTrace(); // This will print the exception message to the console
    		//e.printStackTrace is useful in debugging
    	}

    	//Database Credentials!	
    	
    	//the url to my database schema
    	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1";
    	//my postgres username
    	String username = "postgres";
    	//my postgres password
    	String password = "password";
    	
    	//This is what returns our Connection object
    	//DriverManager is a class from java.sql and this is how we manage our JDBC. We are using it to getConnection
    	return DriverManager.getConnection(url, username, password);
    }
}
