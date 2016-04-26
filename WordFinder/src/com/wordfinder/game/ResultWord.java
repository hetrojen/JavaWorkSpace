package com.wordfinder.game;

public class ResultWord implements Comparable<ResultWord>{
private short startX;
private short startY;
private String word;
private int point;
private short direction;
public short getStartX() {
	return startX;
}
public void setStartX(short startX) {
	this.startX = startX;
}
public short getStartY() {
	return startY;
}
public void setStartY(short startY) {
	this.startY = startY;
}
public String getWord() {
	return word;
}
public void setWord(String word) {
	this.word = word;
}
public int getPoint() {
	return point;
}
public void setPoint(int point) {
	this.point = point;
}
@Override
public int compareTo(ResultWord o) {
	// TODO Auto-generated method stub
	 if(this.point>o.point){
		 return 1;
	 }else if(this.point==o.point){
		 return 0;
	 }		 
	 return -1;
}
public short getDirection() {
	return direction;
}
public void setDirection(short direction) {
	this.direction = direction;
}
@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
	     ResultWord  result= (ResultWord)obj;
		if(this.startX==result.getStartX() && this.startY==result.getStartY() && 
			this.word.equalsIgnoreCase(result.getWord()) && this.point==result.getPoint()){
			return true;
		}
		return false;
	}
}
