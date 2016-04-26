package com.filosoft.gambersiz.model;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Post {
private String poster;
private String destination;
private String postDate;
private String eventId;
private String message;
public String getPoster() {
	return poster;
}
public void setPoster(String poster) {
	this.poster = poster;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getPostDate() {
	return postDate;
}
public void setPostDate(String postDate) {
	this.postDate = postDate;
}
public String getEventId() {
	return eventId;
}
public void setEventId(String eventId) {
	this.eventId = eventId;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}


}
