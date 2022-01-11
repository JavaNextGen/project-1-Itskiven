package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.TemporaryMenu;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
    	
    	UserController uc = new UserController();
    	ReimbursementController rc = new ReimbursementController();
    	AuthController ac = new AuthController();
    	
    	//Testing Database Connectivity
    	try(Connection connect = ConnectionFactory.getConnection()){
    		System.out.println("Connection Successful");
    	} catch(SQLException e) {
    		System.out.println("Connection Failed");
    		e.printStackTrace();
    	}
    	
    	//Instantiate a TemporaryMenu object and use its method
//    	TemporaryMenu menu = new TemporaryMenu();
//    	menu.menuOptions();
    	
    	
    	
    	Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); // allows the server to process JS requests from anywhere
				}
			).start(3000);
	
    	
    	
    	//===================================================USERSERVICE========================================
    	app.get("/user/{username}", uc.getByUsernameHandler);
    	app.get("/user", uc.getAllUsersHandler);
    	app.get("/user/id/{Id}", uc.getUserByIdHandler);
    	app.post("/user/role/{username}", uc.getUserRoleHandler);
    	//===============================================REIMBURSEMENTSERVICE===================================
    	app.get("/reimbursement", rc.getPendingReimbursementHandler);
    	app.get("/reimbursement/{username}", rc.getOwnReimbursementHandler);
    	app.get("/reimbursement/rs/{username}", rc.getResolvedReimbursementHandler);
    	app.get("/reimbursement/stat/status", rc.getReimbursementByStatusHandler);
    	app.post("/reimbursement/submit", rc.submitReimbursementHandler);
    	app.put("/reimbursement", rc.processHandler);
    	app.put("/reimbursement/update", rc.updateHandler);
    	
    	
    	//===================================================AUTHSERVICE========================================
		app.post("/login", ac.loginUserHandler);
		app.post("/user/register", ac.createUserHandler);
		
		
		///==============================================================
//		app.get("/user/problem", ac.getActualUsernameHandler);
//		app.post("/login/solution", ac.loginProblemUserHandler);
//		app.post("/login1", ac.loginHandler);
    	
    } //end of main method
}//end of Driver class
