package com.revature.models;

import java.util.List;
import java.util.Scanner;

import com.revature.repositories.UserDAO;

//Menu class with have a method that displays the menu to the user and they can interact with it
//Menu class will use the Scanner class in taking user inputs
public class TemporaryMenu {
	
	UserDAO uDAO = new UserDAO(); //we need this object to use the methods in the UserDAO
	
	public void menuOptions() {

		boolean menuOptions = true; //Using this to let the menu continue after user input
		Scanner scan = new Scanner(System.in); //Scanner object to take user input
	
		//Greetings for the user
		System.out.println("--------------------------------------------------------");
		System.out.println("Welcome to the Mobile Legends Employee Management System");
		System.out.println("--------------------------------------------------------");
		
		
		//display the menu as long as the menuOptions boolean == true
		//display all my menu options until boolean == false
		while(menuOptions) {
			
			//menu options
			System.out.println("Type the LETTER of your choice");
			System.out.println("A -> Show all Employees");
			System.out.println("B -> Show all Employees with a certain ID");
			System.out.println("C -> Exit Application");
			
			//The user chooses a menu option and the scanner takes the input and put it into a String variable
			String input = scan.nextLine();
			
			
			//Takes the user input and the switch statement executes the appropriate code
			switch(input) {
			
			//A break in each case block so that the other cases will not run
			case "A": {

				//get the List of employees from the repository layer
				List<User> users = uDAO.getUsers();
				break;
			}
			
			case "B": {
				System.out.println("Functionality tbd");
				break;
			}
			
			case "C": {
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
