package com.filosoft.gambersiz.model;

import java.io.File;
import java.util.Date;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Entity;
@PersistenceCapable
public class GambersizEvent {
private String eventId;
private String description;
private int attendCount;
private String picture;
private Date eventDate;
private String owner;
private String status;
private String location;
private Date saveDate;
@NotPersistent
private EventPreferences eventPreferences;

public String getEventId() {
	return eventId;
}
public void setEventId(String eventId) {
	this.eventId = eventId;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getAttendCount() {
	return attendCount;
}
public void setAttendCount(int attendCount) {
	this.attendCount = attendCount;
}

public String getPicture() {
	return picture;
}
public void setPicture(String picture) {
	this.picture = picture;
}
public Date getEventDate() {
	return eventDate;
}
public void setEventDate(Date eventDate) {
	this.eventDate = eventDate;
}

public String getOwner() {
	return owner;
}
public void setOwner(String owner) {
	this.owner = owner;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public EventPreferences getEventPreferences() {
	return eventPreferences;
}
public void setEventPreferences(EventPreferences eventPreferences) {
	this.eventPreferences = eventPreferences;
}



public Entity toEntity(){
	 
	 Entity entity=new Entity("GambersizEvent");
	 entity.setProperty("eventId", eventId);
	 entity.setProperty("description",description );
	 entity.setProperty("attendCount",attendCount );
	 entity.setProperty("picture",picture );
	 entity.setProperty("eventDate",eventDate );
	 entity.setProperty("owner",owner );
	 entity.setProperty("status",status );
	 entity.setProperty("location",location );
	 entity.setProperty("saveDate",saveDate );
	 return entity;
	 
	
}
public GambersizEvent(){
	
}
public GambersizEvent(Entity entity) {
	// TODO Auto-generated constructor stub
	     if(entity.getProperty("eventId")!=null){
	    	 eventId = (String) entity.getProperty("eventId");
	     }
		 if(entity.getProperty("description")!=null){
			 description = (String) entity.getProperty("description");	
			    }
		 if(entity.getProperty("attendCount")!=null){
			 attendCount = ((Long) entity.getProperty("attendCount")).intValue();
		 }
		 if(entity.getProperty("picture")!=null){
			 picture = (String) entity.getProperty("picture");
		 }
		 if(entity.getProperty("eventDate")!=null){
			 eventDate = (Date) entity.getProperty("eventDate");
		 }
		 if(entity.getProperty("owner")!=null){
			 owner = (String) entity.getProperty("owner");
		 }
		 if(entity.getProperty("status")!=null){
			 status = (String) entity.getProperty("status");
		 }
		 if(entity.getProperty("location")!=null){
			 location = (String) entity.getProperty("location");
		 }
		 if(entity.getProperty("saveDate")!=null){
			 saveDate=(Date)entity.getProperty("saveDate");	
		 }
	

}
public Date getSaveDate() {
	return saveDate;
}
public void setSaveDate(Date saveDate) {
	this.saveDate = saveDate;
}


}
