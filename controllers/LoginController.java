package controllers;

import java.awt.event.*;
import models.UserDataAccess;
import myutils.Util;
import views.*;

public class LoginController {

	private LoginView loginView;
	
	public LoginController(LoginView lv) {
		
		this.loginView = lv;
		
		loginView.addRegisterButtonListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				loginView.setVisible(false);
				new RegisterView();
				
			}
		});
		
		loginView.addLoginButtonListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String username = loginView.getUsernameTextFieldInput();
				String password = loginView.getPasswordFieldInput();
				UserDataAccess uda = new UserDataAccess();
								
				if(uda.verifyUser(username, password)) {
					loginView.setVisible(false);
					new ContactView();
				} else {
					//do nothing
				}
			}
		});
		
		loginView.addUsernameFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(loginView.getUsernameTextFieldInput().equals("Enter Username")) {
					loginView.getUsernameTextField().setText("");
					loginView.getUsernameTextField().setFont(Util.TEXT_FONT_PLAIN);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(loginView.getUsernameTextFieldInput().equals("")) {
					loginView.getUsernameTextField().setText("Enter Username");
					loginView.getUsernameTextField().setFont(Util.TEXT_FONT_ITALIC);
				}
			}
			
		});
		
		loginView.addPasswordFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				loginView.getPasswordField().setEchoChar('*'); // sets the echo character to '*' so when data is input into the field it is hidden 
				loginView.getPasswordField().setText(""); // set to empty string to remove the default text from the JPasswordField
					
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(loginView.getPasswordFieldInput().isEmpty()) {
					loginView.getPasswordField().setEchoChar((char)0); // sets the echo character so that it is not hidden when showing the default text
					loginView.getPasswordField().setText("Enter Password");
					
				}	
			}
		});
	}
}