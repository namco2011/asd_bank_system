package ui.bank;

import application.framework.AccountEntry;
import application.framework.AccountEntryDB;
import application.framework.AccountService;
import application.framework.AccountServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

/**
 * A basic JFC based application.
 */
public class BankRptMonthly extends JFrame
{
    /****
     * init variables in the object
     ****/
    String accountnr, clientName,street,city,zip,state,accountType,clientType,amountDeposit;
    boolean newaccount;
    private DefaultTableModel model = new DefaultTableModel();
    private JTable JTable1;
    private JScrollPane JScrollPane1 =new JScrollPane();
    BankRptMonthly myframe;
    private Object rowdata[];
	AccountService accountService =new AccountServiceImpl();
	AccountEntryDB accountEntryDB;

	public BankRptMonthly()
	{
		myframe = this;

		setTitle("Bank Application.");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0,0));
		setSize(575,310);
		setVisible(false);
		JPanel1.setLayout(null);
		getContentPane().add(BorderLayout.CENTER, JPanel1);
		JPanel1.setBounds(0,0,575,310);
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
        newaccount=false;
        
        
        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12,92,444,160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];


		JButton_Generate_Report.setText("Generate Report");
		JPanel1.add(JButton_Generate_Report);
		JButton_Generate_Report.setBounds(250, 248, 140, 31);
		label_Date_From.setText("Date From");
		JPanel1.add(label_Date_From);
		label_Date_From.setForeground(Color.black);
		label_Date_From.setBounds(15,40,140,31);


		Date_From.setEditable(true);
		JPanel1.add(Date_From);
		Date_From.setBounds(10, 60, 140, 31);


		label_Date_To.setText("Date To");
		JPanel1.add(label_Date_To);
		label_Date_To.setForeground(Color.black);
		label_Date_To.setBounds(170,40,140,31);

		Date_To.setEditable(true);
		JPanel1.add(Date_To);
		Date_To.setBounds(165,60,140,31);



		AccNo_lable.setText("Account Number");
		JPanel1.add(AccNo_lable);
		AccNo_lable.setForeground(Color.black);
		AccNo_lable.setBounds(330,40,140,31);

		AccNo.setEditable(true);
		JPanel1.add(AccNo);
		AccNo.setBounds(320,60,140,31);


		JButton_Exit.setText("Exit");
		JPanel1.add(JButton_Exit);
		JButton_Exit.setBounds(468,248,96,31);

		// lineBorder1.setRoundedCorners(true);
		// lineBorder1.setLineColor(java.awt.Color.green);
		//$$ lineBorder1.move(24,312);


		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		JButton_Exit.addActionListener(lSymAction);
		JButton_Generate_Report.addActionListener(lSymAction);
		loadData();

		
	}

	public  void loadData( ){
		rowdata = new Object[6];
		//LocalDate fromdate =LocalDate.parse(Date_From.getText());
		//LocalDate  toDate = LocalDate.parse(Date_To.getText());

		LocalDate fromdate=LocalDate.now();
		if (Date_From.getText().length()>0) fromdate= LocalDate.parse(Date_From.getText());

		LocalDate toDate=LocalDate.now();
		if (Date_To.getText().length()>0) toDate=LocalDate.parse(Date_To.getText());
		model.setRowCount(0);
	for (AccountEntry entry:AccountEntryDB.accountEntries) {
		if (AccNo.getText().length() > 0 && (Date_From.getText().length() == 0 || Date_To.getText().length() == 0)) {
			if ( entry.getFromAccountNumber().equals(AccNo)) {
			//if (entry.getDate().isAfter(fromdate) && entry.getDate().isBefore(toDate) && entry.getFromAccountNumber()==AccNo.getText()) {
			rowdata[0] = entry.getFromAccountNumber();
			rowdata[1] = entry.getFromPersonName();
			rowdata[2] = entry.getDescription();
			rowdata[3] = entry.getAmount();
			rowdata[4] = entry.getDate();

			model.addRow(rowdata);
			//}
		}
			else if (AccNo.getText().length()>0 && Date_From.getText().length()>0 && Date_To.getText().length()>0){
				if (entry.getDate().isAfter(fromdate)
						&& entry.getDate().isBefore(toDate)
						&& entry.getFromAccountNumber().equals(AccNo)) {
					// if (entry.getDate() >= fromdate && entry.getDate() <= toDate && entry.getFromAccountNumber()=="xxc") {
					rowdata[0] = entry.getFromAccountNumber();
					rowdata[1] = entry.getFromPersonName();
					rowdata[2] = entry.getDescription();
					rowdata[3] = entry.getAmount();
					rowdata[4] = entry.getDate();
					model.addRow(rowdata);
				}
			}
			else {
				rowdata[0] = entry.getFromAccountNumber();
				rowdata[1] = entry.getFromPersonName();
				rowdata[2] = entry.getDescription();
				rowdata[3] = entry.getAmount();
				rowdata[4] = entry.getDate();
				model.addRow(rowdata);
			}

	}



//		System.out.println("Year " + cal.get(Calendar.YEAR));
//		System.out.println("Month " + cal.get(Calendar.MONTH));
//		System.out.println("Day " + cal.get(Calendar.DAY_OF_MONTH));
		//ZoneId defaultZoneId = ZoneId.systemDefault();
		//AccountEntryDB.accountEntry.stream().filter(e ->  e.getDate().compareTo(cal.getTime() )>=0);

	}
	}

	
	/*****************************************************
	 * The entry point for this application.
	 * Sets the Look and Feel to the System Look and Feel.
	 * Creates a new JFrame1 and makes it visible.
	 *****************************************************/
	static public void main(String args[])
	{
		try {
		    // Add the following code if you want the Look and Feel
		    // to be set to the Look and Feel of the native system.
		    
		    try {
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (Exception e) { 
		    }
		    
			//Create a new instance of our application's frame, and make it visible.
			(new BankRptMonthly()).setVisible(true);
		} 
		catch (Throwable t) {
			t.printStackTrace();
			//Ensure the application exits with an error condition.
			System.exit(1);
		}
	}


	JPanel JPanel1 = new JPanel();
//	JButton JButton_PerAC = new JButton();
//	JButton JButton_CompAC = new JButton();
//	JButton JButton_Deposit = new JButton();
//	JButton JButton_Withdraw = new JButton();
//	JButton JButton_Addinterest= new JButton();
	JButton JButton_Exit = new JButton();
	JTextField Date_From = new JTextField();
	JTextField Date_To = new JTextField();
	javax.swing.JLabel  label_Date_From = new javax.swing.JLabel();
	javax.swing.JLabel  label_Date_To = new javax.swing.JLabel();
	javax.swing.JButton JButton_Generate_Report = new javax.swing.JButton();
	JTextField AccNo = new JTextField();
	javax.swing.JLabel  AccNo_lable = new javax.swing.JLabel();

	void exitApplication()
	{
		try {
		    	this.setVisible(false);    // hide the Frame
		    	this.dispose();            // free the system resources
		    	System.exit(0);            // close the application
		} catch (Exception e) {
		}
	}

	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == BankRptMonthly.this)
				BankFrm_windowClosing(event);
		}
	}

	void BankFrm_windowClosing(java.awt.event.WindowEvent event)
	{
		// to do: code goes here.
			 
		BankFrm_windowClosing_Interaction1(event);
	}

	void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_Exit)
				JButtonExit_actionPerformed(event);
//			else if (object == JButton_PerAC)
//				JButtonPerAC_actionPerformed(event);
//			else if (object == JButton_CompAC)
//				JButtonCompAC_actionPerformed(event);
//			else if (object == JButton_Deposit)
//				JButtonDeposit_actionPerformed(event);
//			else if (object == JButton_Withdraw)
//				JButtonWithdraw_actionPerformed(event);
//			else if (object == JButton_Addinterest)
//				JButtonAddinterest_actionPerformed(event);
			
		}
	}
    
    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event)
	{
		System.exit(0);
	}


}
