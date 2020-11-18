package application.banking.transaction;

import application.framework.AccountService;

import java.io.IOException;

public class Transfer  implements  ICommand{
    private AccountService accountService;
    private String fromAccount;
    private String toAccount;
    private long amount;

    public Transfer(AccountService accountService, String fromAccount, String toAccount, long amount) {
        this.accountService = accountService;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void execute() throws IOException {
//        accountService.transferFunds(fromAccount,toAccount,amount,"Tranfer");
    }

    @Override
    public void unexecute() throws IOException {
//        accountService.transferFunds(toAccount,fromAccount,amount,"Reserval transfer");;
    }
}
