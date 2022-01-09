package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.services.AuthService;
import com.revature.services.UserService;
import com.revature.models.User;

import io.javalin.http.Handler;

public class UserController {
	
		UserService uService = new UserService();
		AuthService aService = new AuthService();
		
		//This layer is where we will parse our JSON into java objects & vice versa
		// Sits between javalin front controller & service later
		// we'll either be getting data from the service layer (which is our DAO?)
		// or sending data to the service later (will probably return some response that it was a success)
		
		public Handler getUsersHandler = (ctx) -> {
			if (ctx.req.getSession() !=null) {
				
				List<User> allUsers = uService.getAllUsers();
				
				Gson gson = new Gson();
				
				String JSONUsers = gson.toJson(allUsers);
				
				ctx.result(JSONUsers);
				ctx.status(200);
			} else {
				ctx.result("Failed to Retrived Users");
				ctx.status(404);
		}
		
	};
	
		public Handler createUserHandler = (ctx) -> {
			if (ctx.req.getSession() !=null) {
			
				String body = ctx.body();
			
				Gson gson = new Gson();
			
				User user = gson.fromJson(body, User.class);
				
				aService.register(user);
			
				ctx.result("User Successfully Added");
				ctx.status(201);
				
			} else {
				ctx.result("Creating User Failed");
				ctx.status(404);
	}
	
};
}
