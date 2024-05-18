package models;

public class User {
	//private ContactList userContactStorage;
	private int id;
	private String username, password;
	
	public User() {
		username = "";
		password = "";
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		//userContactStorage = new ContactList();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
//	public void showUserContacts() {
//		ContactList contactList = getUserContactStorage();
//		contactList.displayContacts();
//	}
//	
//	public void addUserContact(Contact contact) {
//		ContactList contactList = getUserContactStorage();
//		contactList.addContact(contact);
//	}
	
//	public ContactList getUserContactStorage() {
//	return userContactStorage;
//}
	
	

}
