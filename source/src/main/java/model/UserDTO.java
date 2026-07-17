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

}
