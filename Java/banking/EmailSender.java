package banking;

import banking.utilities.SendEmail;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class EmailSender implements Observer {
    @Override
    public void update(Observable observable, Object arg) {

        Account ac = (Account) observable;
        AccountEntry entry= (AccountEntry) arg;

        SendEmail email = new SendEmail();
        StringBuilder sb = new StringBuilder();
        sb.append(ac.accountNumber);
        sb.append(ac.accountType);
        sb.append(ac.getBalance());
        try {
            email.SendEMail(ac.customer.getEmailAddress(),sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Email sent");
    }
}
