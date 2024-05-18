package models;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import myutils.Util;

public class ContactDataAccess {
	
	private Connection connect;
	public static Contact contactSelected;
	
	public ContactDataAccess() {
		try {
			this.connect = Util.getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Contact getContact(String fName, String lName, String phonenumber) {
		Contact contact = new Contact();
		try {
			
			String query = "SELECT * FROM tb_contacts WHERE f_name=? AND l_name=? AND phonenumber=? AND user=?";
			
			PreparedStatement stm = connect.prepareStatement(query);
			
			stm.setString(1, fName);
			stm.setString(2, lName);
			stm.setString(3, phonenumber);
			stm.setInt(4, UserDataAccess.currentUserId);
			
			ResultSet rs = stm.executeQuery();
	
			while(rs.next()) {
				contact.setFirstName(rs.getString("f_name"));
				contact.setLastName(rs.getString("l_name"));
				contact.setPhoneNumber(rs.getString("phonenumber"));
				contact.setId(rs.getInt("id"));
				
				return contact;
			}
			
			
			
		} catch (SQLException e) {
			
		}
		
		return contact;
	}
	
	public boolean addContact(String fName, String lName, String phonenumber){
		try {
			String query = "INSERT INTO tb_contacts(f_name, l_name, phonenumber, user) VALUES (?, ?, ?, ?)";
			PreparedStatement stm = connect.prepareStatement(query);
			
			stm.setString(1, fName);
			stm.setString(2, lName);
			stm.setString(3, phonenumber);
			stm.setInt(4, UserDataAccess.currentUserId);
			
			int rows = stm.executeUpdate();
			
			if (rows > 0) {
				JOptionPane.showMessageDialog(null, "Contact added successfully.");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error adding contact contact.");
				return false;
			}
		} catch (SQLException e) {
			
			return false;
		}
	}
	
	public boolean updateContact(Contact contact) {
		try {
			String query = "UPDATE tb_contacts SET f_name=?, l_name=?, phonenumber=? WHERE id=? AND user=?";
			PreparedStatement stm = connect.prepareStatement(query);

			stm.setString(1, contact.getFirstName());
			stm.setString(2, contact.getLastName());
			stm.setString(3, contact.getPhoneNumber());
			stm.setInt(4, contact.getId());
			stm.setInt(5, UserDataAccess.currentUserId);

			int row = stm.executeUpdate();

			if (row > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean deleteContact(Contact contact) {
		String query = "DELETE FROM tb_contacts WHERE id=?";
		try {
			PreparedStatement stm = connect.prepareStatement(query);
			stm.setInt(1, contact.getId());
			
			int rows = stm.executeUpdate();
			
			if (rows > 0) {
				JOptionPane.showMessageDialog(null, "Contact deleted successfully.");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error attempting to delete contact.");
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public DefaultListModel<String> getCurrentUserContacts() {
		DefaultListModel<String> contactList = new DefaultListModel<String>();
		
		try {
			String query = "SELECT * FROM tb_contacts WHERE user=?";
			PreparedStatement stm;
			stm = connect.prepareStatement(query);
			stm.setInt(1, UserDataAccess.currentUserId);
			
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				try {
					contactList.addElement(rs.getString(2)+" "+ rs.getString(3)+" "+Util.formatPhoneNumber(rs.getString(4)));
				} catch (ParseException e) {}
			}
			//System.out.println(contactList);
			return sortUserContacts(contactList);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public DefaultListModel<String> sortUserContacts(DefaultListModel<String> inputList) {
		int listSize = inputList.getSize();
		
		for(int i = 0; i < listSize; i++) {
			for(int row = 0; row < listSize-1-i ;row++) {
				String name1 = inputList.elementAt(row);
				String name2 = inputList.elementAt(row+1);

				String[] firstName1 = inputList.elementAt(row).split(" ");
				firstName1[0].toLowerCase();
				String[] firstName2 = inputList.elementAt(row+1).split(" ");
				firstName2[0].toLowerCase();

				int result = firstName2[0].compareTo(firstName1[0]);

				if (result > 0) { //name in the inputList comes before the next name 
					// do nothing 
				}
				else if (result < 0) { //name in the inputList should be placed after the next name
					inputList.setElementAt(name2, row);
					inputList.setElementAt(name1, row+1);
	
				}
				else {
					// do nothing
				}
			}
		}

		return inputList;
	}

}
