package com.revature.controllers;

import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class AuthController {
	
	AuthService aService = new AuthService();
	UserService uService = new UserService();
	
		public Handler loginUserHandler = (ctx) -> {
			
			//Request body? (which we get from ctx.body) it's the data that gets sent in with a request
			//GET requests will have empty request bodies, but POST requests, which send data, will have some data.
			String body = ctx.body(); //turn the body (data) of the POST request into a Java String
			
			Gson gson = new Gson(); // create Gson object to make Java <-> JSON conversions
			
			LoginDTO LDTO = gson.fromJson(body, LoginDTO.class); //turn that JSON String into a LoginDTO object
			
			
			//control flow to determine what happens in the event of successful/unsuccessful login
			//invoke the login() method of the AuthService using the username and password from the Login DTO
			String users = uService.getUserRole(LDTO.getUsername(), LDTO.getPassword());
			if(aService.login1(LDTO.getUsername(), LDTO.getPassword())) {
				
				if (users.equalsIgnoreCase("employee")) {
					//create a user session so that they can access the applications other functionalities
					ctx.req.getSession(); //req is a "request object", we establish sessions through it
					
					//return a successful status code
					ctx.status(202); //accepted. (but you could use any 200 level status code
					
					//send a message relaying a success
					ctx.result("Employee Successfully Login");
				} else if (users.equalsIgnoreCase("finance_manager")){
				//create a user session so that they can access the applications other functionalities
				ctx.req.getSession(); //req is a "request object", we establish sessions through it
				
				//return a successful status code
				ctx.status(202); //accepted. (but you could use any 200 level status code
				
				//send a message relaying a success
				ctx.result("Finance Manager Succesfully Login");
				
			} else {
				
				ctx.status(401); //"unauthorized" status code
				ctx.result("Logging In Failed!");
				
			}
			}
		};
		
		public Handler createUserHandler = (ctx) -> {
			if (ctx.req.getSession(false) !=null) {
			
				String body = ctx.body();
			
				Gson gson = new Gson();
			
				User user = gson.fromJson(body, User.class);
				
				aService.register(user);
			
				ctx.result("User Successfully Added");
				ctx.status(201);
				
			} else {
				ctx.result("NOT LOGGED IN - Creating User Failed");
				ctx.status(404);
			}
	
		};

//		public Handler loginHandler = (ctx) -> {
//			
//			//Request body? (which we get from ctx.body) it's the data that gets sent in with a request
//			//GET requests will have empty request bodies, but POST requests, which send data, will have some data.
//			String body = ctx.body(); //turn the body (data) of the POST request into a Java String
//			
//			Gson gson = new Gson(); // create Gson object to make Java <-> JSON conversions
//			
//			LoginDTO LDTO = gson.fromJson(body, LoginDTO.class); //turn that JSON String into a LoginDTO object
//			
//			Optional<User> log = aService.login(LDTO.getUsername(), LDTO.getPassword());
//			String users = uService.getUserRole(LDTO.getUsername(), LDTO.getPassword());
//			int author = uService.getAuthor(LDTO.getUsername(), LDTO.getPassword());
//			//control flow to determine what happens in the event of successful/unsuccessful login
//			//invoke the login() method of the AuthService using the username and password from the Login DTO
//			
//			if (log.equals(Optional.empty())){
//				System.out.println("PLEASE TRY AGAIN");
//				ctx.status(401); //"unauthorized" status code
//				ctx.result("Logging In Failed!");
//			} else if (users.equalsIgnoreCase("employee")) {
//			System.out.println("You are an Employee");
//				
//				//create a user session so that they can access the applications other functionalities
//				ctx.req.getSession(); //req is a "request object", we establish sessions through it
//				
//				//return a successful status code
//				ctx.status(202); //accepted. (but you could use any 200 level status code
//				
//				//send a message relaying a success
//				ctx.result("Login Success");
//				
//			} else if (users.equalsIgnoreCase("finance_manager")){
//				System.out.println("You are a Finance Manager");
//					//create a user session so that they can access the applications other functionalities
//					ctx.req.getSession(); //req is a "request object", we establish sessions through it
//					
//					//return a successful status code
//					ctx.status(202); //accepted. (but you could use any 200 level status code
//					
//					//send a message relaying a success
//					ctx.result("Login Success");
//				} 
//		};
}
			
			
		


