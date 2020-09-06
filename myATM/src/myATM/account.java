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
	
	public void printTransferHistory() {
		System.out.printf("\nTransaction History for account %s\n", this.accountId);
		for(int i = this.transactions.size()-1; i >= 0; i--) {
			System.out.printf(this.transactions.get(i).getSummaryLine());
		}
		System.out.println();
	}
	
	public void addTransaction(double amount, String memo) {
		transaction newTransaction = new transaction(amount, memo, this);
		this.transactions.add(newTransaction);
	}
}
