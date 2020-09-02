package myATM;
import java.util.Date;

public class transaction {
	private double amount;
	private Date timestamp;
	private String memo;
	private account inAccount;
	
	public transaction(double amount, account inAccount) {
		
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
	}
	
	public transaction(double amount, String memo, account inAccount) {
		
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = memo;
	}

}
