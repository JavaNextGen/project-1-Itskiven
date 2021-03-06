package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class ReimbursementController {
	
	ReimbursementService rService = new ReimbursementService();
	UserService uService = new UserService();
	
	public Handler submitReimbursementHandler= (ctx) -> {
		if (ctx.req.getSession() !=null) {
			
			String body = ctx.body();
		
			Gson gson = new Gson();
		
			Reimbursement submit = gson.fromJson(body, Reimbursement.class);
			
//			int author = uService.getAuthor(submit.getAuthor());
//	    	double amount;
//	    	Status status = Status.PENDING;
//			ReimbursementType typee;
//	    	Reimbursement reimbursementToBeSubmitted = new Reimbursement(amount, author, status, typee);
//	    	rDAO.submit(reimbursementToBeSubmitted);
			
			
			rService.submitReimbursement(submit);
		
			ctx.result("Reimbursement Successfully Added");
			ctx.status(201);
			
		} else {
			ctx.result("Reimbursement Submission Failed");
			ctx.status(404);
		}

	};

	public Handler getPendingReimbursementHandler = (ctx) -> {
			if (ctx.req.getSession() !=null) {
				
				List<Reimbursement> reimbursement = rService.getPendingReimbursements();
				
				Gson gson = new Gson();
				
				String JSONReimbursements = gson.toJson(reimbursement);
				
				ctx.result(JSONReimbursements);
				ctx.status(200);
			} else {
				ctx.result("Retrieving Pending Reimbursements Failed!");
				ctx.status(401);
		}
		
	};
	
	public Handler getOwnReimbursementHandler = (ctx) -> {
		if (ctx.req.getSession() !=null) {
			
			String uUsername = ctx.pathParam("username");
			List<Reimbursement> reimbursement = rService.getOwnReimbursement(uUsername);
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(reimbursement);
			
			ctx.result(JSONReimbursements);
			ctx.status(200);
		} else {
			ctx.result("Retrieving Own Reimbursements Failed!");
			ctx.status(401);
	}
	
};
	public Handler getResolvedReimbursementHandler = (ctx) -> {
		if (ctx.req.getSession() !=null) {
			
			String uUsername = ctx.pathParam("username");
			List<Reimbursement> reimbursement = rService.getResolvedReimbursement(uUsername);
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(reimbursement);
			
			ctx.result(JSONReimbursements);
			ctx.status(200);
		} else {
			ctx.result("Retrieving Resolved Reimbursements Failed!");
			ctx.status(401);
	}
	
};
	public Handler getReimbursementByStatusHandler = (ctx) -> {
		if (ctx.req.getSession() !=null) {
			
		
			List<Reimbursement> reimbursement = rService.getReimbursementsByStatus();
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(reimbursement);
			
			ctx.result(JSONReimbursements);
			ctx.status(200);
		} else {
			ctx.result("Retrieving Reimbursements By Status Failed!");
			ctx.status(401);
	}
	
};
	public Handler processHandler = (ctx) -> {
		if (ctx.req.getSession() !=null) {
			
			String body = ctx.body();
		
			Gson gson = new Gson();
		
			Reimbursement submit = gson.fromJson(body, Reimbursement.class);
			
			if (rService.ifIdExist(submit.getId()) == false){
				ctx.status(404);
				ctx.result("ID DOES NOT EXIST!");
			} else if (rService.ifIdExist(submit.getId()) == true && rService.reimbursementIdResolved(submit.getId()) == true) {
				ctx.result("ID EXIST AND REIMBURSEMENT IS ALREADY RESOLVED");
				ctx.status(400);
			} else if (rService.ifIdExist(submit.getId()) == true && rService.reimbursementIdResolved(submit.getId()) == false){
				
				if (rService.getIntAuthor(submit.getId()) == submit.getResolver()) {
					ctx.status(403);
				} else {
					Reimbursement reimbursementBeProcessed = new Reimbursement (submit.getId(), submit.getStatus(), submit.getResolver());			
					rService.process(reimbursementBeProcessed);
					ctx.result("Reimbursement Successfully Updated");
					ctx.status(202);
				}
			} 
		}


	};
	
	
	
	public Handler updateHandler = (ctx) -> {
//		if (ctx.req.getSession() !=null) {
//			
//			String body = ctx.body();
//		
//			Gson gson = new Gson();
//		
//			Reimbursement submit = gson.fromJson(body, Reimbursement.class);
//			
//			if (rService.ifIdExist(submit.getId()) == false){
//				ctx.status(404);
//				ctx.result("ID DOES NOT EXIST!");
//			} else if (rService.ifIdExist(submit.getId()) == true && rService.reimbursementIdResolved(submit.getId()) == true) {
//				ctx.result("ID EXIST AND REIMBURSEMENT IS ALREADY RESOLVED");
//				ctx.status(400);
//			} else if (rService.ifIdExist(submit.getId()) == true && rService.reimbursementIdResolved(submit.getId()) == false){
//				Reimbursement updatePendingReimbursement = new Reimbursement (submit.getId(), submit.getAmount(), submit.getType());			
//				rService.update(updatePendingReimbursement);
//				ctx.result("Reimbursement Successfully Updated");
//				ctx.status(202);
//			} 
//		}
	};
//	public Handler getReimbursementByIdHandler = (ctx) -> {
//		if (ctx.req.getSession() !=null) {
//
//			String body = ctx.body();
//			
//			Gson gson = new Gson();
//			
//			LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);
//			String JSONReimbursements = gson.toJson(reimbursement);
//			
//			System.out.println(reimbursement);
//			if (reimbursement.equals(null)) {
//				ctx.status(204);
//				ctx.result("ID DOES NOT EXIST!");
//				ctx.result(JSONReimbursements);
//			} 
			
			
//			ctx.status(200);
//			ctx.result("Retrieving ID Success");
//		}
//		else {
//			ctx.result("Retrieving ID Failed");
//			ctx.status(401);
//	}
	
//};
}
