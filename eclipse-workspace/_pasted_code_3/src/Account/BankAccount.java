package Account;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankAccount implements ActionListener{
	 
	      private JTextField acctNumber;
	      private JLabel numberOutput;
	      private JTextField acctName;
	      private JTextField acctBalance;
	      private JTextField depositAmount;
	      private JButton depositButton;
	      private JTextField withdrawAmount;
	      private JButton withdrawButton;
	      public BankAccount()
	    {
	        JFrame frame = new JFrame ("Account Information");
	        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(makePanel());
	        frame.pack();
	        frame.setVisible(true);

	    }
	    private JPanel makePanel() {
	        JPanel panel = new JPanel();
	        panel.add (new JLabel ("Account Number"));
	        acctNumber = new JTextField();
	        panel.add(acctNumber);
	        
	        panel.add (new JLabel ("Account Name"));
	        acctName = new JTextField();
	        panel.add(acctName);
	        
	        panel.add (new JLabel ("Account Balance"));
	        acctBalance = new JTextField(6);
	        panel.add(acctBalance);
	        
	        this.acctName.setText(account.getName());
	        this.acctNumber.setText("" + account.getNumber());
	        this.acctBalance.setText("" + account.getBalance());
	        
	         withdrawButton = new JButton ("Withdraw:");
	        withdrawAmount = new JTextField(10);
	        panel.add (withdrawButton);
	        panel.add (withdrawAmount);
	        withdrawButton.addActionListener(this);
	        
	        depositButton = new JButton ("Deposit:");
	        depositAmount = new JTextField(10);
	        panel.add (depositButton);
	        panel.add (depositAmount);
	        depositButton.addActionListener(this);
	        
	        
	        panel.setPreferredSize(new Dimension(600,200));
	        panel.setBackground(Color.blue);
	        return panel;
	     }


	          public void actionPerformed (ActionEvent event){
	        	  
	        	  
	        	  
	             if (event.getSource() == withdrawButton)
	                {
	            	 try {
	            		  double amount = Double.parseDouble(this.withdrawAmount.getText());
		                  this.account.withdraw(amount, 1.00);
		                  this.acctBalance.setText(Double.toString(this.account.getBalance()));
	            	 
	            	      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
	            	      
	            	      PreparedStatement pstatement = conn.prepareStatement("insert into account values(?,?,?)");
	            	      
	            	      pstatement.setString(1, acctNumber.getText());
	            	      pstatement.setString(2,acctName.getText());
	            	      pstatement.setString(3, acctBalance.getText());
	            	      
	            	      if((withdrawAmount.getText()!="")||(depositAmount.getText()!="")) {
	            	    	  pstatement.executeUpdate();
	            	    	  JOptionPane.showMessageDialog(null, "Success");
	            	      }else {
	            	    	  JOptionPane.showMessageDialog(null, "Error");
	            	      }
	            	 
	                 
	                }catch(SQLException e1) {
	                	e1.printStackTrace();
	                }}
	            	 
	            	 
	             if (event.getSource() == depositButton)
	             {
	            	try {
	            		double amount = Double.parseDouble(this.depositAmount.getText());
	                  this.account.deposit(amount);
	                  this.acctBalance.setText(Double.toString(this.account.getBalance()));
	                  
	                  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
            	      
            	      PreparedStatement pstatement = conn.prepareStatement("insert into account values(?,?,?)");
            	      
            	      pstatement.setString(1, acctNumber.getText());
            	      pstatement.setString(2,acctName.getText());
            	      pstatement.setString(3, acctBalance.getText());
            	      
            	      if((withdrawAmount.getText()!="")||(depositAmount.getText()!="")) {
            	    	  pstatement.executeUpdate();
            	    	  JOptionPane.showMessageDialog(null, "Success");
            	      }else {
            	    	  JOptionPane.showMessageDialog(null, "Error");
            	    	  }
	             } catch(SQLException e1) {
	                	e1.printStackTrace();}}
	                	
	          }

	      private Account account;{
	          account = new Account("John Doe",12345,100.00);
	       }


	        public static void main (String[] args)
	         {
	          new BankAccount();
	         }
			public JLabel getNumberOutput() {
				return numberOutput;
			}
			public void setNumberOutput(JLabel numberOutput) {
				this.numberOutput = numberOutput;
			}
	

}
