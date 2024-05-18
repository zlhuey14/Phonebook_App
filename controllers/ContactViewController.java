package controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.opencsv.*;


import models.Contact;
import models.ContactDataAccess;
import models.UserDataAccess;
import myutils.Util;
import views.*;

public class ContactViewController {
	
	private ContactView contactView;
	
	public ContactViewController(ContactView cv) {
		this.contactView = cv;
		
		contactView.addMenuItemListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == contactView.getItem1()) { // 'Add Contact' menu item
					new AddContactWindow(contactView);
					
				}
				if (e.getSource() == contactView.getItem2()) { // 'Export Contacts' menu item
					String input = JOptionPane.showInputDialog("Enter a filename:");
					input = input.replace(" ", "_");
					input = input + ".csv";
					try {
						exportToCsv(input);
						JOptionPane.showMessageDialog(null, "Contacts exported successfully.");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				if (e.getSource() == contactView.getItem3()) { // 'Exit' menu item
					contactView.dispose();
					UserDataAccess.currentUserId = 0;
					ContactDataAccess.contactSelected = null;
					new LoginView();
					
				}
			}	
		});
		
		contactView.addContactListListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ContactDataAccess cda = new ContactDataAccess();
				int index = contactView.getContactList().getSelectedIndex();
				System.out.println("Row in contactList selected: "+index);
				
				String[] contactInfo = cda.getCurrentUserContacts().get(index).split(" ");
				Contact contact = new Contact();
				try {
					contact = cda.getContact(contactInfo[0], contactInfo[1], Util.removePhoneNumberFormat(contactInfo[2]));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContactDataAccess.contactSelected = contact;
				
				Contact sc = contact;
				System.out.println("Selected contact: "+"Id: "+sc.getId()+" "+sc.getFirstName()+" "+sc.getLastName()+" "+sc.getPhoneNumber());
				
				
			}
			
		});
		
		// https://stackoverflow.com/questions/4854185/jlist-right-click-shows-menu-use-drop-cancel
		contactView.addContactListMouseAdapter(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					//**
					// The below line of code immediately selects the item in the list upon right clicking it.
					// Comment out this line and try right clicking an element to see the outcome.
					// **
					contactView.getContactList().setSelectedIndex(contactView.getContactList().locationToIndex(e.getPoint())); 
					
					JPopupMenu menu = new JPopupMenu();
					JMenuItem editItem = new JMenuItem("Edit Contact");
					JMenuItem deleteItem = new JMenuItem("Delete Contact");
					
					editItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new EditContactWindow(contactView);
							
						}
					});
					
					deleteItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int choice = JOptionPane.showOptionDialog(cv, "Delete the selected contact?", "Delete Contact", 2, 3, null, null, e);
							
							switch(choice) {
							case 0: // case for confirming deleting of the contact
								new ContactDataAccess().deleteContact(ContactDataAccess.contactSelected);
								new ContactView();
								contactView.dispose();
								break;
							case 2: // case for cancelling the delete
								break;
							}
						}
						
					});
					
					menu.add(editItem);
					menu.add(deleteItem);
					menu.show(contactView.getContactList(), e.getPoint().x, e.getPoint().y);
					
				}
			}
		});
		
		contactView.addSearchFieldDocumentListener(new DocumentListener () {
			@Override
			public void insertUpdate(DocumentEvent e) {filter();}
			@Override
			public void removeUpdate(DocumentEvent e) {filter();}
			@Override
			public void changedUpdate(DocumentEvent e) {filter();}
			private void filter() {
				String filter = contactView.getSearchFieldInput();
				filterContactList((DefaultListModel<String>)contactView.getContactList().getModel(), filter);
			}
		});
	}
	
	
	public void exportToCsv(String filePath) throws IOException {
		try {
			File file = new File(filePath);
			FileWriter outFile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outFile);
			
			//making the header
			String[] header = {"First Name", "Last Name", "Phone #"};
			writer.writeNext(header);
			
			DefaultListModel<String> contactList = new ContactDataAccess().getCurrentUserContacts();
			
			for(int i = 0; i < contactList.getSize(); i++) {
				String[] data = contactList.elementAt(i).split(" "); 
				writer.writeNext(data);
			}
			writer.close();
		}
		catch(IOException e) {}
	}
	
	//https://stackoverflow.com/questions/26271760/filtering-a-jlist-from-text-field-input
	// --
	// A lot of this code is altered, however enough of it is still there that I think 
	// it is worthy to cite.
	// --
	public void filterContactList(DefaultListModel<String> model, String filter) {
		ContactDataAccess cda = new ContactDataAccess();
		DefaultListModel<String> contactListModel = cda.getCurrentUserContacts();
		ArrayList<String> contactListArray = new ArrayList<String>();
		
		
		while(!contactListModel.isEmpty()) {
			contactListArray.add(contactListModel.firstElement());
			contactListModel.remove(0);
		}
		
		for (String contact: contactListArray) {
			if (!contact.contains(filter)) {
	            if (model.contains(contact)) {
	                model.removeElement(contact);
	            }
	        } else {
	            if (!model.contains(contact)) {
	                model.addElement(contact);
	                cda.sortUserContacts(model);
	            }
	        }
		}
	}
}
