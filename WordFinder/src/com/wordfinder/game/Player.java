package com.wordfinder.game;

public class Player {
public static final short PLAYER_USABLE_WORDS_SIZE=7;
	
	private char[] characters=new char[PLAYER_USABLE_WORDS_SIZE];
	private int point;
	public char[] getCharacters() {
		return characters;
	}
	public void setCharacters(char[] characters) {
		this.characters = characters;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	} 
	
	
	
}
