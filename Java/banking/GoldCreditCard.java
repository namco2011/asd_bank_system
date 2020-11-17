package banking;

public class GoldCreditCard extends CreditCard{

    public GoldCreditCard(String accountNumber, AccountType accountType, AccountClass accountClass) {
        super(accountNumber, accountType, accountClass);
    }


    @Override
    public void calculateInterest() {

    }

    @Override
    public void charge() {

    }

    @Override
    public void payment() {

    }
}
