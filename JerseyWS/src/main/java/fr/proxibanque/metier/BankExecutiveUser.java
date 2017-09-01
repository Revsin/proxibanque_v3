/**
 * 
 */
package fr.proxibanque.metier;

/**
 * @author malbranche
 *
 */
public class BankExecutiveUser {

	private String login;
	private String pwd;
	private String surName;
	private String firstName;
	private int exec_ID;
	
	public BankExecutiveUser() {
		super();
	}

	public BankExecutiveUser(String login, String pwd, String surName, String firstName) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.surName = surName;
		this.firstName = firstName;
	}
	
	public String getLogin() {
		return login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getExec_ID() {
		return exec_ID;
	}

	public void setExec_ID(int exec_ID) {
		this.exec_ID = exec_ID;
	}

	@Override
	public String toString() {
		return "BankExecutiveUser [login=" + login + ", pwd=" + pwd + ", surName=" + surName + ", firstName="
				+ firstName + ", exec_ID=" + exec_ID + "]";
	}
	
	
}
