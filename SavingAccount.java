/**
	 * Interface Class Name:	SavingAccount
	 * Purpose:					.
	 * Coder:					Danielle Miike
	 * Date:					Fev 16, 2020
*/

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;


public class SavingAccount extends BankAccount implements InterestPayable{
	
	private static double INT_RATE = 0.03;
	
	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;
	
	private ArrayList <Transaction> record = new ArrayList<Transaction>();
	
	DecimalFormat df = new DecimalFormat("$###,##0.00");
	
	SavingAccount(){
		super.setCustomerName(null);
		super.setAccountType(null);
		this.accountNumber = null;
		this.accountActive = false;
		this.numberWithdrawals = 0;
		this.numberDeposits = 0;
		this.balance = 0.0;
		
	}
	
	SavingAccount(String customerName, String month, double balance){
		super.setCustomerName(customerName);
		super.setMonth(month);
		super.setAccountType("Saving");
		this.balance = balance;
		this.accountNumber = setAccountNumber();
		this.accountActive = isAccountActive();
		
		
		
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
	
	public static double getInterestRate() {
		return INT_RATE;
	}

	public String setAccountNumber() {
		
		return("002-623490-" + (int) (Math.random()*1000000) + "-575");
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
		//analyze if it is the last day of the month
		String monthInput = super.getMonth();
		
		//find the last day of the month
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(monthInput);
		int numberMonth = (accessor.get(ChronoField.MONTH_OF_YEAR)); 
		Calendar c = Calendar.getInstance();  
		c.set(Calendar.MONTH, numberMonth - 1);
		int lastDay = c.getMaximum(Calendar.DAY_OF_MONTH);
		
		double temp = 0;
		boolean newIntRate = false;
					
		//Check if the balance was more or equal to 2000 during all month
		
		if(getBalance() >=2000) {
			//Collections.reverse(record);
			for(int i = 0; i < record.size(); i++) {
				if(record.get(i).getBalance()>=2000) {
					newIntRate = true;
				}
				else {
					newIntRate = false;
					break;
				}
			}
		}
		
		if(newIntRate == true) {
			temp = balance * ((INT_RATE + 0.0075) /12);
			balance += temp;
		}
		else if(getBalance() >= 25) {
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
		calcInterest();
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
				"\nAnnual Interest Rate:\t\t" 		+ getInterestRate() * 100 	 +	"%");
	}
}
