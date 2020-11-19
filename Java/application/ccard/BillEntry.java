package application.ccard;

public class BillEntry {
    private String cardNumber;

    double previousBalance;
       double totalCharge ;
    double totalCredit ;
    double newBalance ;
    double totalDue;

    public BillEntry(String cardNumber, double previousBalance, double totalCharge, double totalCredit, double newBalance, double totalDue) {
        this.cardNumber = cardNumber;
        this.previousBalance = previousBalance;
        this.totalCharge = totalCharge;
        this.totalCredit = totalCredit;
        this.newBalance = newBalance;
        this.totalDue = totalDue;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(double previousBalance) {
        this.previousBalance = previousBalance;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }
}
