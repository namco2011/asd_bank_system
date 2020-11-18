package application.framework;

import application.banking.CheckingICStrategy;
import application.banking.SavingICStrategy;
import application.ccard.CreditCardStrategy;
import application.ccard.CreditCardType;
import application.ccard.GoldCCStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

public class Account extends Observable {
    protected Customer customer;
    protected application.banking.ICStrategy ICStrategy;
    protected CreditCardStrategy creditCardStrategy;
    protected AccountClass accountClass;
    protected String accountNumber;
    protected AccountType accountType;
    protected CreditCardType creditCardType;
    protected List<AccountEntry> entryList = new ArrayList<AccountEntry>();

    public Account(String accountNumber, AccountType accountType, AccountClass accountClass) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountClass = accountClass;
        this.ICStrategy = accountType == AccountType.CHECKING ? new CheckingICStrategy() : new SavingICStrategy();
        this.creditCardStrategy = new GoldCCStrategy();
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public Account setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
        return this;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public AccountClass getAccountClass() {
        return accountClass;
    }

//    public void changeNotification() {
//        setChanged();
//        notifyObservers();
//    }


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
        for (AccountEntry entry : AccountEntryDB.accountEntry) {
            if (entry.getFromAccountNumber().equals(accountNumber))
                balance += entry.getAmount();
        }
        return balance;
    }

    public String monthlyBilling() {
        return "go to CreditCard";
    }

    public double getPreviousBalance() {
        double balance = 0;
        LocalDate current = LocalDate.now();
        LocalDate prevMonth = current.minusMonths(1);
        LocalDate startPrevMonth = prevMonth.with(firstDayOfMonth());
//		LocalDate endPrevMonth = prevMonth.with(lastDayOfMonth());
        for (AccountEntry entry : AccountEntryDB.accountEntry) {
            //	LocalDate entryDate = entry.getDate();
            //	LocalDate entryLocalDate = entryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //	if(entryLocalDate.isAfter(startPrevMonth) && entryLocalDate.isBefore(entryLocalDate)){
            if (entry.getDate().isAfter(startPrevMonth) && entry.getDate().isBefore(entry.getDate())) {
                balance += entry.getAmount();
            }
        }
        return balance;
    }

//    public void deposit(double amount) {
//        AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
//        //	entryList.add(entry);
//        AccountEntryDB.accountEntry.add(entry);
//        notifyChanges(entry);
//        for (AccountEntry e : AccountEntryDB.accountEntry) {
//            System.out.println(e.getFromAccountNumber() + " " + e.getAmount());
//        }
//    }

    public void deposit(String accountNumber, double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit", accountNumber, "");
        //	entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);
        notifyChanges(entry);
        for (AccountEntry e : AccountEntryDB.accountEntry) {
            System.out.println("Deposit Transaction: " + e.getFromAccountNumber() + " " + e.getAmount());
        }
    }

    public void addInterest(String accountNumber) {
        AccountEntry entry = new AccountEntry(this.ICStrategy.interestCalculation(this), "interest", accountNumber, "");
        //	entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);
        notifyChanges(entry);
        for (AccountEntry e : AccountEntryDB.accountEntry) {
            System.out.println("Interest Function: " + e.getFromAccountNumber() + " " + e.getAmount());
        }
    }

//    public void withdraw(double amount) {
//        AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
//        //	entryList.add(entry);
//        AccountEntryDB.accountEntry.add(entry);
//
//        notifyChanges(entry);
//        for (AccountEntry e : AccountEntryDB.accountEntry) {
//            System.out.println("Withdraw Function: " + e.getFromAccountNumber() + " " + e.getAmount() + " balance ");
//        }
//    }

    public void withdraw(String accountNumber, double amount) {
        AccountEntry entry = new AccountEntry(-amount, "withdraw", accountNumber, "");
        //	entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);
        notifyChanges(entry);

        for (AccountEntry e : AccountEntryDB.accountEntry) {
            System.out.println(e.getFromAccountNumber() + " " + e.getAmount());
        }
    }

    public void charge(String accountNumber, double amount) {
        AccountEntry entry = new AccountEntry(-amount, "charge", accountNumber, "");
        //	entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);
        notifyChanges(entry);

        for (AccountEntry e : AccountEntryDB.accountEntry) {
            System.out.println(e.getFromAccountNumber() + " " + e.getAmount());
        }
    }

    private void addEntry(AccountEntry entry) {
        entryList.add(entry);
    }

    public void transferFunds(Account toAccount, double amount, String description) {
        AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());
        AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());

        //	entryList.add(fromEntry);
        AccountEntryDB.accountEntry.add(fromEntry);
        notifyChanges(fromEntry);
        //	toAccount.addEntry(toEntry);
        AccountEntryDB.accountEntry.add(toEntry);
        notifyChanges(toEntry);

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
            customer.addAccount(this);
        }
    }

    public Collection<AccountEntry> getEntryList() {
        return entryList;
    }

    public void notifyChanges(AccountEntry entry) {
        setChanged();
        notifyObservers(entry);

    }


}
