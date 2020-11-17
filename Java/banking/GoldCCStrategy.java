package banking;

public class GoldCCStrategy implements ICStrategy {


    @Override
    public double interestCalculation(Account account) {
        double balance = account.getBalance();
        double interestAmount= 0.06;

        if(balance<1000){interestAmount = balance*0.01;}
        else if(balance<5000) {interestAmount = balance*0.02;}
        else {interestAmount = balance*0.04;
        }
        return interestAmount;
    }
}
