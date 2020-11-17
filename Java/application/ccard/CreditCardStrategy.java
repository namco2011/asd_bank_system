package application.ccard;

public interface CreditCardStrategy {
    public double minimumPayment();
    public double monthlyInterest();
}
