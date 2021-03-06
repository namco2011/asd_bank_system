package application.ccard;

import application.framework.*;

import java.time.LocalDate;

public class CreditCard extends Account {

    String cardNumber;
    LocalDate expiry_date;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCartNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }

    public CreditCard(String accountNumber, AccountType accountType, AccountClass accountClass) {
        super(accountNumber, accountType, accountClass);
        this.expiry_date=LocalDate.now().plusYears(3);


    }


    public CreditCard(String accountNumber, AccountType accountType, AccountClass accountClass, String cardNumber, String expireDate) {
        super(accountNumber, accountType, accountClass);
       this.cardNumber=cardNumber;
       this.expiry_date  = LocalDate.parse(expireDate);
    }

    public BillEntry monthlyBilling(String acct) {
        double previousBalance= this.getPreviousBalance(acct);
        double totalCharge =Math.abs(this.getTotalCharges(acct));
        double totalCredit =this.getTotalCredits(acct);
        double MI = this.creditCardStrategy.monthlyInterest();
        double MP = this.creditCardStrategy.minimumPayment();
        double newBalance =previousBalance - totalCredit + totalCharge + MI * (previousBalance - totalCredit);
        double totalDue= MP*newBalance;
        this.addInterest();

    //    StringBuilder report =new StringBuilder();
//        report.append("Name= " +this.getCustomer().getName() );
//        report.append("\n Address=" +this.getCustomer().getStreet());
//        report.append( ", "+super.getCustomer().getCity() );
//        report.append(", "+this.getCustomer().getState() );
//        report.append( ", "+this.getCustomer().getZip() );
//        report.append( "\r\n CC number=" +this.getAccountNumber()) ;
//        report.append("\r\n CC type="+ this.getCreditCardType());
//        report.append( "\r\nPrevious balance : $" +previousBalance);
//        report.append( "\n Total charge : $ "+totalCharge );
//        report.append( "\n Total credit : $ "+totalCredit );
//        report.append( "\n New Balance  : $ "+newBalance );
//        report.append( "\n Total Amount Due    : $ "+totalDue);


//        report.append( this.getAccountNumber()) ;
//        report.append( previousBalance);
//        report.append( totalCharge );
//        report.append( totalCredit );
//        report.append( newBalance );
//        report.append( totalDue);
        BillEntry bill = new BillEntry(this.getAccountNumber(),previousBalance,totalCharge,totalCredit,newBalance,totalDue);
        //return report.toString();
        return bill;
    }


//
//    public String monthlyBilling(String acct) {
//        double previousBalance = this.getPreviousBalance(acct);
//        double totalCharge = Math.abs(this.getTotalCharges(acct));
//        double totalCredit = Math.abs( this.getTotalCredits(acct));
//        double MI = this.creditCardStrategy.monthlyInterest();
//        double MP = this.creditCardStrategy.minimumPayment();
//        double newBalance = previousBalance - totalCredit + totalCharge + MI * (previousBalance - totalCredit);
//        double totalDue = MP * newBalance;
//        this.addInterest();
//        StringBuilder report =new StringBuilder();
//        /*
//        report.append("Name= " +this.getCustomer().getName() );
//        report.append("\n Address=" +this.getCustomer().getStreet());
//        report.append( ", "+super.getCustomer().getCity() );
//        report.append(", "+this.getCustomer().getState() );
//        report.append( ", "+this.getCustomer().getZip() );
//        report.append( "\r\n CC number=" +this.getAccountNumber()) ;
//        report.append("\r\n CC type="+ this.getCreditCardType());
//        report.append( "\r\nPrevious balance : $" +previousBalance);
//        report.append( "\n Total charge : $ "+totalCharge );
//        report.append( "\n Total credit : $ "+totalCredit );
//        report.append( "\n New Balance  : $ "+newBalance );
//        report.append( "\n Total Amount Due    : $ "+totalDue);
//        report.append( "\r\n");
//        report.append( "\r\n");
//        System.out.println(report);
//        */
//        List<String> list = new ArrayList<>();
//        report.append( this.getAccountNumber()+"|||") ;
//        report.append("\r\n CC type="+ this.getCreditCardType());
//        report.append( "\r\nPrevious balance : $" +previousBalance);
//        report.append( "\n Total charge : $ "+totalCharge );
//        report.append( "\n Total credit : $ "+totalCredit );
//        report.append( "\n New Balance  : $ "+newBalance );
//        report.append( "\n Total Amount Due    : $ "+totalDue);
//
//        return report.toString();
//    }

    public double getTotalCredits() {
        double totalCredits = 0;
        for (AccountEntry entry : AccountEntryDB.accountEntries) {
            if (entry.getDescription().equals("deposit")) {
                totalCredits += entry.getAmount();
            }
        }
        return totalCredits;
    }

    public double getTotalCredits(String acct) {
        double totalCredits = 0;
        for (AccountEntry entry : AccountEntryDB.accountEntries) {
            if (entry.getDescription().equals("deposit") && entry.getFromAccountNumber().equals(acct)) {
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

    public double getTotalCharges(String acct) {
        double totalCharges = 0;
        for (AccountEntry entry : AccountEntryDB.accountEntries) {
            if (entry.getDescription().equals("charge") && entry.getFromAccountNumber().equals(acct)) {
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


