package com.filosoft.gambersiz.model;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
@PersistenceCapable
public class Product {
private String productId;
private String productName;
private File productImg;
private BigDecimal price;
private Date lastUpdate;
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public File getProductImg() {
	return productImg;
}
public void setProductImg(File productImg) {
	this.productImg = productImg;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public Date getLastUpdate() {
	return lastUpdate;
}
public void setLastUpdate(Date lastUpdate) {
	this.lastUpdate = lastUpdate;
}

}
