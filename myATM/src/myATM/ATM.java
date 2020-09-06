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
			System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstname());
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
		
		do {
			System.out.printf("Enter the number (1-%d) of the account whose transactions you want the see: ", theUser.numAccounts());
			theAccount = sc.nextInt()-1;
			if(theAccount < 0 || theAccount >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		}while(theAccount < 0 || theAccount >= theUser.numAccounts());
		
		theUser.printAccountTransactionHistory(theAccount);
		
	}
	
	public static void withdrawFunds(user theUser, Scanner sc) {
		int fromAccount;
		double amount;
		double accountBalance;
		String memo;
		
		//get from account
		do {
			System.out.printf("Enter the number (1-%d) of the account to withdraw from: " , theUser.numAccounts());
			fromAccount = sc.nextInt()-1;
			if(fromAccount < 0 || fromAccount >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while(fromAccount < 0 || fromAccount >= theUser.numAccounts());
		
		accountBalance = theUser.getAccountBalance(fromAccount);
		
		//get amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%0.2f): ", accountBalance);
			amount = sc.nextDouble();
			if(amount < 0) {
				System.out.println("Amount must be greater than 0. Please try again.");
			} else if(amount > accountBalance) {
				System.out.printf("Amount must be less that the account balance of $%0.2f. \n. Please try again.", accountBalance);
			}
		} while(amount < 0 || amount > accountBalance);
		
		//gobble up rest if input
		sc.nextLine();
		
		//get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		
		//do the withdrawl
		theUser.addAccountTransaction(fromAccount, -1*amount, memo);
		
	}
	
	public static void depositFunds(user theUser, Scanner sc) {
		int toAccount;
		double amount;
		double accountBalance;
		String memo;
		
		//get from account
		do {
			System.out.printf("Enter the number (1-%d) of the account to deposit to: ", theUser.numAccounts());
			toAccount = sc.nextInt()-1;
			if(toAccount < 0 || toAccount >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while(toAccount < 0 || toAccount >= theUser.numAccounts());
		
		accountBalance = theUser.getAccountBalance(toAccount);
		
		//get amount to transfer
		do {
			System.out.println("Enter the amount to deposit: $");
			amount = sc.nextDouble();
			if(amount < 0) {
				System.out.println("Amount must be greater than 0. Please try again.");
			} 
		} while(amount < 0);
		
		//gobble up rest if input
		sc.nextLine();
		
		//get a memo
		System.out.println("Enter a memo: ");
		memo = sc.nextLine();
		
		//do the deposit
		theUser.addAccountTransaction(toAccount, amount, memo);
				
	}

	public static void transferFunds(user theUser, Scanner sc) {
		int fromAccount;
		int toAccount;
		double amount;
		double accountBalance;
		
		//get from account
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer from: " , theUser.numAccounts());
			fromAccount = sc.nextInt()-1;
			if(fromAccount < 0 || fromAccount >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while(fromAccount < 0 || fromAccount >= theUser.numAccounts());
		
		accountBalance = theUser.getAccountBalance(fromAccount);
		
		//get to account
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer to: " , theUser.numAccounts());
			toAccount = sc.nextInt()-1;
			if(toAccount < 0 || toAccount >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while(toAccount < 0 || toAccount >= theUser.numAccounts());
		
		//get amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%0.2f): ", accountBalance);
			amount = sc.nextDouble();
			if(amount < 0) {
				System.out.println("Amount must be greater than 0. Please try again.");
			} else if(amount > accountBalance) {
				System.out.printf("Amount must be less that the account balance of $%0.2f. \n. Please try again.", accountBalance);
			}
		}while(amount < 0 || amount > accountBalance);
		
		//do transfer
		theUser.addAccountTransaction(fromAccount, -1*amount, String.format("Transfer to account %s", theUser.getAccountId(toAccount)));
		theUser.addAccountTransaction(toAccount, amount, String.format("Transfer from account %s", theUser.getAccountId(fromAccount)));
	}

}
