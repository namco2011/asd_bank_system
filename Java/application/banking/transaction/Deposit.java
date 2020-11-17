package application.banking.transaction;

import application.framework.AccountService;

import java.io.IOException;

public class Deposit implements  ICommand{
    AccountService accountService;
    private String accontNumber;
    private long amount;

    public Deposit(AccountService accountService, String accontNumber,long amount) {
        this.accountService = accountService;
        this.accontNumber = accontNumber;
        this.amount = amount;
    }

    @Override
    public void execute() {
        accountService.deposit(accontNumber,amount);
    }

    @Override
    public void unexecute() throws IOException {
        accountService.withdraw(accontNumber,amount);
    }
}
