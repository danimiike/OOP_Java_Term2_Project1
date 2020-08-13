import java.text.DecimalFormat;


/**
	 * Class Name:		Transaction
	 * Purpose:			This class is just a class whose object can be a data member in another class 
	 * Coder:			Danielle Miike
	 * Date:			Fev 16, 2020
*/
 
public class Transaction {
	private String month;
	private int day;
	private String transaction;
	private double amount;
	private double balance;
	DecimalFormat df = new DecimalFormat("$###,##0.00");
	

	
	
	Transaction(){
		month = null;
		day = 0;
		transaction = null;
		amount = 0;
		balance = 0;
	}

	public String getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public String getTransaction() {
		return transaction;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}
	
	public void set(String month, int day, String transaction, double amount, double balance) {
		this.month = month;
		this.day = day;
		this.transaction = transaction;
		this.amount = amount;
		this.balance = balance;
	}
	
	public String toString() {
		return(this.month + " " + this.getDay() + " \t" + getTransaction() + ": \t" + df.format(this.amount) + "   \tBalance:\t" + df.format(this.balance));
	}

}
