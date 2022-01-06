package com.revature.models;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.xml.crypto.KeySelector.Purpose;

import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

//Menu class with have a method that displays the menu to the user and they can interact with it
//Menu class will use the Scanner class in taking user inputs
public class TemporaryMenu {
	
	UserService uService = new UserService(); //In order to use methods from the UserService class
	ReimbursementService rService = new ReimbursementService();
	AuthService aService = new AuthService();
	Scanner scan = new Scanner(System.in); //Scanner object to take user input
	
	public void menuOptions() {

		boolean menuOptions = true; //Using this to let the menu continue after user input
		
	
		//Greetings for the user
		System.out.println("--------------------------------------------------------");
		System.out.println("Welcome to the Mobile Legends Employee Management System");
		System.out.println("--------------------------------------------------------");
		System.out.println(" ");
		
		
		//display the menu as long as the menuOptions boolean == true
		//display all my menu options until boolean == false
		while(menuOptions) {
			
			//menu options
			System.out.println("PLEASE ENTER THE CAPITAL LETTER OF YOUR CHOICE");
			System.out.println("A -> Show all Employees");
			System.out.println("B -> Get Employees by Username");
			System.out.println("C -> Get Employee by ID");
			System.out.println("D -> New Employee");
			System.out.println("E -> Log-in");
			System.out.println("F -> Exit Application");
			
			//The user chooses a menu option and the scanner takes the input and put it into a String variable
			String input = scan.nextLine();
			
			
			//Takes the user input and the switch statement executes the appropriate code
			switch(input) {
			
			//A break in each case block so that the other cases will not run
			case "A": {
				
				//get the List of employees from the repository layer
				List<User> users = uService.getAllUsers();
				
				//enhanced for loop to print out all users one by one
				for (User u : users) {
					System.out.println(u);
					
				}
				System.out.println(" ");
				break;
			}
			
			case "B": {
				System.out.println(" ");
				System.out.println("Please provide Username:");
				String username = scan.nextLine(); //To move to the nextLine
				System.out.println(" ");
				Optional<User> users = uService.getByUsername(username);
				
				System.out.println(users);
				
				System.out.println(" ");
				break;
			}
			
			case "C": {
				System.out.println(" ");
				System.out.println("Please provide the User ID:");
				int idInput = scan.nextInt();//get user's input for ID
				scan.nextLine(); //To move to the nextLine
				System.out.println(" ");
				Optional<User> users = uService.getUserById(idInput);
				
				System.out.println(users);
				
				System.out.println(" ");
				break;
			}
			
			case "D": {
				System.out.println(" ");
				System.out.println("Enter Username:");
				String username = scan.nextLine();
				System.out.println(" ");
				
				System.out.println("Enter Password:");
				String password = scan.nextLine();
				System.out.println(" ");
				
				System.out.println("Enter First Name:");
				String fname = scan.nextLine();
				System.out.println(" ");
				
				System.out.println("Enter Last Name:");
				String lname = scan.nextLine();
				System.out.println(" ");
				
				System.out.println("Enter Email:");
				String email = scan.nextLine();
				System.out.println(" ");
				
				System.out.println("Enter Role ID:");
				System.out.println("1 -> Employee");
				System.out.println("2 -> Finance Manager");
    			int r=scan.nextInt();
    			scan.nextLine();
    			Role role;
    			
    			if(r == 1) {
    				role = Role.EMPLOYEE;
    			} else if (r == 2) {
    				role = Role.FINANCE_MANAGER;
    			} else {
    				System.out.println("Not a Valid Choice!");
    				role = null;
    				break;
    			}
    			System.out.println(" ");
				
				User userToBeRegistered = new User (username, password, fname, lname, email, role);
				aService.register(userToBeRegistered);
				System.out.println(" ");
				break;
			}
			
//			case "E": {
//				
//				System.out.println(" ");
//				System.out.println("Enter Username:");
//				String username = scan.nextLine();
//				System.out.println(" ");
//				
//				System.out.println("Enter Password:");
//				String password = scan.nextLine();
//				System.out.println(" ");
//				
//				String users = uService.getUserRole(username, password);
//				User author = uService.getAuthor(username, password);
//				User resolver = null;
//				Status status = Status.PENDING;
//				String typee;
//				
//				
//				System.out.println(users);
//		
//				if (users.equalsIgnoreCase("employee")) {
//					System.out.println("You are an Employee");
//					boolean reimbursement = true;
//					while (reimbursement) {
//						System.out.println("What do you want to do? Please select the CAPITAL LETTER of your choice.");
//						System.out.println("A -> Submit a Reimbursement Request");
//						System.out.println("B -> View Reimbursement Statuses");
//						
//						
//						String input1 = scan.nextLine();
//						
//						
//						switch(input1) {
//						
//						case "A": {
//							System.out.println("How Much?");
//							
//							double amount = scan.nextDouble();
//							scan.nextLine();
//							
//							Reimbursement reimbursementToBeSubmitted = new Reimbursement(amount, author, resolver, status, typee);
//							rService.submitReimbursement(reimbursementToBeSubmitted);
//							System.out.println(" ");
//							break;
//						}
//						
//						case "B": {
//							
//						}
//						}
//						
//						
//					}
//					break;
//				} else {
//					System.out.println("You are a Finance Manager");
//					System.out.println("What do you want to do? Please select the CAPITAL LETTER of your choice.");
//					System.out.println("A -> Submit a Reimbursement Request");
//					System.out.println("B -> Approve/Deny Reimbursement Requests");
//					break;
//				} 
//				
//			}
//			
			case "F": {
				System.out.println(" ");
				System.out.println("Have a great day! Goodbye.");
				menuOptions = false;
				break;
			}
			
			//Will take the user input that doesn't match any of the cases
			default: {
				System.out.println(" ");
				System.out.println("Invalid Letter -> TRY AGAIN");
				break;
			}
	
			}
		
		}
		
				
	}//end of menuOptions method
	
} //end of TemporaryMenu class
