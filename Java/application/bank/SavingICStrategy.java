package application.bank;

import application.framework.Account;

public class SavingICStrategy implements ICStrategy {
    @Override
    public double interestCalculation(Account account) {
        double balance = account.getBalance();
        double interestAmount=0;
        if(balance<1000){interestAmount = balance*0.01;}
        else if(balance<5000) {interestAmount = balance*0.02;}
        else {interestAmount = balance*0.04;
        }
        return interestAmount;
    }
}
