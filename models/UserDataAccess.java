package models;

import java.sql.*;

import javax.swing.JOptionPane;

import myutils.*;

public class UserDataAccess {
	
	private Connection connect;
	public static int currentUserId;
	
	public UserDataAccess() {
		try {
			this.connect = Util.getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// good idea to make methods that communicate with the db return BOOLEAN for use with controllers
	public boolean registerUser(User user) {

		if(checkIfInDatabase(user)) {
			if(user.getUsername().equals("Enter Username")) {
				JOptionPane.showMessageDialog(null, "Username cannot be left blank.");
				return false;
			}
			else if(user.getUsername().contains(" ")) {
				JOptionPane.showMessageDialog(null, "Username cannot contain a space.");
				return false;
			}
			
			if(user.getPassword().equals("Enter Password")) {
				JOptionPane.showMessageDialog(null, "Password cannot be left blank.");
				return false;
			}
			else if(user.getPassword().contains(" ")) {
				JOptionPane.showMessageDialog(null, "Password cannot contain a space.");
				return false;
			}
			
	
			try {
				String query = "INSERT INTO tb_users(username, password) VALUES (?, ?)";
				PreparedStatement stm = connect.prepareStatement(query);
				
				stm.setString(1, user.getUsername());
				stm.setString(2, Util.hashPassword(user.getPassword()));
				
				int row = stm.executeUpdate();
				
				if (row > 0) {
					return true;
				}
				else {
					return false;
				}
			}
			catch(SQLException e) {
				return false;
			}
		}
		
		
		else {
			JOptionPane.showMessageDialog(null, "User is already in the database.");
			return false;
		}
		

	}
	
	// returns TRUE if the user IS NOT in the database
	// returns FALSE if the user IS the database
	public boolean checkIfInDatabase(User user) {
		try {
			String query = "SELECT id FROM tb_users WHERE username =?";
			PreparedStatement stm = connect.prepareStatement(query);

			stm.setString(1, user.getUsername());
			
			ResultSet rs = stm.executeQuery();
			int rowCount = 0;
			while(rs.next()) {
				rowCount++;
			}

			if (rowCount == 0) {
				return true; // returns true if user IS NOT in the database
			}
			else {
				return false; // returns false if the user IS in the databse
			}
		}
		catch(SQLException e) {
			return false;
		}
	}

	
	
	public boolean verifyUser(String username, String password) {

		try {
			String query = "SELECT id, username FROM tb_users WHERE username=? AND password=?";
			PreparedStatement stm = connect.prepareStatement(query);
			//System.out.println("Connection made from UserDataAccess.verifyUser(String username, String password): line 108");
			stm.setString(1, username);
			stm.setString(2, Util.hashPassword(password));

			ResultSet rs = stm.executeQuery();
			
			int rowCount = 0;
			
			while(rs.next()) {
				rowCount++;
				System.out.println("Row "+rowCount+": "+"Id = "+rs.getInt(1)+", "+"Username = "+rs.getString(2));
				currentUserId = rs.getInt("Id");
				System.out.println("Current user id: "+currentUserId);
			}
			
			if (rowCount == 1) {
				JOptionPane.showMessageDialog(null, "User found.");
				return true;
			}
			else if (rowCount > 1) {
				JOptionPane.showMessageDialog(null, "Error in database or database query.");
				return false;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check credentials and try again.");
				return false;
			}
		


		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
