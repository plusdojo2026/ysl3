package model;

import java.io.Serializable;

public class UserDTO  implements Serializable{
private int id;
private String loginId;
private String password;
private String mail;
private int role;
private int sol;

public UserDTO(int id, String loginId, String password, String mail, int role, int sol) {	
	super();
	this.id = id;
	this.loginId = loginId;
	this.password = password;
	this.mail = mail;
	this.role = role;
	this.sol = sol;
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getLoginId() {
	return loginId;
}

public void setLoginId(String loginId) {
	this.loginId = loginId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public int getRole() {
	return role;
}

public void setRole(int role) {
	this.role = role;
}

public int getSol() {
	return sol;
}

public void setSol(int sol) {
	this.sol = sol;
}

public UserDTO() {	
	super();
	this.id = 0;
	this.loginId = "";
	this.password = "";
	this.mail = "";
	this.role = 0;
	this.sol = 0;


}

}