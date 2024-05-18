package views;

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

import controllers.RegisterController;
import myutils.Util;

public class RegisterView extends JFrame {
	private final int WINDOW_WIDTH = 300;
	private final int WINDOW_HEIGHT = 150;
	
	// components
	private JPanel registerPanel;
	private JTextField usernameField;
	private JPasswordField passwordField, confirmPassword;
	private JButton registerBtn, cancelBtn;
	
	
	public RegisterView() {
		setTitle("Register");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// intialize components
		usernameField = new JTextField("Enter Username",18);
		passwordField = new JPasswordField(18);
		confirmPassword = new JPasswordField(18);
		registerBtn = new JButton("Register");
		cancelBtn = new JButton("Cancel");
		registerPanel = new JPanel(new GridLayout(5,1 , 0,-5));
		
		// title panel
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Register");
		title.setFont(new Font("Serif", Font.BOLD, 32));
		titlePanel.add(title);
		
		// username panel
		JPanel usernamePanel = new JPanel();
		usernameField.setFont(Util.TEXT_FONT_ITALIC);
		usernamePanel.add(usernameField);
		
		// password panel
		JPanel passwordPanel = new JPanel();
		passwordField.setEchoChar((char)0);
		passwordField.setText("Enter Password");
		passwordField.setFont(Util.TEXT_FONT_ITALIC);
		passwordPanel.add(passwordField);
		
		// confirm password panel
		JPanel confirmPassPanel = new JPanel();
		confirmPassword.setEchoChar((char)0);
		confirmPassword.setText("Confirm Password");
		confirmPassword.setFont(Util.TEXT_FONT_ITALIC);
		confirmPassPanel.add(confirmPassword);

		// add cosmetic changes to the register button
		registerBtn.setPreferredSize(new Dimension(88,32));
		Color c1 = new Color(43,178,96);
		registerBtn.setOpaque(true);
		registerBtn.setBorderPainted(false);
		registerBtn.setBackground(c1);
		registerBtn.setForeground(Color.white);
		
		// add cosmetic changes to the cancel button
		cancelBtn.setPreferredSize(new Dimension(88,32));
		Color c2 = new Color(201, 6, 22);
		cancelBtn.setOpaque(true);
		cancelBtn.setBorderPainted(false);
		cancelBtn.setBackground(c2);
		cancelBtn.setForeground(Color.white);

		// button panel 
		JPanel btnPanel = new JPanel();
		btnPanel.add(registerBtn);
		btnPanel.add(cancelBtn);
		
		// add component panels to the main panel
		registerPanel.add(titlePanel);
		registerPanel.add(usernamePanel);
		registerPanel.add(passwordPanel);
		registerPanel.add(confirmPassPanel);
		registerPanel.add(btnPanel);
		
		// add panel to the frame
		add(registerPanel);
		
		// adding the controller 
		new RegisterController(this);
		
		pack();
		setLocationRelativeTo(null);
		title.requestFocusInWindow();
		setVisible(true);
		
	}
	
	
	
	
	
	//----------------------------------
	public void addCancelButtonListener(ActionListener listener) {
		cancelBtn.addActionListener(listener);
	}
	
	public void addRegisterButtonListener(ActionListener listener) {
		registerBtn.addActionListener(listener);
	}
	
	
	//----------------------------------
	public String getUsernameTextFieldInput() {
		return usernameField.getText();
	}
	
	public JTextField getUsernameTextField() {
		return this.usernameField;
	}
	
	
	//----------------------------------
	public String getPasswordFieldInput() {
		return new String(passwordField.getText());
	}
	
	public JPasswordField getPasswordField() {
		return this.passwordField;
	}
	
	
	//----------------------------------
	public String getConfirmPasswordFieldInput() {
		return new String(confirmPassword.getText());
	}
	
	public JPasswordField getConfirmPasswordField() {
		return this.confirmPassword;
	}
	
	
	
	//----------------------------------
	// add a focus listener to the username text field
	public void addUsernameFocusListener(FocusListener listener) {
		usernameField.addFocusListener(listener);
	}

	// add a focus listener to the password text field
	public void addPasswordFocusListener(FocusListener listener) {
		passwordField.addFocusListener(listener);
	}
	
	public void addConfirmPasswordFocusListener(FocusListener listener) {
		confirmPassword.addFocusListener(listener);
	}
	
	

}
