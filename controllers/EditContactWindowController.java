package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import models.Contact;
import models.ContactDataAccess;
import views.ContactView;
import views.EditContactWindow;

public class EditContactWindowController {
	
	private EditContactWindow ecw;

	public EditContactWindowController(EditContactWindow ecw) {
		this.ecw = ecw;
		
		ecw.addCancelBtnActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ecw.dispose();
			}	
		});
		
		ecw.addApplyChangesBtnActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = ContactDataAccess.contactSelected.getId();
				String fName = ecw.getfNameInput();
				String lName = ecw.getlNameInput();
				String phonenumber = ecw.getPhonenumberInput();
				
				Contact contact = new Contact();
				contact.setId(id);
				contact.setFirstName(fName);
				contact.setLastName(lName);
				contact.setPhoneNumber(phonenumber);
				
				if (new ContactDataAccess().updateContact(contact)) {
					JOptionPane.showMessageDialog(null, "Contact updated succssfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error updating contact.");
				}
				
				ecw.setVisible(false);
				new ContactView();
				SwingUtilities.getWindowAncestor(ecw).dispose();
			
			}	
		});
		
	}
}




