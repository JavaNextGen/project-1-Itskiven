package com.revature.controllers;

import java.util.List;
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
			String users = uService.getUserRole(LDTO.getUsername());
			if(aService.login1(LDTO.getUsername(), LDTO.getPassword()) == true) {
				
				if (users.equalsIgnoreCase("employee")) {
					
					//create a user session so that they can access the applications other functionalities
					ctx.req.getSession(); //req is a "request object", we establish sessions through it
					
					ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=none; Secure");
					//return a successful status code
					ctx.status(200); //accepted. (but you could use any 200 level status code
					
					//send a message relaying a success
					ctx.result("Employee Successfully Login");
				} else if (users.equalsIgnoreCase("finance_manager")){
				//create a user session so that they can access the applications other functionalities
				ctx.req.getSession(); //req is a "request object", we establish sessions through it
				
				//return a successful status code
				ctx.status(202); //accepted. (but you could use any 200 level status code
				
				//send a message relaying a success
				ctx.result("Finance Manager Succesfully Login");
				
			} 
			}else {
				
				ctx.status(401); //"unauthorized" status code
				ctx.result("Logging In Failed!");
				
			}
		};
		
		
		
		public Handler createUserHandler = (ctx) -> {
			
				String body = ctx.body();
			
				Gson gson = new Gson();
				

				LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);
				User user = gson.fromJson(body, User.class);
				
				if (aService.compareUsername(LDTO.getUsername()) == false) {
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
//				ctx.result("PLEASE TRY AGAIN - Logging In Failed!");
//				ctx.status(401); //"unauthorized" status code
//			} else if (users.equalsIgnoreCase("employee")) {
//				ctx.result("Successful Login - Employee");
//				
//				//create a user session so that they can access the applications other functionalities
//				ctx.req.getSession(); //req is a "request object", we establish sessions through it
//				
//				//return a successful status code
//				ctx.status(202); //accepted. (but you could use any 200 level status code
//				
//				//send a message relaying a success
//				
//			} else if (users.equalsIgnoreCase("finance_manager")){
//				ctx.result("Successful Login - Finance Manager");
//					//create a user session so that they can access the applications other functionalities
//					ctx.req.getSession(); //req is a "request object", we establish sessions through it
//					
//					//return a successful status code
//					ctx.status(202); //accepted. (but you could use any 200 level status code
//					
//					//send a message relaying a success
//				} 
//		};
		
		//==============================================================================================================================================================
		//==============================================================================================================================================================
		public Handler loginProblemUserHandler = (ctx) -> {
			
			//Request body? (which we get from ctx.body) it's the data that gets sent in with a request
			//GET requests will have empty request bodies, but POST requests, which send data, will have some data.
			String body = ctx.body(); //turn the body (data) of the POST request into a Java String
			
			Gson gson = new Gson(); // create Gson object to make Java <-> JSON conversions
			
			LoginDTO LDTO = gson.fromJson(body, LoginDTO.class); //turn that JSON String into a LoginDTO object
			
			
			//control flow to determine what happens in the event of successful/unsuccessful login
			//invoke the login() method of the AuthService using the username and password from the Login DTO
			String users = uService.getUserRole(LDTO.getUsername());
			
			aService.registerProblem(LDTO.getUsername());
			
			if(aService.login1(LDTO.getUsername(), LDTO.getPassword()) == true) {
				
				if (users.equalsIgnoreCase("employee")) {
					
					//create a user session so that they can access the applications other functionalities
					ctx.req.getSession(); //req is a "request object", we establish sessions through it
					
					ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=none; Secure");
					//return a successful status code
					ctx.status(200); //accepted. (but you could use any 200 level status code
					
					//send a message relaying a success
					ctx.result("Employee Successfully Login");
				} else if (users.equalsIgnoreCase("finance_manager")){
				//create a user session so that they can access the applications other functionalities
				ctx.req.getSession(); //req is a "request object", we establish sessions through it
				
				//return a successful status code
				ctx.status(202); //accepted. (but you could use any 200 level status code
				
				//send a message relaying a success
				ctx.result("Finance Manager Succesfully Login");
				
			} 
			}else {
				
				ctx.status(401); //"unauthorized" status code
				ctx.result("Logging In Failed!");
				
			}
		};
		public Handler getActualUsernameHandler = (ctx) -> {
			if (ctx.req.getSession() !=null) {
				
				String actualUsername = aService.getActualUsername();
				
				Gson gson = new Gson();
				
				String JSONUsers = gson.toJson(actualUsername);
				
				ctx.result(JSONUsers);
				ctx.status(200);
			} else {
				ctx.result("NOT LOGGED IN - Failed to Retrived Users");
				ctx.status(401);
		}
		
	};
}
			
			
		



