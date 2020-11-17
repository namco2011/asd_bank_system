package banking;

import banking.Database.AccountDB;
import banking.Database.CustomerDB;

import java.util.Collection;
import java.util.Date;
public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;

    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
    }

    public Account createAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                 String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail) {

        Account account = new Account(accountNumber, accountType, accountClass);
        Customer customer = new Customer(accountNumber, customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        account.setCustomer(customer);
        accountDAO.saveAccount(account);
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
        account.addObserver(new EmailSender());
        account.changeNotification();
        return account;
    }

    public Account createPersonalAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                         String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, Date birthdate) {
        Account account = new Account(accountNumber, accountType, AccountClass.PERSONAL);
        Customer customer = new Customer(accountNumber, customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        account.setCustomer(customer);
        customer.setBirthday(birthdate);
        accountDAO.saveAccount(account);
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
        account.addObserver(new EmailSender());
        account.changeNotification();
        return account;
    }

    public Account createCompanyAccount(String accountNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                        String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, int noOfEmployee) {
        Account account = new Account(accountNumber, accountType, AccountClass.COMPANY);
        Customer customer = new Customer(accountNumber, customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        account.setCustomer(customer);
        customer.setNoOfEmployee(noOfEmployee);
        accountDAO.saveAccount(account);
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
        account.addObserver(new EmailSender());
        account.changeNotification();
        return account;
    }

    public Account createCreditCard(String ccNumber, String customerName, AccountType accountType, AccountClass accountClass,
                                    String customerStreet, String customerCity, String customerState, String customerZip, String customerEmail, Date expireDate, CreditCardType creditCardType) {
        Account account = new CreditCard(ccNumber, AccountType.CREDITCARD, AccountClass.CREDITCARD);
        Customer customer = new Customer(ccNumber, customerName, customerEmail, customerStreet, customerCity, customerState, customerZip);
        account.setCustomer(customer);
        customer.setExpirationDate(expireDate);
        accountDAO.saveAccount(account);

        account.addObserver(new EmailSender());
        account.changeNotification();
        switch (creditCardType) {
            case GOLD -> account.setCreditCardStrategy(new GoldCCStrategy());
            case SILVER -> account.setCreditCardStrategy(new SilverCCStrategy());
            case BRONZE -> account.setCreditCardStrategy(new BronzeCCStrategy());
        }
        AccountDB.accountList.add(account);
        CustomerDB.customerList.add(customer);
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
