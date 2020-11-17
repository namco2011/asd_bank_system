package application.bank;

import application.framework.Account;

public interface ICStrategy {
    public double interestCalculation(Account account);
}
