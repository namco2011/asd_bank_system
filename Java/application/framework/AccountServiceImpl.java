package application.framework;

import application.ccard.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    List<Customer> customerList = CustomerDB.customerList;


    //MEKU
    //Singleton account service implementation

    private static AccountServiceImpl instance;

    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
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
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
        account.addObserver(new EmailSender());
        //  account.changeNotification();
        return account;
    }

    public Account createPersonalAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                         String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, Date birthdate) {
        Account account = new Account(accountNumber, accountType, AccountClass.PERSONAL);
//        Customer customer = new Customer(accountNumber, customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        Customer customer = null;
        for (Customer customer1 : customerList) {
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
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
        account.addObserver(new EmailSender());
        //   account.changeNotification();
        return account;
    }

    public Account createCompanyAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                        String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, int noOfEmployee) {
        Account account = new Account(accountNumber, accountType, AccountClass.COMPANY);
//        Customer customer = new Customer(accountNumber, customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        Customer customer = null;
        for (Customer customer1 : customerList) {
            if (customer1.getName().equals(customerName)) {
                customer = customer1;
                break;
            }
        }
        if (customer == null) {
            customer = new Customer( customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        }
        account.setCustomer(customer);
        customer.setNoOfEmployee(noOfEmployee);
        accountDAO.saveAccount(account);
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
        account.addObserver(new EmailSender());
        //   account.changeNotification();
        return account;
    }

    public Account createCreditCard(String ccNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                    String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, Date expireDate, CreditCardType creditCardType) {
        Account account = new CreditCard(ccNumber, AccountType.CREDITCARD, AccountClass.CREDITCARD);
        Customer customer = null;
        for (Customer customer1 : customerList) {
            if (customer1.getName().equals(customerName)) {
                customer = customer1;
                break;
            }
        }
        if (customer == null) {
            customer = new Customer( customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        }
        account.setCustomer(customer);
        account.setCreditCardType(creditCardType);
        customer.setExpirationDate(expireDate);
        accountDAO.saveAccount(account);

        account.addObserver(new EmailSender());
//        account.changeNotification();
        switch (creditCardType) {
            case GOLD -> account.setCreditCardStrategy(new GoldCCStrategy());
            case SILVER -> account.setCreditCardStrategy(new SilverCCStrategy());
            case BRONZE -> account.setCreditCardStrategy(new BronzeCCStrategy());
        }
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
        return account;
    }

    public String monthlyBilling(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account.monthlyBilling();
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.addObserver(new EmailSender());
        account.deposit(accountNumber, amount);
        accountDAO.updateAccount(account);
    }

    public void addInterest(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.addObserver(new EmailSender());
        account.addInterest(accountNumber);
        accountDAO.updateAccount(account);
    }

    public Account getAccount(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account;
    }

    public Collection<Account> getAllAccounts() {
        //Revert later
        // return accountDAO.getAccounts();
        return AccountDB.accountList;
    }

    public void withdraw(String accountNumber, double amount) throws IOException {
        Account account = accountDAO.loadAccount(accountNumber);
        account.addObserver(new EmailSender());
        if (account.accountClass != AccountClass.CREDITCARD) {
//            if (account.getBalance() < amount) {
//                SendEmail email = new SendEmail();
//                email.SendEMail(account.getCustomer().getEmailAddress(), " You can't withdraw because account balance " + account.getBalance() + " less than " + amount);
//            } else {
                account.withdraw(accountNumber, amount);
                accountDAO.updateAccount(account);
//            }
        } else {
            account.charge(accountNumber, amount);
            accountDAO.updateAccount(account);
        }
    }


    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) throws IOException {
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        if (fromAccount.getBalance() < amount) {
            SendEmail email = new SendEmail();
            email.SendEMail(fromAccount.getCustomer().getEmailAddress(), " You can't withdraw because account balance " + fromAccount.getBalance() + " less than " + amount);
        } else {
            fromAccount.transferFunds(toAccount, amount, description);
            //  fromAccount.changeNotification();
            accountDAO.updateAccount(fromAccount);
            accountDAO.updateAccount(toAccount);
        }
    }
}
