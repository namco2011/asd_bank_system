package application.framework;

import application.ccard.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    private AccountEntryDAO accountEntryDAO;
    private CustomerDAO customerDAO;

    //Singleton account service implementation
    private static AccountServiceImpl instance;

    private AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
        accountEntryDAO = new AccountEntryDAOImpl();
        customerDAO = new CustomerDAOImpl();
    }

    public static AccountServiceImpl getInstance() {
        if (instance == null) {
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    public Account createAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                 String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail) {

        Account account = new Account(accountNumber, accountType, accountClass);
        Customer customer = new Customer(customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        account.setCustomer(customer);
        accountDAO.saveAccount(account);
        customerDAO.saveCustomer(customer);
        account.addObserver(new EmailSender());
        return account;
    }

    public Account createPersonalAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                         String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, Date birthdate) {
        Account account = new Account(accountNumber, accountType, AccountClass.PERSONAL);
        Customer customer = null;
        for (Customer customer1 : customerDAO.getCustomerList()) {
            if (customer1.getName().equals(customerName)) {
                customer = customer1;
                break;
            }
        }
        if (customer == null) {
            customer = new Customer(customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        }
        account.setCustomer(customer);
        customer.setBirthday(birthdate);
        accountDAO.saveAccount(account);
        customerDAO.saveCustomer(customer);
        account.addObserver(new EmailSender());
        return account;
    }

    public Account createCompanyAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                        String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, int noOfEmployee) {
        Account account = new Account(accountNumber, accountType, AccountClass.COMPANY);
        Customer customer = null;
        for (Customer customer1 : customerDAO.getCustomerList()) {
            if (customer1.getName().equals(customerName)) {
                customer = customer1;
                break;
            }
        }
        if (customer == null) {
            customer = new Customer(customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        }
        account.setCustomer(customer);
        customer.setNoOfEmployee(noOfEmployee);
        accountDAO.saveAccount(account);
        customerDAO.saveCustomer(customer);
        account.addObserver(new EmailSender());
        return account;
    }

    public Account createCreditCard(String ccNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                    String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, LocalDate expireDate, CreditCardType creditCardType) {
        Account account = new CreditCard(ccNumber, AccountType.CREDITCARD, AccountClass.CREDITCARD);
        Customer customer = null;
        for (Customer customer1 : customerDAO.getCustomerList()) {
            if (customer1.getName().equals(customerName)) {
                customer = customer1;
                break;
            }
        }
        if (customer == null) {
            customer = new Customer(customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        }
        account.setCustomer(customer);
        account.setCreditCardType(creditCardType);
        customer.setExpirationDate(expireDate);
        account.addObserver(new EmailSender());
        switch (creditCardType) {
            case GOLD -> account.setCreditCardStrategy(new GoldCCStrategy());
            case SILVER -> account.setCreditCardStrategy(new SilverCCStrategy());
            case BRONZE -> account.setCreditCardStrategy(new BronzeCCStrategy());
        }
        accountDAO.saveAccount(account);
        customerDAO.saveCustomer(customer);
        return account;
    }


    public BillEntry monthlyBilling(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account.monthlyBilling(accountNumber);
    }

    @Override
    public String monthlyBilling() {
        return null;
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.addObserver(new EmailSender());
        accountEntryDAO.saveAccountEntry(account.deposit(accountNumber, amount));
        accountDAO.updateAccount(account);
    }

    public void addInterest(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.addObserver(new EmailSender());
//        for (AccountEntry e : AccountEntryDB.accountEntries) {
//            System.out.println("Interest Function in AccountService Impl, default value: " + e.getFromAccountNumber() + " " + e.getAmount());
//        }
        accountEntryDAO.saveAccountEntry(account.addInterest(accountNumber));
        accountDAO.updateAccount(account);
    }

    public Account getAccount(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account;
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public Collection<AccountEntry> getAllAccountEntries() {
        return accountEntryDAO.getAccountEntries();
    }

    public void withdraw(String accountNumber, double amount) throws IOException {
        Account account = accountDAO.loadAccount(accountNumber);
    //    account.addObserver(new EmailSender());
        if (account.accountClass != AccountClass.CREDITCARD) {
            accountEntryDAO.saveAccountEntry(account.withdraw(accountNumber, amount));
            accountDAO.updateAccount(account);
        } else {
            accountEntryDAO.saveAccountEntry(account.charge(accountNumber, amount));
            accountDAO.updateAccount(account);
        }
    }

//
//
//    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) throws IOException {
//        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
//        Account toAccount = accountDAO.loadAccount(toAccountNumber);
//        if (fromAccount.getBalance() < amount) {
//            SendEmail email = new SendEmail();
//            email.SendEMail(fromAccount.getCustomer().getEmailAddress(), " You can't withdraw because account balance " + fromAccount.getBalance() + " less than " + amount);
//        } else {
//            fromAccount.transferFunds(toAccount, amount, description);
//            //  fromAccount.changeNotification();
//            accountDAO.updateAccount(fromAccount);
//            accountDAO.updateAccount(toAccount);
//        }
//    }
}
