package application.banking.report;



import application.framework.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * A basic JFC based application.
 */
public class AccountEntryRpt extends javax.swing.JFrame {
    /****
     * init variables in the object
     ****/
    String accountnr, clientName, street, city, zip, state, clientType, transactionAmount, email, noOfEmployee, dateOfBirth;
    AccountType accountType;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    AccountEntryRpt myframe;
    private Object rowdata[];


    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_Report = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();
    javax.swing.JRadioButton JRadio_Account= new JRadioButton();
    javax.swing.JRadioButton JRadio_CreditCard= new JRadioButton();

    JLabel lblfromdate = new JLabel();
    JLabel lbltodate = new JLabel();

    JTextField txtacct= new JTextField();
    JTextField fromdatestr= new JTextField();
    JTextField todatestr= new JTextField();
    ButtonGroup buttonGroup  = new ButtonGroup();

    AccountService accountService =  AccountServiceImpl.getInstance();

    public AccountEntryRpt() {
        myframe = this;

        setTitle("AccountEntry Monthly Report.");
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
//        model.addColumn("Name");
        model.addColumn("Account");
        model.addColumn("Date");
        model.addColumn("Credit");
        model.addColumn("Debit");
        model.addColumn("Description");


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

//        buttonGroup.add(JRadio_Account);
//        buttonGroup.add(JRadio_CreditCard);
//        JRadio_Account.setText("Account");
//        JPanel1.add(JRadio_Account);
//        JRadio_Account.setSelected(true);
//        JRadio_Account.setBounds(10,50,150,30);
//
//        JRadio_CreditCard.setText("Credit Card");
//        JPanel1.add(JRadio_CreditCard);
//        JRadio_CreditCard.setSelected(false);
//        JRadio_CreditCard.setBounds(100,50,150,30);
        txtacct.setText("");
        txtacct.setBounds(200,10,100,30);
        JPanel1.add(txtacct);

        fromdatestr.setText("2020-01-10");
        JPanel1.add(fromdatestr);
        fromdatestr.setBounds(200,50,100,30);

        todatestr.setText("2020-12-12");
        JPanel1.add(todatestr);
        todatestr.setBounds(340,50,100,30);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(700, 500, 96, 31);

        lblfromdate.setText("From");
        lbltodate.setBounds(260,50,40,30);
        JPanel1.add(lblfromdate);

        lbltodate.setText("To");
        lbltodate.setBounds(280,50,40,30);
        JPanel1.add(lbltodate);

        // lineBorder1.setRoundedCorners(true);
        // lineBorder1.setLineColor(java.awt.Color.green);
        //$$ lineBorder1.move(24,312);

        JButton_Report.setActionCommand("jbutton");
        AccountEntryRpt.SymWindow aSymWindow = new AccountEntryRpt.SymWindow();
        this.addWindowListener(aSymWindow);
        AccountEntryRpt.SymAction lSymAction = new AccountEntryRpt.SymAction();
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
            (new AccountEntryRpt()).setVisible(true);
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
         //   System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == AccountEntryRpt.this)
                AccountEntryRpt_windowClosing(event);
        }
    }

    void AccountEntryRpt_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        AccountEntryRpt_windowClosing_Interaction1(event);
    }

    void AccountEntryRpt_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
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
      //  System.exit(0);
        this.exitApplication();
    }
   void loadData() {
        model.setRowCount(0);

        if (fromdatestr.getText().length()>0 && todatestr.getText().length()>0) {
            LocalDate fromDate = LocalDate.parse(fromdatestr.getText());
            LocalDate toDate = LocalDate.parse(todatestr.getText());
            for (AccountEntry entry : accountService.getAllAccountEntries().stream()
                    .collect(Collectors.toList())) {
                if (txtacct.getText().length() == 0) {
                    if (entry.getDate().isAfter(fromDate) && entry.getDate().isBefore(toDate)) {
                        //    rowdata[0] =
                        rowdata[0] = entry.getFromAccountNumber();
                        rowdata[1] = entry.getDate();
                        rowdata[2] = entry.getAmount()>0?entry.getAmount():0;
                        rowdata[3] = entry.getAmount()<=0?Math.abs(entry.getAmount()):0;
                        rowdata[4] = entry.getDescription();
                        model.addRow(rowdata);

                    }
                }
                else {
                    if (entry.getDate().isAfter(fromDate) && entry.getDate().isBefore(toDate) &&entry.getFromAccountNumber().equals(txtacct.getText())) {
                        //    rowdata[0] =
                        rowdata[0] = entry.getFromAccountNumber();
                        rowdata[1] = entry.getDate();
                        rowdata[2] = entry.getAmount();
                        rowdata[3] = entry.getDescription();

                        model.addRow(rowdata);

                    }
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






