package application.banking;

import application.framework.Account;

public interface ICStrategy {
    public double interestCalculation(Account account);
}
