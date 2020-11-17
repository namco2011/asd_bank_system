package banking;

public class SilverCCStrategy implements CreditCardStrategy{
    @Override
    public double minimumPayment() {
        return 0.12;
    }

    @Override
    public double monthlyInterest() {
        return 0.08;
    }
}
