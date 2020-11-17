package ui.bank;

import banking.AccountEntry;
import banking.Database.AccountEntryDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * A basic JFC based application.
 */
public class BankRpt extends JFrame
{
    /****
     * init variables in the object
     ****/
    String accountnr, clientName,street,city,zip,state,accountType,clientType,amountDeposit;
    boolean newaccount;
    private static DefaultTableModel model =new DefaultTableModel();
    private JTable JTable1;
    private JScrollPane JScrollPane1 =new JScrollPane();
    BankRpt myframe;
    private Object rowdata[];

	public BankRpt()
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
//        JScrollPane1 = new JScrollPane();
//        model = new DefaultTableModel();
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

		loadData();
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

		
	}
	public  void loadData(){
		rowdata = new Object[8];
		System.out.println(AccountEntryDB.accountEntry.size());
		for (AccountEntry entry:AccountEntryDB.accountEntry) {

			 rowdata[0]=entry.getFromAccountNumber();
			 rowdata[1]=entry.getFromPersonName();
			 rowdata[2]=entry.getFromPersonName();
			 rowdata[3]=entry.getFromPersonName();
			 rowdata[4]=entry.getFromPersonName();
			 rowdata[5]=entry.getAmount();

			System.out.println(entry.getFromAccountNumber() + " "+ entry.getAmount() + " "+ entry.getFromPersonName());
			model.addRow(rowdata);
		}

			// add row to table
//			rowdata[0] = accountnr;
//			rowdata[1] = clientName;
//			rowdata[2] = city;
//			rowdata[3] = "C";
//			rowdata[4] = accountType;
//			rowdata[5] = "0";
//			model.addRow(rowdata);

			//JTable1.getSelectionModel().setAnchorSelectionIndex(-1);


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
			(new BankRpt()).setVisible(true);
		} 
		catch (Throwable t) {
			t.printStackTrace();
			//Ensure the application exits with an error condition.
			System.exit(1);
		}
	}


	JPanel JPanel1 = new JPanel();

	JButton JButton_Exit = new JButton();

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

		}
	}





	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();


			
		}
	}
    
    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event)
	{
		System.exit(0);
	}


}
