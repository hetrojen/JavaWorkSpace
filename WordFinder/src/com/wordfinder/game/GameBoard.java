package com.wordfinder.game;

public class GameBoard {
private static WordGrid  grid;
private static Player players;
public static WordGrid getGrid() {
	return grid;
}
public static void setGrid(WordGrid grid) {
	GameBoard.grid = grid;
}
public static Player getPlayers() {
	return players;
}
public static void setPlayers(Player players) {
	GameBoard.players = players;
}

}
