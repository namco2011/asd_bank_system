package banking;

import banking.Database.AccountEntryDB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

public class Account extends Observable {
	protected Customer customer;
	protected ICStrategy ICStrategy;
	protected CreditCardStrategy creditCardStrategy;

	public AccountType getAccountType() {
		return accountType;
	}

	protected AccountType accountType;

	public AccountClass getAccountClass() {
		return accountClass;
	}

	protected AccountClass accountClass;
	protected String accountNumber;

	protected List<AccountEntry> entryList = new ArrayList<AccountEntry>();

	public Account(String accountNumber, AccountType accountType, AccountClass accountClass) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountClass = accountClass;
		this.ICStrategy = accountType==AccountType.CHECKING?new CheckingICStrategy():new SavingICStrategy();
		this.creditCardStrategy = new GoldCCStrategy();
	}

	public void changeNotification(){
		setChanged();
		notifyObservers();
	}



	public Account setCreditCardStrategy(CreditCardStrategy creditCardStrategy) {
		this.creditCardStrategy = creditCardStrategy;
		return this;
	}


	public CreditCardStrategy getCreditCardStrategy() {
		return creditCardStrategy;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entryList) {
			balance += entry.getAmount();
		}
		return balance;
	}




	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
		entryList.add(entry);
		AccountEntryDB.accountEntry.add(entry);
		notifyChanges(entry);
	}

	public void addInterest() {
		AccountEntry entry = new AccountEntry(this.ICStrategy.interestCalculation(this), "interest", "", "");
		entryList.add(entry);
		AccountEntryDB.accountEntry.add(entry);
		notifyChanges(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
		entryList.add(entry);
		AccountEntryDB.accountEntry.add(entry);
		notifyChanges(entry);
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description) {
		AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		
		entryList.add(fromEntry);
		AccountEntryDB.accountEntry.add(fromEntry);
		notifyChanges(fromEntry);
		toAccount.addEntry(toEntry);
		AccountEntryDB.accountEntry.add(toEntry);
		notifyChanges(toEntry);

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public void notifyChanges(AccountEntry entry){
		setChanged();
		notifyObservers(entry);

	}


}
