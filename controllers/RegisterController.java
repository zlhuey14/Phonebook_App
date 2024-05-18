package controllers;

import java.awt.event.*;

import javax.swing.JOptionPane;

import models.*;
import myutils.Util;
import views.*;

public class RegisterController {

	//private RegisterView registerView;
	
	public RegisterController(RegisterView rv) 
	{
		//this.registerView = rv;
		
		rv.addRegisterButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				User user = new User();
				
				// if statement to catch if the 'Password' and the 'Confirm Password' fields match
				if(Util.hashPassword(rv.getPasswordFieldInput()).equals(Util.hashPassword(rv.getConfirmPasswordFieldInput()))) {
					user.setUsername(rv.getUsernameTextFieldInput());
					user.setPassword(rv.getPasswordFieldInput());
					
					UserDataAccess uda = new UserDataAccess();
					
					if(uda.registerUser(user)) {
						JOptionPane.showMessageDialog(null, "Registered succesffully.");
						LoginView lv = new LoginView();
						//new LoginController(lv);
						lv.setVisible(true);
						rv.setVisible(false);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Register failed.");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Passwords do not match.");
				}
			}
		});
		
		rv.addCancelButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				LoginView lv = new LoginView();
				//new LoginController(lv);
				lv.setVisible(true);
				rv.setVisible(false);
			}
		});
		
		rv.addUsernameFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(rv.getUsernameTextFieldInput().equals("Enter Username")) {
					rv.getUsernameTextField().setText("");
					rv.getUsernameTextField().setFont(Util.TEXT_FONT_PLAIN);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(rv.getUsernameTextFieldInput().equals("")) {
					rv.getUsernameTextField().setText("Enter Username");
					rv.getUsernameTextField().setFont(Util.TEXT_FONT_ITALIC);
				}
				
			}
			
		});
		
		rv.addPasswordFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				rv.getPasswordField().setEchoChar('*'); // sets the echo character to '*' so when data is input into the field it is hidden 
				rv.getPasswordField().setText(""); // set to empty string to remove the default text from the JPasswordField
					
				
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(rv.getPasswordFieldInput().isEmpty()) {
					rv.getPasswordField().setEchoChar((char)0); // sets the echo character so that it is not hidden when showing the default text
					rv.getPasswordField().setText("Enter Password");
				}
				
			}
			
		});
		
		rv.addConfirmPasswordFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				rv.getConfirmPasswordField().setEchoChar('*'); // sets the echo character to '*' so when data is input into the field it is hidden 
				rv.getConfirmPasswordField().setText(""); // set to empty string to remove the default text from the JPasswordField
					
				
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(rv.getConfirmPasswordFieldInput().isEmpty()) {
					rv.getConfirmPasswordField().setEchoChar((char)0); // sets the echo character so that it is not hidden when showing the default text
					rv.getConfirmPasswordField().setText("Confirm Password");
				}
				
			}
			
		});
	}
	
}