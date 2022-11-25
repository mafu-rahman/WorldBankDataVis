package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
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
	
	/**
	 * Constructor Method
	 */
	public loginUI() {
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
		
		statusLabel = new JLabel();
		statusLabel.setBounds(100, 110, 200, 25);
		panel.add(statusLabel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginButtonClicked();
				
			}
		});
	}

	/**
	 * Checks if the recalculate button was clicked on, if clicked,
	 * it generates the desired visualization 
	 * @param e Event that triggers activation 
	 */
	@SuppressWarnings("deprecation")
	public void loginButtonClicked() {
		//getting the username and password from the UI text field
		
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
			
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader((path)));
		
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if(username.equals(values[0]) && password.equals(values[1])){
					statusLabel.setText("Success!");
					frame.dispose(); //login window closes when login is successful
					new MainUI();
					return;
				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		statusLabel.setText("Wrong Credentials!");
	}
	
	/**
	 * Setter Method
	 * @param user String value of username to be used instead of the previous value
	 */
	public void setUsername(String user) {
		this.username = user;
	}
	
	/**
	 * Setter Method
	 * @param pass String value of the password to be used instead of the previous value
	 */
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	/**
	 * Getter Method
	 * @return username
	 */
	public String getUsername() {
		if(this.username == null) {
			throw new NullPointerException("Error: No username entered!");
		}
		return this.username;
	}
	
	/**
	 * Getter Method
	 * @return password 
	 */
	public String getPassword() {
		if(this.password == null) {
			throw new NullPointerException("Error: No password entered!");
		}
		return this.password;
	}
	
}
