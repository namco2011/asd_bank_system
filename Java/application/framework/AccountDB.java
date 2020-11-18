package application.framework;

import application.ccard.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class AccountDB {
    public static List<Account> accountList = new ArrayList<>();
    static {


        Account account1 = new Account("1111",AccountType.CHECKING,AccountClass.PERSONAL);
        Account account2 = new Account("2222",AccountType.SAVING,AccountClass.PERSONAL);
        Account account3 = new Account("3333",AccountType.CHECKING,AccountClass.COMPANY);
        Account account4 = new Account("4444",AccountType.SAVING,AccountClass.COMPANY);
        Account account5 = new CreditCard("5555",AccountType.CREDITCARD,AccountClass.CREDITCARD);
//
        account1.setCustomer(new  Customer( "Jhon", "hanhduy.nguyen@gmail.com", "101 street", "Fairfield", "Iowa", "52556"));
        account2.setCustomer(new  Customer( "Jhon", "hanhduy.nguyen@gmail.com", "101 street", "Fairfield", "Iowa", "52556"));
        account3.setCustomer(new Customer("Frank", "frank@gmail.com", "102 street", "Fairfield", "Iowa", "52556"));
        account4.setCustomer(new Customer("Frank", "frank@gmail.com", "102 street", "Fairfield", "Iowa", "52556"));
        account5.setCustomer(new Customer("Frank", "frank@gmail.com", "102 street", "Fairfield", "Iowa", "52556"));

        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);
        accountList.add(account5);


    }
}
