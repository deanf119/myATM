package myATM;

import java.util.Scanner;

public class ATM {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		bank theBank = new bank("Bank of Don");
		
		//add user
		user aUser = theBank.addUser("john", "doe", "1234");
		account nAccount = new account("Checking", aUser, theBank);
		aUser.addAccount(nAccount);
		theBank.addAccount(nAccount);
		
		user curUser;
		while(true) {
			//Stay in login till login success
			curUser = ATM.mainMenuPrompt(theBank, sc);
			
			//stay in main menu till user logouts
			ATM.printUserMenu(curUser, sc);
		}
		
	}
	
	
	public static user mainMenuPrompt(bank theBank, Scanner sc) {
		String userId;
		String pin;
		user authUser;
		
		//prompt for correct userid and pin
		do {
			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
			System.out.print("Enter user ID:");
			userId = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();
			
			authUser = theBank.userLogin(userId, pin);
			if(authUser == null) {
				System.out.println("Incorrect user ID/pin combination, Please try again" );
			}
			
		} while(authUser == null);
		
		return authUser;
	}
	
	public static void printUserMenu(user theUser, Scanner sc) {
		
		theUser.printAccountSummary();
		
		int choice;
		
		do {
			System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstname());
			System.out.println(" 1) Show account transaction history.");
			System.out.println(" 2) Withdrawl.");
			System.out.println(" 3) Deposit.");
			System.out.println(" 4) Transfer.");
			System.out.println(" 5) Quit.");
			System.out.println();
			System.out.println("Enter choice: ");
			choice = sc.nextInt();
			if(choice < 1 || choice > 5) {
				System.out.println("Invalid Choice. Please select between 1-5");
			}
		}while(choice < 1 || choice > 5);
		
		//Process choice
		switch(choice) {
		case 1:
			ATM.showTransferHistory(theUser, sc);
		case 2:
			ATM.withdrawFunds(theUser, sc);
		case 3:
			ATM.depositFunds(theUser, sc);
		case 4:
			ATM.transferFunds(theUser, sc);
		break;
		}
		
		//redisplay menu, unless quitting
		if(choice !=5 ) {
			ATM.printUserMenu(theUser, sc);
		}
		
	}
	
	public static void showTransferHistory(user theUser, Scanner sc) {
		int theAccount;
		
		
		
	}
	
	public static void withdrawFunds(user theUser, Scanner sc) {
		
	}
	
	public static void depositFunds(user theUser, Scanner sc) {
		
	}

	public static void transferFunds(user theUser, Scanner sc) {
	
	}

}
