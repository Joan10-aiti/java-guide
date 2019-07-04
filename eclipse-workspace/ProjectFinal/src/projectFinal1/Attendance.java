package projectFinal1;

import java.awt.Color;
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
import javax.swing.JTextField;


public class Attendance implements ActionListener {
JFrame frame;
	
	JLabel Sid = new JLabel(" Sdt ID");
	JLabel date = new JLabel("Date");
	JTextField SidField = new JTextField(15);
	JTextField DateField = new JTextField(20);
	JButton presentButton = new JButton("Present");
	JButton resetButton = new JButton("Reset");
	//UtilDateModel model = new UtilDateModel();
	//JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
	//JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
	
	Attendance() {
	
	createWindow();
    setLocationAndSize();
	addComponentsToFrame();
	actionEvent();
}	
public void createWindow() {
	frame=new JFrame();
	frame.setTitle("Attendance");
	frame.setBounds(40,40,380,600);
	frame.getContentPane().setBackground(Color.pink);
	frame.getContentPane().setLayout(null);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(true);
	//frame.add(datePicker);
}

public void setLocationAndSize() {
	presentButton.setBounds(70,400,100,35);
	resetButton.setBounds(220,400,100,35);
	Sid.setBounds(20, 20, 40, 70);
	date.setBounds(20, 70, 80, 70);
	SidField.setBounds(180, 43, 165, 23);
	DateField.setBounds(180,93, 165, 23);
	
	
}
public void addComponentsToFrame()
{
	frame.add(presentButton);
	frame.add(resetButton);
	frame.add(Sid);
	frame.add(date);
	frame.add(SidField);
	frame.add(DateField);
	
}
public void actionEvent()
{
	presentButton.addActionListener(this);
	resetButton.addActionListener(this);
	
}
@Override
public void actionPerformed(ActionEvent e) {
	
	if(e.getSource()==resetButton) {
		SidField.setText("");
		DateField.setText("");
     }
	if(e.getSource()==presentButton) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancechecker","root","");
			
			PreparedStatement pstatement = conn.prepareStatement("insert into attendance values(?,?)");
			
			pstatement.setString(1, SidField.getText());
			pstatement.setString(2, DateField.getText());
			
			if((SidField.getText()!="")||(DateField.getText()!="")) {
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
}
	public static void main (String[] args) {
		new Attendance();
	}
	
}
