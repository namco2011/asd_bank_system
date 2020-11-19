package application.banking.report;



import application.framework.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * A basic JFC based application.
 */
public class AccountRpt extends javax.swing.JFrame {
    /****
     * init variables in the object
     ****/
    String accountnr, clientName, street, city, zip, state, clientType, transactionAmount, email, noOfEmployee, dateOfBirth;
    AccountType accountType;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    AccountRpt myframe;
    private Object rowdata[];


    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_Report = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();
    javax.swing.JRadioButton JRadio_Account= new JRadioButton();
    javax.swing.JRadioButton JRadio_CreditCard= new JRadioButton();
    JLabel lblfromdate = new JLabel();
    JLabel lbltodate = new JLabel();

    JTextField fromdatestr= new JTextField();
    JTextField todatestr= new JTextField();
    ButtonGroup buttonGroup  = new ButtonGroup();

    AccountService accountService =  AccountServiceImpl.getInstance();

    public AccountRpt() {
        myframe = this;

        setTitle("Account Monthly Report.");
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(800, 600);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 800, 600);
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
        JScrollPane1.setBounds(50, 92, 600, 350);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 600, 0);
//        rowdata = new Object[8];

        JButton_Report.setText("Generate Report");
        JPanel1.add(JButton_Report);
        JButton_Report.setBounds(24, 20, 150, 33);

        buttonGroup.add(JRadio_Account);
        buttonGroup.add(JRadio_CreditCard);
        JRadio_Account.setText("Account");
        JPanel1.add(JRadio_Account);
        JRadio_Account.setSelected(true);
        JRadio_Account.setBounds(10,50,150,30);

        JRadio_CreditCard.setText("Credit Card");
        JPanel1.add(JRadio_CreditCard);
        JRadio_CreditCard.setSelected(false);
        JRadio_CreditCard.setBounds(100,50,150,30);
//
//        fromdatestr.setText("2020-01-10");
//        JPanel1.add(fromdatestr);
//        fromdatestr.setBounds(200,50,100,30);
//
//        todatestr.setText("2020-12-12");
//        JPanel1.add(todatestr);
//        todatestr.setBounds(340,50,100,30);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(700, 500, 96, 31);

//        lblfromdate.setText("From");
//        lbltodate.setBounds(260,50,40,30);
//        JPanel1.add(lblfromdate);
//
//        lbltodate.setText("To");
//        lbltodate.setBounds(280,50,40,30);
//        JPanel1.add(lbltodate);

        // lineBorder1.setRoundedCorners(true);
        // lineBorder1.setLineColor(java.awt.Color.green);
        //$$ lineBorder1.move(24,312);

        JButton_Report.setActionCommand("jbutton");
        AccountRpt.SymWindow aSymWindow = new AccountRpt.SymWindow();
        this.addWindowListener(aSymWindow);
        AccountRpt.SymAction lSymAction = new AccountRpt.SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_Report.addActionListener(lSymAction);

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
            (new AccountRpt()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }



    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
       //     System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == AccountRpt.this)
                AccountRpt_windowClosing(event);
        }
    }

    void AccountRpt_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        AccountRpt_windowClosing_Interaction1(event);
    }

    void AccountRpt_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
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
            else if (object == JButton_Report)
                JButton_Report_actionPerformed(event);


        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
       this.exitApplication();
    }



    void loadData() {
        model.setRowCount(0);

             for (Account account : accountService.getAllAccounts()) {

                if (JRadio_Account.isSelected()) {
                    if (account.getAccountClass() != null && !account.getAccountClass().equals(AccountClass.CREDITCARD)

                    ) {
                        //	if (entry.getDate() >= fromdate && entry.getDate() <= toDate && entry.getFromAccountNumber()=="xxc") {
                        rowdata[0] = account.getAccountNumber();
                        rowdata[1] = account.getCustomer().getName();
                        rowdata[2] = account.getCustomer().getCity();
                        rowdata[3] = account.getAccountClass() == AccountClass.COMPANY ? "C" : "P";
                        rowdata[4] = account.getAccountType();
                        rowdata[5] = account.getBalance();
                        model.addRow(rowdata);
                    }
                } else {
                    if (account.getAccountClass() != null && account.getAccountClass().equals(AccountClass.CREDITCARD)) {
                        //	if (entry.getDate() >= fromdate && entry.getDate() <= toDate && entry.getFromAccountNumber()=="xxc") {
                        rowdata[0] = account.getAccountNumber();
                        rowdata[1] = account.getCustomer().getName();
                        rowdata[2] = account.getCustomer().getCity();
                        rowdata[3] = account.getAccountClass() == AccountClass.COMPANY ? "C" : "P";
                        rowdata[4] = account.getAccountType();
                        rowdata[5] = account.getBalance();
                        model.addRow(rowdata);

                    }
                }
            }

    }
    void JButton_Report_actionPerformed(java.awt.event.ActionEvent event) {

        loadData();


    }

    void JButtonCalcel_actionPerformed(java.awt.event.ActionEvent event)
    {
        //make this frame invisible if Cancel button is clicked
        dispose();
    }


}





