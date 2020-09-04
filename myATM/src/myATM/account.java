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
	
	public String getSummaryLine() {
		double balance = this.getBalance();
		
		//format summary line
		if(balance >=0 ) {
			return String.format("%s : $%.02f : %s", this.accountId, balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.accountId, balance, this.name);
		}
	}
	
	public double getBalance() {
		
		double balance = 0;
		for (transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}
}
