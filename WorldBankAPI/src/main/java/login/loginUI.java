package login;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.mainUI;

public class loginUI extends JFrame implements ActionListener{
	
	/*
	 * Class attributes
	 */
	
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel usernameLabel;
	private static JLabel passwordLabel;
	private static JLabel statusLabel;
	private static JTextField usernameText;
	private static JPasswordField passwordText;
	private static JButton loginButton;
	private String username;
	private String password;
	

	/*
	 * Main Method
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		frame.setTitle("Login");
		
		frame.setSize(900, 600);
		
		//username label
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 20, 80, 25);
		panel.add(usernameLabel);

		//password label
		passwordLabel = new JLabel("Enter Password:");
		passwordLabel.setBounds(10,  50,  120,  25);
		panel.add(passwordLabel);

		//username text
		usernameText = new JTextField(20); 
		usernameText.setBounds(160, 20, 165, 25);
		panel.add(usernameText);

		//password text
		passwordText = new JPasswordField(20); 
		passwordText.setBounds(160, 50, 165, 25);
		panel.add(passwordText);

		//login button
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		loginButton.addActionListener(new loginUI());
		
		statusLabel = new JLabel();
		statusLabel.setBounds(100, 110, 200, 25);
		panel.add(statusLabel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	/*
	 *  Static factory Methods
	 */
	
	
	/*
	 * Constructors
	 */
	public loginUI() {
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//getting the username and password from the UI text field
		String user = usernameText.getText();
		String password = passwordText.getText();
		this.username = usernameText.getText();
		this.password = passwordText.getText();
		
		/*
		 * Theres a .csv file where username and passwords are stored as comma separated values (.csv)
		 */
		String path = "credentials.csv";
		String line = "";
		
		/*
		 * Using buffered reader to read the .csv file line by line and match credentials
		 */
		try {
			
			BufferedReader br = new BufferedReader(new FileReader((path)));
		
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if(user.equals(values[0]) && password.equals(values[1])){
					statusLabel.setText("Success!");
					frame.dispose(); //login window closes when login is successful
					new mainUI();
					return;
				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		statusLabel.setText("Wrong Credentials!");
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getUsername() {
		if(this.username == null) {
			throw new NullPointerException("Error: No username entered!");
		}
		return this.username;
	}
	
	public String getPassword() {
		if(this.password == null) {
			throw new NullPointerException("Error: No password entered!");
		}
		return this.password;
	}
	
}
