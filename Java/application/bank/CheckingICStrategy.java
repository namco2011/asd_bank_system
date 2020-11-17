package application.bank;

import application.framework.Account;

public class CheckingICStrategy implements ICStrategy {
    @Override
    public double interestCalculation(Account account) {
        double balance = account.getBalance();
        double interestAmount=0;
        if(balance<1000){interestAmount = balance*0.015;}
        else {interestAmount = balance*0.025;
        }
        return interestAmount;
    }
}
