package myATM;
import java.util.ArrayList;

public class account {
	private String name;
	private String accountId;
	private user holder;
	private ArrayList<transaction> transactions;
	
	public account(String name, user holder, bank theBank) {
		
		this.name = name;
		this.holder = holder;
		
		this.accountId = theBank.getNewAccountId();
		
		this.transactions = new ArrayList<transaction>();
		
	};
	
	public String getAccountId() {
		return this.accountId;
	}
}
