package application.banking;

import application.framework.AccountClass;
import application.framework.AccountType;
import application.framework.Account;

import java.util.Date;

public class PersonalAccount extends Account {
    private Date birthDate;


    public PersonalAccount(String accountNumber, AccountType accountType, AccountClass accountClass) {
        super(accountNumber, accountType, accountClass);
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public void changeNotification() {
     Account account;

    }
}