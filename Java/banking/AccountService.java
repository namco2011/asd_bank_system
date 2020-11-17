package banking;

import java.util.Collection;

public interface AccountService {
    Account createAccount(String accountNumber, String customerName,  AccountType accountType,AccountClass accountClass,
                          String customerStreet,String customerCity,String customerState,String customerZip , String customerEmail );
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void addInterest (String accountNumber);
    void withdraw (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
}
