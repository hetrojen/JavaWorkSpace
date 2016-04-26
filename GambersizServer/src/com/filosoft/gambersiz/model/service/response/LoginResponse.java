package com.filosoft.gambersiz.model.service.response;

public class LoginResponse extends BaseResponse{
private boolean firstLogin;
private String name;
private String surname;
private String facebookId;
public boolean isFirstLogin() {
	return firstLogin;
}
public void setFirstLogin(boolean firstLogin) {
	this.firstLogin = firstLogin;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public String getFacebookId() {
	return facebookId;
}
public void setFacebookId(String facebookId) {
	this.facebookId = facebookId;
}

}
