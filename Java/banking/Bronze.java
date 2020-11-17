package banking;

public class Bronze implements CreditCardStrategy{
    @Override
    public double minimumPayment() {
        return 0.1;
    }

    @Override
    public double monthlyInterest() {
        return 0.06;
    }
}
