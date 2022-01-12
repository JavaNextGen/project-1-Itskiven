package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.services.UserService;
import com.revature.models.LoginDTO;
import com.revature.models.User;

import io.javalin.http.Handler;

public class UserController {
	
		UserService uService = new UserService();
		
		//This layer is where we will parse our JSON into java objects & vice versa
		// Sits between javalin front controller & service later
		// we'll either be getting data from the service layer (which is our DAO?)
		// or sending data to the service later (will probably return some response that it was a success)
		
		public Handler getAllUsersHandler = (ctx) -> {
			if (ctx.req.getSession() !=null) {
				
				List<User> allUsers = uService.getAllUsers();
				
				Gson gson = new Gson();
				
				String JSONUsers = gson.toJson(allUsers);
				
				ctx.result(JSONUsers);
				ctx.status(200);
			} else {
				ctx.result("NOT LOGGED IN - Failed to Retrived Users");
				ctx.status(401);
		}
		
	};

		public Handler getByUsernameHandler = (ctx) -> {
			if (ctx.req.getSession() != null) {
				
				String uUsername = ctx.pathParam("username");
				Optional<User> user = uService.getByUsername(uUsername);
				
				Gson gson = new Gson();
				
				String JSONUser =  gson.toJson(user);
				
				ctx.result(JSONUser);
				ctx.status(200);
			} else {
				ctx.result("Failed To Retrieved Users By Username");
				ctx.status(404);
			}
			
		};

		public Handler getUserByIdHandler = (ctx)-> {
            if(ctx.req.getSession() != null) {
                
            	String uId = ctx.pathParam("Id");
                Optional<User> employee = uService.getUserById(Integer.parseInt(uId)); 
                
                Gson gson = new Gson(); 
                
                String JSONUser = gson.toJson(employee); 
                
                ctx.result(JSONUser); 
                
                ctx.status(200);
                
            } else {
                ctx.result("Failed to retrieve employees!");
                ctx.status(404); 
            }
        };

		public Handler getUserRoleHandler = (ctx) -> {
			if (ctx.req.getSession() != null) {
				
				String uUsername = ctx.pathParam("username");
				String user = uService.getUserRole(uUsername);
				
				Gson gson = new Gson();
				
				String JSONUser =  gson.toJson(user);
				
				ctx.result(JSONUser);
				ctx.status(200);
			} else {
				ctx.status(202);
			}
			
		};

		public Handler getAuthorHandler = (ctx) -> {
			if (ctx.req.getSession() != null) {
				
				String uUsername = ctx.pathParam("username");
				int author = uService.getAuthor(uUsername);
				
				Gson gson = new Gson();
				
				String JSONUser =  gson.toJson(author);
				
				ctx.result(JSONUser);
				ctx.status(200);
			} else {
				ctx.status(202);
			}
			
		};
		
}
