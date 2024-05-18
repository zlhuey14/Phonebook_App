package views;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import controllers.ContactViewController;
import models.ContactDataAccess;
import myutils.Util;

import java.awt.event.*;
import java.awt.*;


public class ContactView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 700;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem item1, item2, item3;
	private JTextField searchField;
	private JList<String> contactList;
	
	public ContactView() {
		// setting initial frame stuff
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		// initialize components menu bar components
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		item1 = new JMenuItem("Add Contact");
		item2 = new JMenuItem("Export Contacts");
		item3 = new JMenuItem("Logout");
		searchField = new JTextField(8);
		contactList = createJList();

		
		// setting up the JPanel that will display the users' contacts
		JPanel contactListPanel = new JPanel();
		JScrollPane contactScrollPane = new JScrollPane(contactList);
		DefaultListModel<String> contactListModel = (DefaultListModel<String>)contactList.getModel();
		if(!contactListModel.isEmpty()) {
			contactList.setSize(new Dimension(100,100));
			contactList.setFont(new Font("monospaced", Font.PLAIN, 12) );
			contactScrollPane.setViewportView(contactList);
			contactListPanel.add(contactScrollPane);
		}
		else {
			JLabel label = new JLabel("User has no contacts. Add contacts from the menu drop down.");
			label.setFont(Util.TEXT_FONT_ITALIC);
			contactListPanel.add(label);
		}
		
		
		// search panel
		JPanel searchPanel = new JPanel();
		searchPanel.add(new JLabel("Search: "));
		searchPanel.add(searchField);
		
		
		// add items to menu
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		
		
		// add menu to the menubar
		menuBar.add(menu);
		
		
		// add menubar to the frame
		setJMenuBar(menuBar);
		add(searchPanel, BorderLayout.NORTH);
		add(contactListPanel, BorderLayout.CENTER);
		
		
		// adding the controller
		new ContactViewController(this);
		
		
		// finalize 
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	// make the JList for the current user
	private JList<String> createJList() {
		JList<String> list = new JList<String>(new ContactDataAccess().getCurrentUserContacts());
		return list;
	}
	
	
	// method to add actionlisteners to the items in the JMenu
	public void addMenuItemListener(ActionListener listener) {
		item1.addActionListener(listener);
		item2.addActionListener(listener);
		item3.addActionListener(listener);
	}
	
	public void addSearchFieldDocumentListener(DocumentListener listener) {
		searchField.getDocument().addDocumentListener(listener);
	}
	
	
	// methods that add a listselectionlistener and a mouse adapter to the contactlist
	public void addContactListListener(ListSelectionListener listener) {
		contactList.addListSelectionListener(listener);
	}
	public void addContactListMouseAdapter(MouseAdapter adapter) {
		contactList.addMouseListener(adapter);
	}
	
	// text field getter
	public String getSearchFieldInput() {
		return searchField.getText();
	}
	
	
	// component getters
	public JMenuItem getItem1() {
		return item1;
	}
	public JMenuItem getItem2() {
		return item2;
	}
	public JMenuItem getItem3() {
		return item3;
	}
	public JList<String> getContactList() {
		return contactList;
	}

}
