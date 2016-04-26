package com.filosoft.gambersiz.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Entity;
@PersistenceCapable
public class Customer {
 @PrimaryKey
 private String facebookId;
 private Date signupDate;
 
 @NotPersistent
 private String name;
 @NotPersistent
 private String surname;
 
 public Date getSignupDate() {
	return signupDate;
}
public void setSignupDate(Date signupDate) {
	this.signupDate = signupDate;
}
@NotPersistent
 private List<Customer> friends;
 @NotPersistent
 private List<GambersizEvent> events;
 @NotPersistent
 private List<Purchase> purchases;
public String getFacebookId() {
	return facebookId;
}
public void setFacebookId(String facebookId) {
	this.facebookId = facebookId;
}
public List<Customer> getFriends() {
	return friends;
}
public void setFriends(List<Customer> friends) {
	this.friends = friends;
}
public List<GambersizEvent> getEvents() {
	return events;
}

public void setEvents(List<GambersizEvent> events) {
	this.events = events;
}
public List<Purchase> getPurchases() {
	return purchases;
}
public void setPurchases(List<Purchase> purchases) {
	this.purchases = purchases;
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
 
 public Customer() {
	// TODO Auto-generated constructor stub
}
 public Customer(Entity entity) {
		// TODO Auto-generated constructor stub
	 
	    facebookId=(String)entity.getProperty("facebookId");
		signupDate=(Date)entity.getProperty("signupDate");
	 
	}
 public Entity toEntity(){
	 
	 Entity entity=new Entity("Customer","facebookId");
	 entity.setProperty("facebookId", facebookId);
	 entity.setProperty("signupDate",signupDate );
     return entity;
 }
}
