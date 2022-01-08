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
			
			case "E": {
				
				System.out.println(" ");
				System.out.println("Enter Username:");
				String username = scan.nextLine();
				System.out.println(" ");
				
				System.out.println("Enter Password:");
				String password = scan.nextLine();
				System.out.println(" ");
				
				
				//WHAT TO DO IF WRONG USERNAME AND PASSWORD INPUT!
				aService.login(username, password);		
				
				String users = uService.getUserRole(username, password);
				int author = uService.getAuthor(username, password);
				
				
				
				if (users.equalsIgnoreCase("employee")) {
					System.out.println("You are an Employee");
					boolean reimbursement = true;
					while (reimbursement) {
						System.out.println("What do you want to do? Please select the CAPITAL LETTER of your choice.");
						System.out.println("A -> Submit a Reimbursement Request"); //done
						System.out.println("B -> Edit a Pending Reimbursement Request");  //done
						System.out.println("C -> View Pending Reimbursement Requests"); //done
						System.out.println("D -> View Resolved Reimbursement Requests"); //done
						System.out.println("E -> Logout"); //done
						
						String input1 = scan.nextLine();
						
						
						switch(input1) {
						
						case "A": {
							System.out.println("How Much?");
							
							double amount = scan.nextDouble();
							scan.nextLine();
							
							System.out.println("Enter Reimbursement Type:");
							System.out.println("1 -> Lodging");
							System.out.println("2 -> Travel");
							System.out.println("3 -> Food");
							System.out.println("4 -> Other");
							
							int r = scan.nextInt();
							scan.nextLine();
							Status status = Status.PENDING;
							ReimbursementType typee;
							
							if (r == 1) {
								typee = ReimbursementType.LODGING;
							} else if (r == 2) {
								typee = ReimbursementType.TRAVEL;
							} else if (r == 3) {
								typee = ReimbursementType.FOOD;
							} else if (r == 4) {
								typee = ReimbursementType.OTHER;
							} else {
								System.out.println("Unsuccessful Submission! Try Again.");
								break;
							}
							
							Reimbursement reimbursementToBeSubmitted = new Reimbursement(amount, author, status, typee);
							rService.submitReimbursement(reimbursementToBeSubmitted);
							System.out.println(" ");
							break;
						}
						
						case "B": {
							
							System.out.println("Provide the Reimbursement ID to be Updated:");
							int id = scan.nextInt();
							scan.nextLine();
							
							int verification = rService.getIntAuthor(id);
							String stat = rService.getCurrentStatus(id);
							if (verification == author && stat.equalsIgnoreCase("pending")) {
								System.out.println("How Much is the Amount?");
								
								double amount = scan.nextDouble();
								scan.nextLine();
								
								System.out.println("Update Reimbursement Type:");
								System.out.println("1 -> Lodging");
								System.out.println("2 -> Travel");
								System.out.println("3 -> Food");
								System.out.println("4 -> Other");
								
								int r = scan.nextInt();
								scan.nextLine();
								ReimbursementType typee;
								
								if (r == 1) {
									typee = ReimbursementType.LODGING;
								} else if (r == 2) {
									typee = ReimbursementType.TRAVEL;
								} else if (r == 3) {
									typee = ReimbursementType.FOOD;
								} else if (r == 4) {
									typee = ReimbursementType.OTHER;
								} else {
									System.out.println("Unsuccessful Submission! Try Again.");
									break;
								}
								
								Reimbursement updatePendingReimbursement = new Reimbursement(id, amount, typee);
								
								
								//get the List of employees from the repository layer
								Reimbursement reimbursement1 = rService.update(updatePendingReimbursement);
								
								//enhanced for loop to print out all users one by one
								
									System.out.println(reimbursement1);
									
								
							
								System.out.println(" ");
							} else if (verification == author && !stat.equalsIgnoreCase("pending")) {
								System.out.println("Reimbursement Already Resolved!");
							} else {
								System.out.println("You are not the Owner of this Reimbursement Request!");
								System.out.println("");
								break;
							}
												
							break;
						}
						
						case "C": {
							//get the List of employees from the repository layer
							List<Reimbursement> reimbursements = rService.getOwnReimbursement(username, password);
							
							//enhanced for loop to print out all users one by one
							for (Reimbursement r : reimbursements) {
								System.out.println(r);
								
							}
							System.out.println(" ");
							break;
						}
						
						case "D": {
//							//get the List of employees from the repository layer
							List<Reimbursement> reimbursements = rService.getResolvedReimbursement(username, password);
							
							//enhanced for loop to print out all users one by one
							for (Reimbursement r : reimbursements) {
								System.out.println(r);
								
							}
							System.out.println(" ");
							break;
						}
							
						case "E": {
							reimbursement = false;
							System.out.println(" ");
							break;
						
							}
						default: {
							System.out.println(" ");
							System.out.println("Invalid Letter -> TRY AGAIN");
							break;
						}
						}
						
						
						
					}
					break;
					
				} else {
					System.out.println("You are a Finance Manager");
					boolean reimbursement = true;
					while (reimbursement) {
					System.out.println("What do you want to do? Please select the CAPITAL LETTER of your choice.");
					System.out.println("A -> Submit a Reimbursement Request"); //done
					System.out.println("B -> Approve/Deny Reimbursement Requests"); //done
					System.out.println("C -> View All Pending Reimbursement Requests"); //done
					System.out.println("D -> View All Reimbursement Requests"); //done
					System.out.println("E -> View Own Pending Reimbursements"); //done
					System.out.println("F -> Logout"); //done
					
					String input1 = scan.nextLine();
					
					
					switch(input1) {
					
					case "A": {
						System.out.println("How Much?");
						
						double amount = scan.nextDouble();
						scan.nextLine();
						
						System.out.println("Enter Reimbursement Type:");
						System.out.println("1 -> Lodging");
						System.out.println("2 -> Travel");
						System.out.println("3 -> Food");
						System.out.println("4 -> Other");
						
						int r = scan.nextInt();
						scan.nextLine();
						Status status = Status.PENDING;
						ReimbursementType typee;
						
						if (r == 1) {
							typee = ReimbursementType.LODGING;
						} else if (r == 2) {
							typee = ReimbursementType.TRAVEL;
						} else if (r == 3) {
							typee = ReimbursementType.FOOD;
						} else if (r == 4) {
							typee = ReimbursementType.OTHER;
						} else {
							System.out.println("Unsuccessful Submission! Try Again.");
							break;
						}
						
						Reimbursement reimbursementToBeSubmitted = new Reimbursement(amount, author, status, typee);
						rService.submitReimbursement(reimbursementToBeSubmitted);
						System.out.println(" ");
						break;
					}
					case "B": {

						System.out.println("Give Reimbursement ID you want to process");
						int id = scan.nextInt();
						scan.nextLine();
						
						int verification = rService.getIntAuthor(id);
						int resolver = author;
						String stat = rService.getCurrentStatus(id);
						if (verification == author) {
							System.out.println("You can't Process Self Request!");
						} else if (verification != author && stat.equalsIgnoreCase("pending")) {
							System.out.println("Final Decision?");
							System.out.println("1 -> Approve");
							System.out.println("2 -> Deny");
							
							
							Status status = null;
							int r = scan.nextInt();
							scan.nextLine();
							
							if (r == 1) {
								status = Status.APPROVED;
							} else if (r == 2) {
								status = Status.DENIED;
						}
							Reimbursement reimbursementToBeProcessed = new Reimbursement (id, status, resolver);
							Reimbursement processedReimbursement = rService.process(reimbursementToBeProcessed);
							System.out.println(processedReimbursement);
							System.out.println("");
						} else if (verification != author && !stat.equalsIgnoreCase("pending")) {
							System.out.println("Reimbursement ID Doesn't Exist!");
						} else {
							System.out.println("Reimbursement ID Already Resolved!");
						}
						
						break;
					}
					case "C": {
						//get the List of employees from the repository layer
						List<Reimbursement> reimbursements = rService.getPendingReimbursements();
						
						//enhanced for loop to print out all users one by one
						for (Reimbursement r : reimbursements) {
							System.out.println(r);
							
						}
						System.out.println(" ");
						break;
					}
					case "D": {
						
						List<Reimbursement> reimbursements = rService.getReimbursementsByStatus();
						
						//enhanced for loop to print out all users one by one
						for (Reimbursement r : reimbursements) {
							System.out.println(r);
						}
						System.out.println(" ");
						break;
					
					}
					case "E": {
						//get the List of employees from the repository layer
						List<Reimbursement> reimbursements = rService.getOwnReimbursement(username, password);
						
						//enhanced for loop to print out all users one by one
						for (Reimbursement r : reimbursements) {
							System.out.println(r);
							
						}
						System.out.println(" ");
						break;
					}
					case "F": {
						reimbursement = false;
						System.out.println(" ");
						break;
					}
					default: {
						System.out.println(" ");
						System.out.println("Invalid Letter -> TRY AGAIN");
						break;
					}
					}
				} 
					break;
				}
			}
			
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
