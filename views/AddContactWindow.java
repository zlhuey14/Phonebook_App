package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddContactWindowController;
import myutils.Util;

public class AddContactWindow extends JDialog{
	private final int WINDOW_WIDTH = 300;
	private final int WINDOW_HEIGHT = 150;
	
	private JPanel addContactPanel;
	private JTextField fNameField, lNameField, phonenumberField;
	private JButton addContactBtn, cancelBtn;
	
	public AddContactWindow(JFrame parent) {
		super(parent);
		
		setTitle("Add Contact");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// intialize components
		addContactPanel = new JPanel(new GridLayout(5,1 , 0,-5));
		fNameField = new JTextField("First name...", 18);
		lNameField = new JTextField("Last name...", 18);
		phonenumberField = new JTextField("Phone Number...", 18);
		addContactBtn = new JButton("Add and Update");
		cancelBtn = new JButton("Cancel");
		
		// title sub panel 
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Add Contact");
		titlePanel.add(title);
		
		// First name field sub panel
		JPanel panel1 = new JPanel();
		fNameField.setFont(Util.TEXT_FONT_ITALIC);
		panel1.add(fNameField);
		
		
		// last name field sub panel
		JPanel panel2 = new JPanel();
		lNameField.setFont(Util.TEXT_FONT_ITALIC);
		panel2.add(lNameField);
		
		
		// Phone number field sub panel
		JPanel panel3 = new JPanel();
		phonenumberField.setFont(Util.TEXT_FONT_ITALIC);
		panel3.add(phonenumberField);
		
		// Buttons sub panel
		JPanel panel4 = new JPanel();
		panel4.add(addContactBtn);
		panel4.add(cancelBtn);
		
		// add sub panels to the main panel
		addContactPanel.add(titlePanel);
		addContactPanel.add(panel1);
		addContactPanel.add(panel2);
		addContactPanel.add(panel3);
		addContactPanel.add(panel4);
		
		// add panels to the frame
		add(addContactPanel);
		
		// adding the controller
		new AddContactWindowController(this);
		
		//
		pack();
		setLocationRelativeTo(null);
		title.requestFocusInWindow();
		setModal(true);
		setVisible(true);
	}
	
	//------------Getters to return text field components--------------
	public JTextField getfNameField() {
		return fNameField;
	}
	public JTextField getlNameField() {
		return lNameField;
	}
	public JTextField getPhonenumberField() {
		return phonenumberField;
	}

	
	//------------Getters to return text field inputs--------------
	public String getfNameInput() {
		return fNameField.getText();
	}
	public String getlNameInput() {
		return lNameField.getText();
	}
	public String getPhonenumberInput() {
		return phonenumberField.getText();
	}
	
	
	//------------Adding action listeners to the buttons--------------
	public void addContactBtnActionListener(ActionListener listener) {
		addContactBtn.addActionListener(listener);
	}
	public void addCancelBtnActionListener(ActionListener listener) {
		cancelBtn.addActionListener(listener);
	}
	
	
	//------------Adding focus listners to the text fields--------------
	public void addFnameFocusListener(FocusListener listener) {
		fNameField.addFocusListener(listener);
	}
	public void addLnameFocusListener(FocusListener listener) {
		lNameField.addFocusListener(listener);
	}
	public void addPhonenumberFocusListener(FocusListener listener) {
		phonenumberField.addFocusListener(listener);
	}


}
