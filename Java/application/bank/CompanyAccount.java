package application.bank;

import application.framework.AccountClass;
import application.framework.AccountType;
import application.framework.Account;

public class CompanyAccount extends Account {



    private int numOfEmployee;


    public CompanyAccount(String accountNumber, AccountType accountType, AccountClass accountClass, int numOfEmployee ) {
        super(accountNumber, accountType, accountClass);
        this.numOfEmployee = numOfEmployee;

    }


    public int getNumOfEmployee() {
        return numOfEmployee;
    }

    @Override
    public void changeNotification() {
        setChanged();
        notifyObservers();
    }
}
