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
//        System.out.println(this.getCustomer().getEmailAddress() + " "+this.getCustomer().getName() + " "+ this.getCustomer().getCity()
//         + this.getCustomer().getStreet());
//        report = String.format("Name= ",this.customer.getName() );
//        report += String.format("\n Address=",this.customer.getStreet() );
//        report += String.format(", ",super.customer.getCity() );
//        report += String.format(", ",this.customer.getState() );
//        report += String.format(", ",this.customer.getZip() );
//        report += String.format("\r\n CC number=",this.getAccountNumber() );
//        report += String.format("\r\n CC type=",this.getCreditCardType());
//        report += String.format("\r\nPrevious balance : ${0}",previousBalance);
//        report += String.format("\n Total charge : ${0}",totalCharge );
//        report += String.format("\n Total credit : ${0}",totalCredit );
//        report += String.format("\n New Balance  : ${0}",newBalance );
//        report += String.format("\n Total Amount Due    : ${0}",totalDue);
//        report += String.format("\r\n");
//        report += String.format("\r\n");

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
        for (AccountEntry entry : AccountEntryDB.accountEntry) {
            if (entry.getDescription().equals("deposit")) {
                totalCredits += entry.getAmount();
            }
        }
        return totalCredits;
    }

    public double getTotalCharges() {
        double totalCharges = 0;
        for (AccountEntry entry : AccountEntryDB.accountEntry) {
            if (entry.getDescription().equals("charge")) {
                totalCharges += entry.getAmount();
            }
        }
        return totalCharges;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "credit", "", "");
        entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);

    }

    public void addInterest() {
        if (this.getBalance() > 0) {
            AccountEntry entry = new AccountEntry(this.getBalance() * this.creditCardStrategy.monthlyInterest(), "cc interest", "", "");
            entryList.add(entry);
            AccountEntryDB.accountEntry.add(entry);
        }
    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(-amount, "charge", "", "");
        entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);
    }

}


