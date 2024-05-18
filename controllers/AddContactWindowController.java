package controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import models.ContactDataAccess;
import myutils.Util;
import views.AddContactWindow;
import views.ContactView;

public class AddContactWindowController {
	
	private AddContactWindow acw;
	
	public AddContactWindowController(AddContactWindow acw) {
		this.acw = acw;
		
		acw.addCancelBtnActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acw.dispose();
			}	
		});
		
		acw.addContactBtnActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
			
				String fName = acw.getfNameInput();
				String lName = acw.getlNameInput();
				String phonenumber = acw.getPhonenumberInput();
				
				
				if(fName.contains(" ")) {
					JOptionPane.showMessageDialog(acw, "First name cannot contain a space", null, 0);
					flag = false;
				}
				if(lName.contains(" ")) {
					JOptionPane.showMessageDialog(acw, "Last name cannot contain a space", null, 0);
					flag = false;
				}
				if(phonenumber.length() > 10 || phonenumber.length() < 10) {
					JOptionPane.showMessageDialog(acw, "Phone number must be 10 characters long", null, 0);
					flag = false;
				}
				
				if(flag) {
					ContactDataAccess cda = new ContactDataAccess();
					
					cda.addContact(fName, lName, phonenumber);
					acw.setVisible(false);
					
					// Refreshing the ContactView frame to show the new contact added
					ContactView contactView = new ContactView();
					contactView.setVisible(true);
					SwingUtilities.getWindowAncestor(acw).dispose();
					
				}
			}	
		});
		
		acw.addFnameFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(acw.getfNameInput().equals("First name...")) {
					acw.getfNameField().setText("");
					acw.getfNameField().setFont(Util.TEXT_FONT_PLAIN);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(acw.getfNameInput().isEmpty()) {
					acw.getfNameField().setText("First name...");
					acw.getfNameField().setFont(Util.TEXT_FONT_ITALIC);
				}
			}
		});
		
		acw.addLnameFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(acw.getlNameInput().equals("Last name...")) {
					acw.getlNameField().setText("");
					acw.getlNameField().setFont(Util.TEXT_FONT_PLAIN);
				}
				
				
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(acw.getlNameInput().isEmpty()) {
					acw.getlNameField().setText("Last name...");
					acw.getlNameField().setFont(Util.TEXT_FONT_ITALIC);
				}
			}
		});
		
		acw.addPhonenumberFocusListener(new FocusListener() {
		
			@Override
			public void focusGained(FocusEvent e) {
				if (acw.getPhonenumberInput().equals("Phone Number...")) {
					acw.getPhonenumberField().setText("");
					acw.getPhonenumberField().setFont(Util.TEXT_FONT_PLAIN);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(acw.getPhonenumberInput().isEmpty()) {
					acw.getPhonenumberField().setText("Phone Number...");
					acw.getPhonenumberField().setFont(Util.TEXT_FONT_ITALIC);
				}
				
			}
		});
	}

}
