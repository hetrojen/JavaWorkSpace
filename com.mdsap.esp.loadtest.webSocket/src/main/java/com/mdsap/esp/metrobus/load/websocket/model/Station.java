package com.mdsap.esp.metrobus.load.websocket.model;

public class Station {
private int turnstileCount;
private int totalPassCount;
private int minPassInterval;
private int maxPassInterval;
private String stationName;

public Station(String stationName,int turnstileCount,int totalPassCount, int minPassInterval, int maxPassInterval) {
	// TODO Auto-generated constructor stub
  this.turnstileCount=turnstileCount;
  this.totalPassCount=totalPassCount;
  this.minPassInterval=minPassInterval;
  this.maxPassInterval=maxPassInterval;
  this.stationName=stationName;
}
public Station() {
	// TODO Auto-generated constructor stub
}
public int getTurnstileCount() {
	return turnstileCount;
}
public void setTurnstileCount(int turnstileCount) {
	this.turnstileCount = turnstileCount;
}
public int getTotalPassCount() {
	return totalPassCount;
}
public void setTotalPassCount(int totalPassCount) {
	this.totalPassCount = totalPassCount;
}
public int getMinPassInterval() {
	return minPassInterval;
}
public void setMinPassInterval(int minPassInterval) {
	this.minPassInterval = minPassInterval;
}
public int getMaxPassInterval() {
	return maxPassInterval;
}
public void setMaxPassInterval(int maxPassInterval) {
	this.maxPassInterval = maxPassInterval;
}
public String getStationName() {
	return stationName;
}
public void setStationName(String stationName) {
	this.stationName = stationName;
}



}
