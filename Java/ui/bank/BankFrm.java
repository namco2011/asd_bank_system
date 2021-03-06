package ui.bank;


import application.banking.report.AccountEntryRpt;
import application.banking.report.AccountRpt;
import application.banking.transaction.HistoryCommand;
import application.framework.*;
import ui.JDialog_Deposit;
import ui.JDialog_Withdraw;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A basic JFC based application.
 */
public class BankFrm extends javax.swing.JFrame {
    /****
     * init variables in the object
     ****/
    String accountnr, clientName, street, city, zip, state, clientType, transactionAmount, email, noOfEmployee, dateOfBirth;
    AccountType accountType;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    BankFrm myframe;
    private Object rowdata[];
    AccountService accountService = AccountServiceImpl.getInstance();



    //     protected static HistoryCommand historyCommand = new HistoryCommand();
    public static HistoryCommand historyCommand = new HistoryCommand();
    public BankFrm() {
        myframe = this;

        setTitle("Bank Application.");
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(1000, 600);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 1000, 600);
		/*
		/Add five buttons on the pane
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);
        model.addColumn("AccountNr");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("P/C");
        model.addColumn("Ch/S");
        model.addColumn("Amount");
        rowdata = new Object[8];
        newaccount = false;


        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(50, 92, 600, 400);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        JButton_PerAC.setText("Add personal account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24, 20, 192, 33);
        JButton_CompAC.setText("Add company account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(650, 104, 96, 33);
        JButton_Withdraw.setText("Withdraw");
        JPanel1.add(JButton_Withdraw);
        JButton_Addinterest.setBounds(650, 20, 106, 33);
        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);
        JButton_Withdraw.setBounds(650, 164, 96, 33);

        JButton_AccountRpt.setText("Account Report");
    //    JPanel1.add(JButton_AccountRpt);
        JButton_AccountRpt.setBounds(650, 300, 200, 31);

        JButton_AccountEntryRpt.setText("AccountEntry Report");
        JPanel1.add(JButton_AccountEntryRpt);
        JButton_AccountEntryRpt.setBounds(650, 400, 200, 31);

        JButton_Reversal.setText("Reversal");
        JPanel1.add(JButton_Reversal);
        JButton_Reversal.setBounds(650, 450, 200, 31);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(650, 500, 96, 31);
        // lineBorder1.setRoundedCorners(true);
        // lineBorder1.setLineColor(java.awt.Color.green);
        //$$ lineBorder1.move(24,312);

        JButton_PerAC.setActionCommand("jbutton");
        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_PerAC.addActionListener(lSymAction);
        JButton_CompAC.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
        JButton_Addinterest.addActionListener(lSymAction);
        JButton_AccountRpt.addActionListener(lSymAction);
        JButton_AccountEntryRpt.addActionListener(lSymAction);
        JButton_Reversal.addActionListener(lSymAction);
        loadData();

    }


    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    static public void main(String args[]) {
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            (new BankFrm()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }


    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_PerAC = new javax.swing.JButton();
    javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
    javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();
    javax.swing.JButton JButton_AccountRpt = new javax.swing.JButton();
    javax.swing.JButton JButton_AccountEntryRpt = new javax.swing.JButton();

    JButton JButton_Reversal = new JButton();
    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == BankFrm.this)
                BankFrm_windowClosing(event);
        }
    }

    void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else if (object == JButton_PerAC) {
                try {
                    JButtonPerAC_actionPerformed(event);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (object == JButton_CompAC)
                JButtonCompAC_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
            else if (object == JButton_Addinterest)
                JButtonAddinterest_actionPerformed(event);
            else if (object == JButton_AccountRpt)
                JButton_AccountRpt_actionPerformed(event);
            else if (object == JButton_AccountEntryRpt)
                JButton_AccountEntryRpt_actionPerformed(event);
            else if (object == JButton_Reversal) {
                try {
                    JButton_Reversal_actionPerformed(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) throws ParseException {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

        JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = "P";
            rowdata[4] = accountType;
            rowdata[5] = "0";
            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            //Nam update
            Date birthDay = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
            accountService.createPersonalAccount(accountnr, clientName, accountType, AccountClass.PERSONAL, street, city, state, zip, email, birthDay);
//            for(Account account: AccountDB.accountList){
//				System.out.println(account.getAccountNumber());
//			}
            newaccount = false;
        }
    }

    void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object
		 set the boundaries and
		 show it
		*/

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = "C";
            rowdata[4] = accountType;
            rowdata[5] = "0";
            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            //Nam update
            int noEmployee = Integer.parseInt(noOfEmployee);
            accountService.createCompanyAccount(accountnr, clientName, accountType, AccountClass.PERSONAL, street, city, state, zip, email, noEmployee);
            newaccount = false;
        }

    }

    void JButton_AccountRpt_actionPerformed(java.awt.event.ActionEvent event) {

        AccountRpt billFrm  = new AccountRpt();
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();
    }

    void JButton_Reversal_actionPerformed(java.awt.event.ActionEvent event) throws IOException {
    historyCommand.undo();
    loadData();

    }

    void JButton_AccountEntryRpt_actionPerformed(java.awt.event.ActionEvent event) {

        AccountEntryRpt billFrm  = new AccountEntryRpt();
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();


    }

    void loadData() {
        model.setRowCount(0);
        for (Account account : accountService.getAllAccounts()) {
            if(account.getAccountClass()!=null && !account.getAccountClass().equals(AccountClass.CREDITCARD)){
            //	if (entry.getDate() >= fromdate && entry.getDate() <= toDate && entry.getFromAccountNumber()=="xxc") {
            rowdata[0] = account.getAccountNumber();
            rowdata[1] = account.getCustomer().getName();
            rowdata[2] = account.getCustomer().getCity();
            rowdata[3] = account.getAccountClass()==AccountClass.COMPANY?"C":"P";
            rowdata[4] = account.getAccountType();
            rowdata[5] = account.getBalance();
            model.addRow(rowdata);}
        }
    }

    void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myframe, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
