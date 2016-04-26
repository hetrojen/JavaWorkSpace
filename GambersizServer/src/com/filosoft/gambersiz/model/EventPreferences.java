package com.filosoft.gambersiz.model;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Entity;

@PersistenceCapable
public class EventPreferences {
@PrimaryKey
private String preferencesKey;

private boolean acceptPublicPostsOnTheWall;
private boolean acceptGiftPostOnTheWall;

public String getPreferencesKey() {
	return preferencesKey;
}
public void setPreferencesKey(String preferencesKey) {
	this.preferencesKey = preferencesKey;
}
public boolean isAcceptPublicPostsOnTheWall() {
	return acceptPublicPostsOnTheWall;
}
public void setAcceptPublicPostsOnTheWall(boolean acceptPublicPostsOnTheWall) {
	this.acceptPublicPostsOnTheWall = acceptPublicPostsOnTheWall;
}
public boolean isAcceptGiftPostOnTheWall() {
	return acceptGiftPostOnTheWall;
}
public void setAcceptGiftPostOnTheWall(boolean acceptGiftPostOnTheWall) {
	this.acceptGiftPostOnTheWall = acceptGiftPostOnTheWall;
}
public Entity toEntity(){
	 
	 Entity entity=new Entity("EventPreferences","preferencesKey");
	 entity.setProperty("preferencesKey", preferencesKey);
	 entity.setProperty("acceptPublicPostsOnTheWall",acceptPublicPostsOnTheWall );
	 entity.setProperty("acceptGiftPostOnTheWall",acceptGiftPostOnTheWall );
	 return entity;
}
public EventPreferences() {
	// TODO Auto-generated constructor stub
}
public EventPreferences(Entity entity){

	preferencesKey=(String)entity.getProperty("preferencesKey");
    acceptPublicPostsOnTheWall=(boolean)entity.getProperty("acceptPublicPostsOnTheWall");
    acceptGiftPostOnTheWall=(boolean)entity.getProperty("acceptGiftPostOnTheWall");
}
}
