package banking;

import banking.Database.AccountEntryDB;

public class CreditCard extends Account{

    public CreditCard(String accountNumber, AccountType accountType, AccountClass accountClass) {
        super(accountNumber, accountType, accountClass);
    }

    public String monthlyBilling(){
        double previousBalance = this.getBalance();
        double totalCharge = this.getTotalCharges();
        double totalCredit = this.getTotalCredits();
        double MI = this.creditCardStrategy.monthlyInterest();
        double MP = this.creditCardStrategy.minimumPayment();
        double newBalance = previousBalance - totalCredit + totalCharge + MI*(previousBalance - totalCredit);
        double totalDue = MP*newBalance;
        return String.format("Name nhu yeu cau repot");
    }

    public double getTotalCredits(){
        double totalCredits = 0;
        for (AccountEntry entry : entryList) {
            if(entry.getDescription().equals("deposit")){
                totalCredits += entry.getAmount();}
        }
        return totalCredits;
    }

    public double getTotalCharges(){
        double totalCharges = 0;
        for (AccountEntry entry : entryList) {
            if(entry.getDescription().equals("withdrawn")){
                totalCharges += entry.getAmount();}
        }
        return totalCharges;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "credit", "", "");
        entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);

    }

    public void addInterest() {
        AccountEntry entry = new AccountEntry(this.getBalance()*this.creditCardStrategy.monthlyInterest(), "cc interest", "", "");
        entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);
    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(-amount, "charge", "", "");
        entryList.add(entry);
        AccountEntryDB.accountEntry.add(entry);
    }

}


