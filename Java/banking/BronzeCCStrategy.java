package banking;

public class BronzeCCStrategy  implements ICStrategy {
    @Override
    public double interestCalculation(Account account) {
        double balance = account.getBalance();
        double interestAmount = 0.1;

        if(balance<1000){interestAmount = balance*0.01;}
        else if(balance<5000) {interestAmount = balance*0.02;}
        else {interestAmount = balance*0.04;
        }
        return interestAmount;
    }
}
