package com.revature.models;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.KeySelector.Purpose;

import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

//Menu class with have a method that displays the menu to the user and they can interact with it
//Menu class will use the Scanner class in taking user inputs
public class TemporaryMenu {
	
	UserService uService = new UserService(); //In order to use methods from the UserService class
	
	public void menuOptions() {

		boolean menuOptions = true; //Using this to let the menu continue after user input
		Scanner scan = new Scanner(System.in); //Scanner object to take user input
	
		//Greetings for the user
		System.out.println("--------------------------------------------------------");
		System.out.println("Welcome to the Mobile Legends Employee Management System");
		System.out.println("--------------------------------------------------------");
		System.out.println(" ");
		System.out.println("I know you are here for a purpose. Please, type the CAPITAL LETTER of your choice.");
		
		//display the menu as long as the menuOptions boolean == true
		//display all my menu options until boolean == false
		while(menuOptions) {
			
			//menu options
			System.out.println("A -> Show all Employees");
			System.out.println("B -> Get Employees by Username");
			System.out.println("C -> Get Employee by ID");
			System.out.println("D -> New Employee");
			System.out.println("E -> Exit Application");
			
			//The user chooses a menu option and the scanner takes the input and put it into a String variable
			String input = scan.nextLine();
			
			
			//Takes the user input and the switch statement executes the appropriate code
			switch(input) {
			
			//A break in each case block so that the other cases will not run
			case "A": {
				System.out.println(" ");
				
				//get the List of employees from the repository layer
				List<User> users = uService.getAllUsers();
				
				//enhanced for loop to print out all users one by one
				for (User u : users) {
					System.out.println(u);
				}
				break;
			}
			
			case "B": {
				System.out.println(" ");
				System.out.println("You chose B");
				break;
			}
			
			case "C": {
				System.out.println("Please provide the User ID:");
				int idInput = scan.nextInt();//get user's input for ID
				scan.nextLine(); //To move to the nextLine
				
				List<User> users = uService.getUserById(idInput);
				
				for(User u : users) {
					System.out.println(u);
				}
				break;
			}
			
			case "D": {
				System.out.println("Enter Username:");
				String username = scan.nextLine();
				
				System.out.println("Enter Password:");
				String password = scan.nextLine();
				
				System.out.println("Enter Role ID:");
				System.out.println("1 -> Employee");
				System.out.println("2 -> Finance Manager");

    			String r=scan.nextLine();
    			Role o;
    			
    			if(r.equals("1")) {
    				o = Role.EMPLOYEE;
    			} else if (r.equals("2")) {
    				o = Role.FINANCE_MANAGER;
    			} else {
    				System.out.println("Not a Valid Choice!");
    				o = null;
    				break;
    			}
				
				User userToBeRegistered = new User (username, password, o);
				uService.createUser(userToBeRegistered);
				
				break;
			}
			
			case "E": {
				System.out.println(" ");
				System.out.println("Have a great day! Goodbye.");
				menuOptions = false;
				break;
			}
			
			//Will take the user input that doesn't match any of the cases
			default: {
				System.out.println("Invalid Letter -> TRY AGAIN");
				break;
			}
	
			}
		
		}
		
				
	}//end of menuOptions method
	
} //end of TemporaryMenu class
