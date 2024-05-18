package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;
import myutils.Util;

public class LoginView extends JFrame {
	private final int WINDOW_WIDTH = 300;
	private final int WINDOW_HEIGHT = 150;
	
	private JPanel loginPanel;
	private JTextField usernameField;
	private JPasswordField passField;
	private JButton loginBtn, registerBtn;
	
	public LoginView() {
		//
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialize components
		loginPanel = new JPanel(new GridLayout(4,1 , 0,-5));
		usernameField = new JTextField("Enter Username",18);
		passField = new JPasswordField(18);
		loginBtn = new JButton("Login");
		registerBtn = new JButton("Register");
		
		
		// title panel
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Login");
		title.setFont(new Font("Serif", Font.BOLD, 32));
		titlePanel.add(title);
		
		// login commponent panel
		JPanel panel1 = new JPanel();
		usernameField.setFont(Util.TEXT_FONT_ITALIC);
		panel1.add(usernameField);
		
		// password component panel
		JPanel panel2 = new JPanel();
		passField.setEchoChar((char)0);
		passField.setText("Enter Password");
		passField.setFont(Util.TEXT_FONT_ITALIC);
		panel2.add(passField);
		
		
		// add cosmetic changes to the login button
		loginBtn.setPreferredSize(new Dimension(88,32));
		Color c1 = new Color(43,143,178);
		loginBtn.setOpaque(true);
		loginBtn.setBorderPainted(false);
		loginBtn.setBackground(c1);
		loginBtn.setForeground(Color.white);

		// add cosmetic changes to the register button
		registerBtn.setPreferredSize(new Dimension(88,32));
		Color c2 = new Color(43,178,96);
		registerBtn.setOpaque(true);
		registerBtn.setBorderPainted(false);
		registerBtn.setBackground(c2);
		registerBtn.setForeground(Color.white);
		
		// add buttons to the button panel
		JPanel panel3 = new JPanel(new BorderLayout());
		JPanel panel3SubPanel = new JPanel(); // using sub-panel to group together the buttons so they are centered together when added to the main panel
		panel3SubPanel.add(registerBtn);
		panel3SubPanel.add(loginBtn);
		panel3.add(panel3SubPanel, BorderLayout.CENTER); 
		
		
		
		// adding components to the main panel
		loginPanel.add(titlePanel);
		loginPanel.add(panel1);
		loginPanel.add(panel2);
		loginPanel.add(panel3);
		
		// add the main panel to the frame
		add(loginPanel);
		
		// adding the controller
		new LoginController(this);
		
		//
		pack();
		setLocationRelativeTo(null);
		title.requestFocusInWindow(); // this makes it so that the first JTextField isn't automatically selected upon running the application
		setVisible(true);
	}
	
	
	// Add an action listener to the login button
	public void addLoginButtonListener(ActionListener listener) {
		loginBtn.addActionListener(listener);
	}
	
	// adding action listener to the register button
	public void addRegisterButtonListener(ActionListener listener) {
		registerBtn.addActionListener(listener);
	}
	
	
	// add a focus listener to the username text field
	public void addUsernameFocusListener(FocusListener listener) {
		usernameField.addFocusListener(listener);
	}
	
	// add a focus listener to the password text field
	public void addPasswordFocusListener(FocusListener listener) {
		passField.addFocusListener(listener);
	}
	
	
	public JTextField getUsernameTextField() {
		return this.usernameField;
	}
	
	public JPasswordField getPasswordField() {
		return this.passField;
	}
	
	public String getUsernameTextFieldInput() {
		return usernameField.getText();
	}
	
	
	public String getPasswordFieldInput() {
		return new String(passField.getText());
	}
}
