/**
	 * Class Name:		PersonalChequingAccount
	 * Purpose:			 
	 * Coder:			Danielle Miike
	 * Date:			Fev 16, 2020
*/


import java.text.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class PersonalChequingAccount extends BankAccount implements InterestPayable {
	
	private static double INT_RATE = 0.025;
	private static double SERVICE_FEE = 0.85;
	
	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;
	private ArrayList <Transaction> record = new ArrayList<Transaction>();

	DecimalFormat df = new DecimalFormat("$###,##0.00");

	PersonalChequingAccount(){
		
		super.setCustomerName(null);
		super.setAccountType(null);
		this.accountNumber = null;
		this.numberWithdrawals = 0;
		this.numberDeposits = 0;
		this.balance = 0.0;
			
	}
	
	PersonalChequingAccount(String customerName, String month, double balance){

		this.balance = balance;
		super.setAccountType("PersonalChequing");
		super.setCustomerName(customerName);
		super.setMonth(month);
		isAccountActive();
	}
	
	
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public int getNumberWithdrawals() {
		return numberWithdrawals;
	}

	public int getNumberDeposits() {
		return numberDeposits;
	}

	public double getBalance() {
		return balance;
	}

	public boolean isAccountActive() {
		if (this.balance < 25.00) {
			this.accountActive = false;
		}
		else {
			this.accountActive = true;
			}
		return this.accountActive;
	}
	
	public double getInterestRate() {
		return INT_RATE;
	}
	
	public double getServiceFee() {
		return SERVICE_FEE;
	}

	public String setAccountNumber() {
	
		return("002-623490-" + (int) (Math.random()*1000000) + "-550");
		
	}
	
	public void deposit(double depositAmount, int day) {
		this.balance += depositAmount;
		this.numberDeposits +=1;
		isAccountActive();
		recordTransaction(day,"Deposit", depositAmount, balance);
		
	}
	
	public void withdrawal(double withdrawalAmount, int day) {
		String transactionMessage = " ";
		
		double temp = this.balance - withdrawalAmount;
		
		if(temp > 0 && isAccountActive()==true) {
			transactionMessage = "Withdrawal";
			balance -= withdrawalAmount;
			numberWithdrawals +=1;	
		}
		else if(temp < 0) {
			transactionMessage = "Transaction cancelled. Insufficient funds";
		}
		else if(isAccountActive()==false) {
			transactionMessage = "Transaction cancelled. Account is inactive";
		}
		isAccountActive();
		recordTransaction(day,transactionMessage, withdrawalAmount, balance);
	}
	
	public void calcInterest() {
		//get the month
		String monthInput = super.getMonth();
		
		//find the last day of the month
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(monthInput);
		int numberMonth = (accessor.get(ChronoField.MONTH_OF_YEAR)); 
		Calendar c = Calendar.getInstance();  
		c.set(Calendar.MONTH, numberMonth - 1);
		int lastDay = c.getMaximum(Calendar.DAY_OF_MONTH);
		
		double temp = 0;
		if(getBalance() >= 1000) {
			temp = balance * (INT_RATE /12);
			balance += temp;
		}
			recordTransaction(lastDay,"Interest Rate", temp, balance);	
	}
	
	public void recordTransaction(int day, String transaction, double amount, double balance) {
		Transaction trans = new Transaction();
		trans.set(super.getMonth(), day, transaction, amount, balance);
		record.add(trans);
	
	}
	
	public void printTransaction() {
		System.out.println("*****************************************************************************");
		System.out.println(" **** \t\t"+"Transaction Record for the Month of " + super.getMonth() + "\t\t****");
		System.out.println("*****************************************************************************");
		
		 for(int i = 0; i < record.size(); i++){
	            System.out.println(record.get(i) );
	        }
	} 
	
	public void monthlyProcess() {
		String monthInput = super.getMonth();
		
		//find the last day of the month
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(monthInput);
		int numberMonth = (accessor.get(ChronoField.MONTH_OF_YEAR)); 
		Calendar c = Calendar.getInstance();  
		c.set(Calendar.MONTH, numberMonth - 1);
		int lastDay = c.getMaximum(Calendar.DAY_OF_MONTH);
		
		double amountFee;
		
		if(this.numberWithdrawals > 4) {
			amountFee = numberWithdrawals * SERVICE_FEE ;
			balance += amountFee;
		}
		else {
			amountFee = 0;
		}
		
		calcInterest();
		recordTransaction(lastDay, "Service Fee", amountFee, balance);
		printTransaction();
		isAccountActive();
	}
	
	public String toString() {
		
		return(super.toString() +
				"\nAccount Number:\t\t\t" 			+ setAccountNumber() +
				"\nNumber of Withdrawals:\t\t" 		+ getNumberWithdrawals() + 
				"\nNumber of Deposits:\t\t" 		+ getNumberDeposits() + 
				"\nBalance:\t\t\t" 					+ df.format(getBalance()) + 
				"\nAccount Active:\t\t\t" 			+ isAccountActive() + 
				"\nAnnual Interest Rate:\t\t" 		+ getInterestRate() * 100 	 +	"%"+
				"\nMonthly Service Fee Rate:\t"		+ df.format(getServiceFee()) +	" (applied to no. of withdrawals if withdrawals are over 4)");
	}

}
