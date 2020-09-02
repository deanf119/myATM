package myATM;
import java.util.ArrayList;
import java.util.Random;


public class bank {
	private String name;
	private ArrayList<user> users;
	private ArrayList<account> accounts;
	
	public String getNewUserId() {
		String userId;
		Random idGenerator = new Random();
		int len = 6;
		boolean nonUnique;
		
		do {
			userId = "";
			for (int i = 0; i < len; i++) {
				userId += ((Integer)idGenerator.nextInt(10)).toString();
			}
			
			nonUnique = false;
			for(user u: this.users) {
				if(userId.compareTo(u.getUserId()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		return userId;
	};
	
	public String getNewAccountId() {
		String accountId;
		Random idGenerator = new Random();
		int len = 10;
		boolean nonUnique;
		
		do {
			accountId = "";
			for (int i = 0; i < len; i++) {
				accountId += ((Integer)idGenerator.nextInt(10)).toString();
			}
			
			nonUnique = false;
			for(account a: this.accounts) {
				if(accountId.compareTo(a.getAccountId()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		return accountId;
	};
	
	public void addAccount(account anAccount) {
		this.accounts.add(anAccount);
	}
	
	public user addUser(String firstName, String lastName, String pin) {
		user newUser = new user(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		account newAccount = new account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	public user userLogin(String userId, String pin) {
		
		for (user u: this.users) {
			if(u.getUserId().compareTo(userId) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;

	}
	
	
}
