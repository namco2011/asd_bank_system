package application.framework;

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
        sb.append("Dear "+ ac.getCustomer().getName() );
        sb.append("\n");

        if (entry.getAmount()>0)
        sb.append("There is a deposit " + entry.getAmount() +" to account "+ entry.getFromAccountNumber() +"( "+entry.getDescription()+")");

        if (entry.getAmount()<0)
            sb.append("There is a withdraw "+ entry.getAmount() +" from account "+ entry.getFromAccountNumber()+ entry.getFromAccountNumber() +"( "+entry.getDescription()+")");
         sb.append("\n");
        sb.append("Account balance of  is "+ac.getBalance());
        sb.append("\n");
        sb.append("Thank you");
        sb.append("\n");
        sb.append("ASD Bank");
        try {
            email.SendEMail(ac.customer.getEmailAddress(),sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Email sent");
    }
}
