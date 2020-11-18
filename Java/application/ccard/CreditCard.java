package application.ccard;

import application.framework.Account;
import application.framework.AccountClass;
import application.framework.AccountEntry;
import application.framework.AccountType;
import application.framework.AccountEntryDB;

public class CreditCard extends Account {

    public CreditCard(String accountNumber, AccountType accountType, AccountClass accountClass) {
        super(accountNumber, accountType, accountClass);
    }

    public String monthlyBilling() {
        double previousBalance = this.getPreviousBalance();
        double totalCharge = this.getTotalCharges();
        double totalCredit = this.getTotalCredits();
        double MI = this.creditCardStrategy.monthlyInterest();
        double MP = this.creditCardStrategy.minimumPayment();
        double newBalance = previousBalance - totalCredit + totalCharge + MI * (previousBalance - totalCredit);
        double totalDue = MP * newBalance;
        this.addInterest();
        StringBuilder report =new StringBuilder();
        report.append("Name= " +this.getCustomer().getName() );
        report.append("\n Address=" +this.getCustomer().getStreet());
        report.append( ", "+super.getCustomer().getCity() );
        report.append(", "+this.getCustomer().getState() );
        report.append( ", "+this.getCustomer().getZip() );
        report.append( "\r\n CC number=" +this.getAccountNumber()) ;
        report.append("\r\n CC type="+ this.getCreditCardType());
        report.append( "\r\nPrevious balance : $" +previousBalance);
        report.append( "\n Total charge : $ "+totalCharge );
        report.append( "\n Total credit : $ "+totalCredit );
        report.append( "\n New Balance  : $ "+newBalance );
        report.append( "\n Total Amount Due    : $ "+totalDue);
        report.append( "\r\n");
        report.append( "\r\n");
        System.out.println(report);
        return report.toString();
    }

    public double getTotalCredits() {
        double totalCredits = 0;
        for (AccountEntry entry : AccountEntryDB.accountEntries) {
            if (entry.getDescription().equals("deposit")) {
                totalCredits += entry.getAmount();
            }
        }
        return totalCredits;
    }

    public double getTotalCharges() {
        double totalCharges = 0;
        for (AccountEntry entry : AccountEntryDB.accountEntries) {
            if (entry.getDescription().equals("charge")) {
                totalCharges += entry.getAmount();
            }
        }
        return totalCharges;
    }

//    public void deposit(double amount) {
//        AccountEntry entry = new AccountEntry(amount, "credit", "", "");
//        entryList.add(entry);
//        AccountEntryDB.accountEntry.add(entry);
//    }

    public AccountEntry addInterest() {
        AccountEntry entry = null;
        if (this.getBalance() > 0) {
             entry = new AccountEntry(this.getBalance() * this.creditCardStrategy.monthlyInterest(), "cc interest", "", "");
//            entryList.add(entry);
//            AccountEntryDB.accountEntries.add(entry);
        }
        return entry;
    }

//    public void withdraw(double amount) {
//        AccountEntry entry = new AccountEntry(-amount, "charge", "", "");
//        entryList.add(entry);
//        AccountEntryDB.accountEntry.add(entry);
//    }

}


