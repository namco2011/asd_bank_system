package banking.Database;

import banking.Account;
import banking.AccountClass;
import banking.AccountType;

import java.util.ArrayList;
import java.util.List;

public class AccountDB {
    public static List<Account> accountList = new ArrayList<>();
    static {

        Account account1 = new Account("11111111",AccountType.CHECKING,AccountClass.PERSONAL);
        Account account2 = new Account("22222222",AccountType.SAVING,AccountClass.PERSONAL);
        Account account3 = new Account("33333333",AccountType.CHECKING,AccountClass.COMPANY);
        Account account4 = new Account("44444444",AccountType.SAVING,AccountClass.COMPANY);

        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);

    }
}
