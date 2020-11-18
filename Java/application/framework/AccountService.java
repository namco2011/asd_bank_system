package application.framework;

import application.ccard.CreditCardType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public interface AccountService {
    Account createAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                          String customerStreet, String customerCity, String customerState, String customerZip , String customerEmail );
    Account createPersonalAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                  String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, Date birthdate);
    Account createCompanyAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                 String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, int noOfEmployee);
    Account createCreditCard(String ccNumber, String customerName, AccountType accountType, AccountClass accountClass,
                             String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, LocalDate expireDate, CreditCardType creditCardType);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    Collection<AccountEntry> getAllAccountEntries();
    void deposit (String accountNumber, double amount);
    void addInterest (String accountNumber);
    public String monthlyBilling(String accountNumber);
    void withdraw (String accountNumber, double amount) throws IOException;
//    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) throws IOException;

    }
