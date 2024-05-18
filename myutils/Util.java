package myutils;

import java.awt.Font;
import java.security.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import views.LoginView;

public class Util {
	public static final Font TEXT_FONT_ITALIC = new Font("Dialog Plain", Font.ITALIC, 12);
	public static final Font TEXT_FONT_PLAIN = new Font("Dialog Plain", Font.PLAIN, 12);
	
	private static final String URL="jdbc:mysql:///phonebook_db";
	private static final String USER="root";
	private static final String PASSWORD="Porsche_951!";
	
	public static Connection getConnection() throws SQLException {
		System.out.println("Connection created to phonebook_db.");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes());
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : hashedBytes) {
				stringBuilder.append(String.format("%02x", b));
			}
			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void startApp() {
		new LoginView();
	}
	
	public static String formatPhoneNumber(String phonenumber) throws ParseException {
		String phonenumberMask = "(###)-###-####";
		MaskFormatter maskFormatter = new MaskFormatter(phonenumberMask);
		maskFormatter.setValueContainsLiteralCharacters(false);
		
		return phonenumber = maskFormatter.valueToString(phonenumber);
		
	}
	
	public static String removePhoneNumberFormat(String phonenumber) throws ParseException {
		String unmaskedNumber = phonenumber.replace("(", "").replace(")", "").replace("-", "");
		return unmaskedNumber;
	}
	

}
