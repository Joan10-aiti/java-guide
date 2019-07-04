package projectFinal1;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class Home implements ActionListener {
	JFrame frame;
	
	JButton registrationButton = new JButton("Registration Form");
	JButton attendanceButton = new JButton("Attendance Form");
	JButton recordButton = new JButton("Record");
	
Home() {
	
	createWindow();
    setLocationAndSize();
	addComponentsToFrame();
	actionEvent();
}	
public void createWindow() {
	frame=new JFrame();
	frame.setTitle("Home");
	frame.setBounds(40,40,380,600);
	frame.getContentPane().setBackground(Color.pink);
	frame.getContentPane().setLayout(null);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(true);
}

public void setLocationAndSize() {
	registrationButton.setBounds(70,100,150,35);
	attendanceButton.setBounds(70,200,150,35);
	recordButton.setBounds(70,300,150,35);
	
}
public void addComponentsToFrame()
{
	frame.add(registrationButton);
	frame.add(attendanceButton);
	frame.add(recordButton);
}
public void actionEvent()
{
	registrationButton.addActionListener(this);
	attendanceButton.addActionListener(this);
	recordButton.addActionListener(this);
}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==registrationButton) {
		new RegistrationForm();
		}
		if(e.getSource()==attendanceButton) {
			new Attendance();
			}
		if(e.getSource()==recordButton) {
			new Record();
			}
		
	}
	 
	
		
	public static void main (String[] args) 
	{
	  new Home();	  
	}
	
}
