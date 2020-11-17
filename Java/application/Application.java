package application;

import application.framework.*;

import java.io.IOException;

public class Application {
	public static void main(String[] args) throws IOException {
		AccountService accountService = AccountServiceImpl.getInstance();


		//String accountNumber, String customerName,  AccountType accountType,AccountClass accountClass,
		//								 String customerStreet,String customerCity,String customerState,String customerZip , String customerEmail
		// create 2 accounts;
		accountService.createAccount("1263862", "Frank Brown",AccountType.SAVING, AccountClass.COMPANY,
				"110 st", "Fairfield", "Iowa", "52556", "hanhduy.nguyen@gmail.com");
		accountService.createAccount("4253892", "John Doe",AccountType.CHECKING,AccountClass.CREDITCARD,
				"111 st", "Fairfield", "Iowa", "52556", "hanhduy.nguyen@gmail.com");
		// use account 1;
		accountService.deposit("1263862", 100);
//		accountService.deposit("1263862", 529);
		accountService.withdraw("1263862", 200);
		accountService.addInterest("1263862");
//		// use account 2;
		accountService.deposit("4253892", 300);
		accountService.addInterest("4253892");
		accountService.transferFunds("4253892", "1263862", 10000, "payment of invoice 10232");
		// show balances

//		for (Account account : accountService.getAllAccounts()) {
//			Customer customer = account.getCustomer();
//			System.out.println("Statement for Account: " + account.getAccountNumber());
//			System.out.println("Account Holder: " + customer.getName());
//
//			System.out.println("-Date-------------------------"
//					+ "-Description------------------"
//					+ "-Amount-------------");
//
//			for (AccountEntry entry : account.getEntryList()) {
//				System.out.printf("%30s%30s%20.2f\n",
//						entry.getDate().toString(),
//						entry.getDescription(),
//						entry.getAmount());
//			}
//
//			System.out.println("----------------------------------------" + "----------------------------------------");
//			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
//		}
	}

}
