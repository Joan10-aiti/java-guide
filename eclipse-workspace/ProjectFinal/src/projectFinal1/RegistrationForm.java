package projectFinal1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;


public class RegistrationForm implements ActionListener {
JFrame frame;
	
	JButton registerButton = new JButton("Register");
	JButton resetButton = new JButton("Reset");
	
	JLabel id = new JLabel("ID");
	JLabel Lname = new JLabel("Lastname");
	JLabel Fname = new JLabel("Firstname");
	JLabel Oname = new JLabel("Othernames");
	JTextField idField = new JTextField(15);
	JTextField LnameField = new JTextField(20);
	JTextField FnameField = new JTextField(20);
	JTextField OnameField = new JTextField(20);
	
	RegistrationForm() {
		
		createWindow();
	    setLocationAndSize();
		addComponentsToFrame();
		actionEvent();
	}	
	public void createWindow() {
		frame=new JFrame();
		frame.setTitle("Registration Form");
		frame.setBounds(40,40,380,600);
		frame.getContentPane().setBackground(Color.pink);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

	}

	public void setLocationAndSize() {
		id.setBounds(20, 20, 40, 70);
		Lname.setBounds(20, 70, 80, 70);
		Fname.setBounds(20, 120, 100, 70);
		Oname.setBounds(20, 170, 100, 70);
		
		idField.setBounds(180, 43, 165, 23);
		LnameField.setBounds(180, 93, 165, 23);
		FnameField.setBounds(180, 143, 165, 23);
		OnameField.setBounds(180, 193, 165, 23);
		
		registerButton.setBounds(70,400,100,35);
		resetButton.setBounds(220,400,100,35);
		
		
	}
	public void addComponentsToFrame()
	{
		frame.add(registerButton);
		frame.add(resetButton);
		frame.add(id);
		frame.add(Lname);
		frame.add(Fname);
		frame.add(Oname);
		frame.add(idField);
		frame.add(LnameField);
		frame.add(FnameField);
		frame.add(OnameField);
	}
	public void actionEvent()
	{
		registerButton.addActionListener(this);
		resetButton.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	if(e.getSource()==registerButton) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancechecker","root","");
			
			PreparedStatement pstatement = conn.prepareStatement("insert into student values(?,?,?,?)");
			
			pstatement.setString(1, idField.getText());
			pstatement.setString(2, LnameField.getText());
			pstatement.setString(3, FnameField.getText());
			pstatement.setString(4, OnameField.getText());
			
			if((LnameField.getText()!="")||(FnameField.getText()!="")||(idField.getText()!="")) {
				pstatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Data Successfully Registered");
			}
			else {
				JOptionPane.showMessageDialog(null, "Please Fill Required Fields");
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	if(e.getSource()==resetButton) {
		idField.setText("");
		LnameField.setText("");
		FnameField.setText("");
		OnameField.setText("");
	}
		
	}
	public static void main(String[] args) {
		new RegistrationForm();
	}
	
}



