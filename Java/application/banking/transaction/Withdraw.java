package application.banking.transaction;

import application.framework.AccountService;

import java.io.IOException;

public class Withdraw implements ICommand{
    private AccountService accountService;
    private String accountNumber;
    private long amount;

    public Withdraw(AccountService accountService, String accountNumber, long amount) {
        this.accountService = accountService;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public void execute() throws IOException {
        accountService.withdraw(accountNumber,amount);
    }

    @Override
    public void unexecute() throws IOException {
        accountService.deposit(accountNumber,amount);
    }
}
