package myATM;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class user {

	private String firstName;
	private String lastName;
	private String userId;
	private byte userPinHash[];
	private ArrayList<account> accounts;
	
	public user(String firstName, String lastName, String pin, bank theBank) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		
		// hashing pin to save a safe version
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.userPinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//assign universal unique id.
		this.userId = theBank.getNewUserId();
		//create empty list of accounts.
		this.accounts = new ArrayList<account>();
		
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.userId);
		
		
		
	};
}
