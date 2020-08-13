/**
	 * Class Name:		BankAccount
	 * Purpose:			This is an abstract superclass of CheckingAccount and SavingsAccount
	 * Coder:			Danielle Miike
	 * Date:			Fev 16, 2020
*/
 
abstract public class BankAccount {
	 	
    private String customerName;
    private String accountType;
    private String month;
    
    //For the no-arg constructor, set the field values to null
    BankAccount(){
        customerName = null;
        accountType = null;
        month = null;
    }

    //For the multi-arg constructor, set the field values to the arguments
    BankAccount(String customerName, String accountType, String month){
        customerName = this.customerName;
        accountType = this.accountType;
        month = this.month;
    }

    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    //build five abstract methods that will be overridden and implemented in the subclasses 
    abstract public String setAccountNumber();
    
    abstract public void deposit(double depositAmount, int day);

    abstract public void withdrawal(double withdrawalAmount, int day);
    
    abstract public void recordTransaction(int day, String transaction, double amount, double balance);
    
    abstract public void monthlyProcess();
    
    //for the toString() method have the method return a String that reads
    public String toString() {
        return( "********************************************"+
                "\nCustomer Name:\t" 	+ this.customerName + 
                "\nAccount Type:\t" 		+ this.accountType + 
                "\nCurrent Month:\t" 	+ this.month +
                "\n*********************************************"
                );
        
    }
}


