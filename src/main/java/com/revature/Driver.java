package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.UserController;
import com.revature.models.TemporaryMenu;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
    	
//    	UserController uc = new UserController();
    	
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
    	
    	
    	
//    	Javalin app = Javalin.create(
//				config -> {
//					config.enableCorsForAllOrigins(); // allows the server to process JS requests from anywhere
//				}
//			).start(3000);
//	
//    	app.get("/user", uc.getUsersHandler);
//		app.post("/user", uc.createUserHandler);
    	
    	
    } //end of main method
}//end of Driver class
