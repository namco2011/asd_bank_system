package banking;

public class GoldCCStrategy implements ICStrategy {


    @Override
    public double interestCalculation(Account account) {
        double balance = account.getBalance();
        return balance*0.06;
    }
}
