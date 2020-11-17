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

        String report;
        report = String.format("Previous balance : ${0}",previousBalance );
        report += String.format("\n Total charge : ${0}",totalCharge );
        report += String.format("\n Total credit : ${0}",totalCredit );
        report += String.format("\n New Balance  : ${0}",newBalance );
        report += String.format("\n Total Due    : ${0}",totalDue);
        System.out.println(report);
        return report;
    }


    public double getTotalCredits() {
        double totalCredits = 0;
        for (AccountEntry entry : entryList) {
            if (entry.getDescription().equals("deposit")) {
                totalCredits += entry.getAmount();
            }
        }
        return totalCredits;
    }

    public double getTotalCharges() {
        double totalCharges = 0;
        for (AccountEntry entry : entryList) {
            if (entry.getDescription().equals("withdrawn")) {
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


