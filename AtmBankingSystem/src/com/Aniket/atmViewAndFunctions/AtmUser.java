package com.Aniket.atmViewAndFunctions;

//import java.awt.List;
import java.sql.Time;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmUser{
	
	private String userName;
	private static byte ATTEMPTS_ASSIGNED = 5;
	private Scanner Input;
	private ATM atm;
	
	public AtmUser() {
		this.Input = new Scanner(System.in);
		this.atm = new ATM();
	}	
	protected void getUserDetails() {
		final String name;
		boolean accessAtmOrNot = true;
		System.out.println("Please Enter your first name :");
		try {
			name = Input.next(); 
			System.out.println("Do you want to use ATM functionalities..?? Enter 'true' or 'false' only");
			accessAtmOrNot = Input.nextBoolean();
		} catch (InputMismatchException inputEx) {
			System.out.println("ERROR : Details entered incorrectly..!!");
			System.exit(-1);
		}
		if(accessAtmOrNot) {
			System.out.println("Enter :");
			System.out.println("1 -> Balance Inquiry");
			System.out.println("2 -> Deposit");
			System.out.println("3 -> Withdrawal");
			System.out.println("4 -> Checking Bank Statements");
			byte decision = 0;
			
			try {
				decision = Input.nextByte();	
			} catch (InputMismatchException ex) {
				System.out.println("ERROR : Decision entered incorrectly..!!");
				System.exit(-1);
			}
			boolean userChance = true;
			
			byte Attempts = 1;
			while(userChance == true)	{
				switch (decision) {
				case 1:
					System.out.println("your balance : "+this.atm.balanceInquiry());
					break;
				case 2:
					this.atm.deposit();
					break;
				case 3:
					this.atm.withDrawal();
					break;
				case 4:
					this.atm.bankStatements();	
					break;
				default:
					Attempts++;
					userChance = true;
					if(Attempts>ATTEMPTS_ASSIGNED) {	//ATTEMPTS_ASSIGNED = 5;
						System.out.println("You have made more then 5 wrong Attempts, so, Better luck next time..!!");
						System.exit(-1);
					}
					System.out.println("Enter your decision again :");
					try {
						decision = Input.nextByte();	
						//input.close();
					} catch (InputMismatchException ex) {
						System.out.println("ERROR : Decision entered incorrectly..!!");
						System.exit(-1);
					}					
				}//switch ends
				System.out.println("Do you want to continue with functionalities..?? if yes enter true");
				try {
					userChance = Input.nextBoolean();	
					//input.close();
				} catch (InputMismatchException ex) {
					System.out.println("ERROR : Decision entered incorrectly..!!");
					System.exit(-1);
				}	
				if(userChance == true) {
					userChance = true;
					System.out.println("Enter what functionality (1, 2, 3, 4)");
					try {
						decision = Input.nextByte();	
						//input.close();
					} catch (InputMismatchException ex) {
						System.out.println("ERROR : Decision entered incorrectly..!!");
						System.exit(-1);
					}	
				}else {
					userChance = false;
				}
			}//while
		}//if
	}// functions ends

	final class ATM {
		
		private long amount;
		private Date lastDepositDate;
		private Date lastWithDrawalDate;
	
		private Time lastDepositTime;
		private Time lastWithDrawalTime;
	
		public long balanceInquiry() {	// Balance details
			return this.amount;
		}
		public void deposit() {		//to deposit
			System.out.println("Enter amount to be deposited");
			final long userDepositAmount;
			try {
				userDepositAmount = Input.nextLong();		//only 'Long' value can be entered
				this.amount += userDepositAmount;	
				System.out.println("Deposited amount : "+userDepositAmount);
				this.lastDepositDate = new Date();
				// this.lastDepositTime = new Time(23, 59, 59);
			  
			} catch (InputMismatchException e) {
				System.out.println("ERROR : Deposit amount entered incorrectly..!!");
				System.exit(-1);
			}
		}
		public void withDrawal() {
			System.out.println("Enter amount to be withdrawled :");
			final long userWithdrawlAmount;
			final long depshort;
			try {

				userWithdrawlAmount = Input.nextLong();		//only 'Long' value can be entered
				depshort = this.amount - userWithdrawlAmount;
				if(depshort<1) {
					throw new  WitdrawlException(this.amount - userWithdrawlAmount);         
				}else {
					this.amount -= userWithdrawlAmount;
					this.lastWithDrawalDate = new Date();
				}	
			} catch (InputMismatchException | WitdrawlException ex) { 		//catching multiple exceptions
				if(ex instanceof WitdrawlException) {
					System.out.println(ex.getMessage());
				}else {
					System.out.println("ERROR : Withdrawl amount entered in correctly..!!");			
				}
				//System.exit(-1);
			}
		}
		public void bankStatements() {
			System.out.println("last deposited date : "+this.lastDepositDate);
			System.out.println("last withdrawled date : "+this.lastWithDrawalDate);

		}	
	}//end of ATM class

}