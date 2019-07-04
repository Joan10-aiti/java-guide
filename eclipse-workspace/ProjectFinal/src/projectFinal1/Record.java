package projectFinal1;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Record implements ActionListener {
	JFrame frame;
	
	JLabel Sid = new JLabel(" Sdt ID");
	JTextField SidField = new JTextField(15);
	JButton searchButton = new JButton("Search");
	
	Record() {
		
		createWindow();
	    setLocationAndSize();
		addComponentsToFrame();
		actionEvent();
	}	
	
	public void createWindow() {
		frame=new JFrame();
		frame.setTitle("Record");
		frame.setBounds(40,40,380,600);
		frame.getContentPane().setBackground(Color.pink);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
	}
	
	public void setLocationAndSize() {
		Sid.setBounds(20, 20, 40, 70);
		SidField.setBounds(180, 43, 165, 23);
		searchButton.setBounds(70,400,100,35);
	}
	
	public void addComponentsToFrame() {
		frame.add(Sid);
		frame.add(SidField);
		frame.add(searchButton);
	}
	
	public void actionEvent() {
		searchButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==searchButton) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancechecker","root","");
				String query = "select * from attendance where sid = Sid"; 
				Statement st = conn.createStatement();			
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
					int Sid = rs.getInt("sid");
					Date date = rs.getDate("date");
					
					System.out.format("%s %s", Sid,date+"\n");
				}
				
			}catch(SQLException e1) {
					e1.printStackTrace();
				}
		}
	}
	
	public static void main() {
		new Record();
	}

}
