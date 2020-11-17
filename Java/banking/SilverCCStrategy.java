package banking;

public class SilverCCStrategy implements ICStrategy  {


    @Override
    public double interestCalculation(Account account) {

        double balance = account.getBalance();
        return balance*0.08;
    }
}