//            long deposit = Long.parseLong(transactionAmount);
//            String samount = (String) model.getValueAt(selection, 5);
//            long currentamount = Long.parseLong(samount);
//            long newamount = currentamount + deposit;
//            model.setValueAt(String.valueOf(newamount), selection, 5);
            model.setValueAt(String.valueOf((accountService.getAccount(accnr).getBalance())), selection, 5);
        }


    }

    void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= -1) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myframe, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount
//            long deposit = Long.parseLong(transactionAmount);
//            String samount = (String) model.getValueAt(selection, 5);
//            long currentamount = Long.parseLong(samount);
//            long newamount = currentamount - deposit;
//            model.setValueAt(String.valueOf(newamount), selection, 5);
//            if (newamount < 0) {
//                JOptionPane.showMessageDialog(JButton_Withdraw, " Account " + accnr + " : balance is negative: $" + String.valueOf(newamount) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
//            }
            model.setValueAt(String.valueOf((accountService.getAccount(accnr).getBalance())), selection, 5);
        }

    }

    void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
        JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);

      for (AccountEntry e : accountService.getAllAccountEntries()) {
            System.out.println("Before Generate transaction: " + e.getFromAccountNumber() + " " + e.getAmount()+" "+ e.getDate());
        }

        for (Account account : accountService.getAllAccounts()) {

            if (account.getAccountClass() != AccountClass.CREDITCARD) {
                accountService.addInterest(account.getAccountNumber());
            }
        }

        for (AccountEntry e : accountService.getAllAccountEntries()) {
            System.out.println("After Generate transaction: " + e.getFromAccountNumber() + " " + e.getAmount()+" "+ e.getDate());
        }
        loadData();

//		  			AccountDB.accountList.stream()
//					.filter(a->!a.getAccountType().equals(AccountType.CREDITCARD))
//					.filter(a->a.getAccountNumber().equals("111"))
//					.forEach(acc->{accountService.addInterest(acc.getAccountNumber());});
//Nam update
        for (AccountEntry accountEntry : accountService.getAllAccountEntries()) {
            System.out.println(accountEntry.getFromAccountNumber() + " " + accountEntry.getDescription() + " " + accountEntry.getAmount());

        }

    }


}


