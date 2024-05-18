package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.EditContactWindowController;
import models.Contact;
import models.ContactDataAccess;
import myutils.Util;

public class EditContactWindow extends JDialog {
	private final int WINDOW_WIDTH = 300;
	private final int WINDOW_HEIGHT = 150;
	
	private JPanel editContactPanel;
	private JTextField fNameField, lNameField, phonenumberField;
	private JButton applyChangesBtn, cancelBtn;
	
	public EditContactWindow(ContactView cv) {
		super(cv);
		
		//
		setTitle("Edit Contact");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// intialize components
		editContactPanel = new JPanel(new GridLayout(5,1 , 0,-5));
		fNameField = new JTextField(18);
		lNameField = new JTextField(18);
		phonenumberField = new JTextField(18);
		applyChangesBtn = new JButton("Apply Changes");
		cancelBtn = new JButton("Cancel");
		
		// autofill textfields with the currently selected contact
		fillFields(ContactDataAccess.contactSelected);

		// title sub panel 
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Edit Contact");
		titlePanel.add(title);
		
		// First name field sub panel
		JPanel panel1 = new JPanel();
		fNameField.setFont(Util.TEXT_FONT_PLAIN);
		panel1.add(fNameField);


		// last name field sub panel
		JPanel panel2 = new JPanel();
		lNameField.setFont(Util.TEXT_FONT_PLAIN);
		panel2.add(lNameField);


		// Phone number field sub panel
		JPanel panel3 = new JPanel();
		phonenumberField.setFont(Util.TEXT_FONT_PLAIN);
		panel3.add(phonenumberField);

		// Buttons sub panel
		JPanel panel4 = new JPanel();
		panel4.add(applyChangesBtn);
		panel4.add(cancelBtn);

		// add sub panels to the main panel
		editContactPanel.add(titlePanel);
		editContactPanel.add(panel1);
		editContactPanel.add(panel2);
		editContactPanel.add(panel3);
		editContactPanel.add(panel4);
		
		// add the main panel to the JDialog window
		add(editContactPanel);
		
		// create a new controller for the window
		new EditContactWindowController(this);
		
		
		//
		pack();
		setLocationRelativeTo(null);
		title.requestFocusInWindow();
		setModal(true);
		setVisible(true);
		
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


	//------------Adding action listeners to the buttons--------------
	public void addApplyChangesBtnActionListener(ActionListener listener) {
		applyChangesBtn.addActionListener(listener);
	}
	public void addCancelBtnActionListener(ActionListener listener) {
		cancelBtn.addActionListener(listener);
	}
	
	
	//
	public void fillFields(Contact contact) {
		fNameField.setText(contact.getFirstName());
		lNameField.setText(contact.getLastName());
		String phonenumber;
		try {
			phonenumber = Util.removePhoneNumberFormat(contact.getPhoneNumber()); //removing the mask on the phonenumber;
			phonenumberField.setText(phonenumber);
		} catch (ParseException e) {}
	}

}
