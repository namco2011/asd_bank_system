package banking;

import java.util.Collection;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;
	
	public AccountServiceImpl(){
		accountDAO = new AccountDAOImpl();
	}

	public Account createAccount(String accountNumber, String customerName,  AccountType accountType,AccountClass accountClass,
								 String customerStreet,String customerCity,String customerState,String customerZip , String customerEmail ) {

		Account account = new Account(accountNumber,accountType,accountClass);
		Customer customer = new Customer(accountNumber,customerName, customerEmail, customerStreet,customerCity, customerState, customerZip);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		account.addObserver(new EmailSender());
		account.changeNotification();
		return account;
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		account.changeNotification();
		accountDAO.updateAccount(account);
	}

	public void addInterest(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.addInterest();
		accountDAO.updateAccount(account);
	}

	public Account getAccount(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		account.changeNotification();
		accountDAO.updateAccount(account);
	}


	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		fromAccount.changeNotification();
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
	}
}
