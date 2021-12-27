package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.TemporaryMenu;
import com.revature.util.ConnectionFactory;

public class Driver {

    public static void main(String[] args) {
    	
    	//Testing Database Connectivity
    	try(Connection connect = ConnectionFactory.getConnection()){
    		System.out.println("Connection Successful");
    	} catch(SQLException e) {
    		System.out.println("Connection Failed");
    		e.printStackTrace();
    	}
    	
    	//Instantiate a TemporaryMenu object and use its method
    	TemporaryMenu menu = new TemporaryMenu();
    	menu.menuOptions();
    	
    } //end of main method
}//end of Driver class
