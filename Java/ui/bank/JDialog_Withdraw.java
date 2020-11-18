package ui.bank;


import application.banking.transaction.HistoryCommand;
import application.banking.transaction.Withdraw;

import javax.swing.*;
import java.io.IOException;

public class JDialog_Withdraw extends javax.swing.JDialog
{
   
    private BankFrm parentframe;
    private String accnr;

	public JDialog_Withdraw(BankFrm parent, String aaccnr)
	{
		super(parent);
		parentframe=parent;
		accnr=aaccnr;
		
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.

		setTitle("Withdraw");
		setModal(true);
		getContentPane().setLayout(null);
		setSize(277,134);
		setVisible(false);
		JLabel1.setText("Acc Nr");
		getContentPane().add(JLabel1);
		JLabel1.setForeground(java.awt.Color.black);
		JLabel1.setBounds(12,12,48,24);
		JLabel2.setText("Amount");
		getContentPane().add(JLabel2);
		JLabel2.setForeground(java.awt.Color.black);
		JLabel2.setBounds(12,36,48,24);
		JTextField_NAME.setEditable(false);
		getContentPane().add(JTextField_NAME);
		JTextField_NAME.setBounds(84,12,156,20);
		getContentPane().add(JTextField_AMT);
		JTextField_AMT.setBounds(84,36,156,20);
		JButton_OK.setText("OK");
		JButton_OK.setActionCommand("OK");
		getContentPane().add(JButton_OK);
		JButton_OK.setBounds(48,84,84,24);
		JButton_Calcel.setText("Cancel");
		JButton_Calcel.setActionCommand("Cancel");
		getContentPane().add(JButton_Calcel);
		JButton_Calcel.setBounds(156,84,84,24);
		
	    JTextField_NAME.setText(accnr);
	
		
		SymAction lSymAction = new SymAction();
		JButton_OK.addActionListener(lSymAction);
		JButton_Calcel.addActionListener(lSymAction);
		
	}



	
	javax.swing.JLabel JLabel1 = new javax.swing.JLabel();
	javax.swing.JLabel JLabel2 = new javax.swing.JLabel();
	javax.swing.JTextField JTextField_NAME = new javax.swing.JTextField();
	javax.swing.JTextField JTextField_AMT = new javax.swing.JTextField();
	javax.swing.JButton JButton_OK = new javax.swing.JButton();
	javax.swing.JButton JButton_Calcel = new javax.swing.JButton();



	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK) {
				try {

					JButtonOK_actionPerformed(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (object == JButton_Calcel)
				JButtonCalcel_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event) throws IOException {
		double amount = Double.parseDouble(JTextField_AMT.getText());
		if (amount < parentframe.accountService.getAccount(accnr).getBalance()) {
			parentframe.transactionAmount = JTextField_AMT.getText();
		//	parentframe.accountService.withdraw(accnr, Double.parseDouble(JTextField_AMT.getText()));

			HistoryCommand historyCommand = new HistoryCommand();
			long amounts=Long.parseLong(JTextField_AMT.getText());
			Withdraw withdrawcommand = new Withdraw(parentframe.accountService,accnr,amounts);
			withdrawcommand.execute();
			historyCommand.addCommand(withdrawcommand);
		}
		else {
			JOptionPane.showMessageDialog(this, "Withdraw exceed current balance.");
		}
//		            for(Account account: AccountDB.accountList){
//				System.out.println(account.getAccountNumber()+account.getBalance());
//			}
		dispose();
	}

	void JButtonCalcel_actionPerformed(java.awt.event.ActionEvent event)
	{
		dispose();
	}
}