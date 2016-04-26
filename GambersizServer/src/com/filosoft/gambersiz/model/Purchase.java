package com.filosoft.gambersiz.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
@PersistenceCapable
public class Purchase {
 private String buyer;
 private String destination;
 private Date purchaseDate;
 private BigDecimal price;
 private String eventId;
public String getBuyer() {
	return buyer;
}
public void setBuyer(String buyer) {
	this.buyer = buyer;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public Date getPurchaseDate() {
	return purchaseDate;
}
public void setPurchaseDate(Date purchaseDate) {
	this.purchaseDate = purchaseDate;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public String getEventId() {
	return eventId;
}
public void setEventId(String eventId) {
	this.eventId = eventId;
}
 
}
