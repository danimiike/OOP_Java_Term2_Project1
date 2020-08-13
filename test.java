

public class test {
	
	public static void main(String[] args)
	{
		BankAccount a = new PersonalChequingAccount("Amanda Joplin", "March", 2345.00);
		System.out.println(a.toString());
		a.deposit(25.98, 5);
		a.withdrawal(1300, 6);
		a.withdrawal(1700, 10);
		a.deposit(1050, 11);
		a.withdrawal(1700, 11);
		a.deposit(25.98, 25);
		a.withdrawal(400.00, 26);
		a.withdrawal(27.00, 28);
		a.withdrawal(7.50, 28);
		
		a.monthlyProcess();
		System.out.println(a.toString());
	/*
		BankAccount b = new SavingAccount("Elves Presley", "March", 6100);
		System.out.println(b.toString());
		
		b.deposit(500, 3);
		b.withdrawal(1000, 6);
		b.deposit(250, 15);
		b.withdrawal(3000, 21);
		b.withdrawal(825, 28);
		b.deposit(250, 29);

		b.monthlyProcess();*/
	}
	
}