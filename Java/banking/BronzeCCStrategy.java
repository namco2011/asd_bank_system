package banking;

public class BronzeCCStrategy implements CreditCardStrategy{
    @Override
    public double minimumPayment() {
        return 0.14;
    }

    @Override
    public double monthlyInterest() {
        return 0.1;
    }
}
