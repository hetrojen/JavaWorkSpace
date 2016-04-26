package com.wordfinder.game;

import java.util.ArrayList;
import java.util.List;

public class WordCell {
public final static short DEFAULT=0;	
public final static short H2=1;
public final static short H3=2;
public final static short K2=3;
public final static short K3=4;
public final static short STAR=5;


private char  character='0';
private short cellType=0;
private short columnIndex=0;
private short rowIndex=0;
private boolean empty=true;

private List<Character> tempChars=new ArrayList<Character>();
public char getCharacter() {
	return character;
}
public void setCharacter(char character) {
	this.character = character;
}
public short getCellType() {
	return cellType;
}
public void setCellType(short cellType) {
	this.cellType = cellType;
}
public short getColumnIndex() {
	return columnIndex;
}
public void setColumnIndex(short columnIndex) {
	this.columnIndex = columnIndex;
}
public short getRowIndex() {
	return rowIndex;
}
public void setRowIndex(short rowIndex) {
	this.rowIndex = rowIndex;
}

public void addTempChar(char ch){
	this.tempChars.add(ch);
}

public char getTempChar(short index){
	return this.tempChars.get(index).charValue();
}
public boolean isEmpty() {
	return empty;
}
public void setEmpty(boolean empty) {
	this.empty = empty;
}


}
