package com.wordfinder.game;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {

private Player player;
private List<ResultWord> matchedWords=new ArrayList<ResultWord>();


public WordFinder(Player player) {
	// TODO Auto-generated constructor stub
  
   this.player=player;
}
private void setResult(WordCell cell,String word,short direction,int wordWorth){

	ResultWord resultWord=new ResultWord();
	int point=WordGrid.calculateWordPoint(cell, word,direction);  
    resultWord.setPoint(point+wordWorth);
    resultWord.setStartX(cell.getRowIndex());
    resultWord.setStartY(cell.getColumnIndex());
    resultWord.setWord(word);
    resultWord.setDirection(direction);
    if(!matchedWords.contains(resultWord)){
 	   matchedWords.add(resultWord);
    }
}
private List<Character> getCellChars(WordCell cell){
	 List<Character> tempchars=new ArrayList<Character>();  
	if(!cell.isEmpty()){
		tempchars.add(new Character(cell.getCharacter()));
	}else{
		for (char ch : player.getCharacters()) {
		tempchars.add(new Character(ch));	
	 }
	
	}
	return tempchars;
			
}
public  void  findWords(){
	short startColumn=WordGrid.getColumnFirstScanIndex();
	short startRow=WordGrid.getRowFirstScanIndex();
	short endColumn=WordGrid.getColumnLastScanIndex();
	short endRow=WordGrid.getRowLastScanIndex();

		for (short i = startColumn; i<=endColumn; i++) {//for4
			WordCell[] column = WordGrid.getColumn(i);
           
			for (WordCell cell : column) {// for3
				
				for (Character ch : getCellChars(cell)) { // for2
					List<String> wordList = TdkDictionary.getWordsStartsWith(ch);
					if(wordList==null){
						continue;
					}
					for (String word : wordList) { //for1
					   int wordWorth=WordGrid.isWordValid(cell, word, WordGrid.VERTICAL,player);
                       if(wordWorth>-1){
                    	  setResult(cell, word, WordGrid.VERTICAL, wordWorth);
                           
                       }
					} //for1 end

				} //for2 end
			}//for 3

		}//for4 end

		for (short i = startRow; i <=endRow; i++) {//for4
			WordCell[] row = WordGrid.getRow(i);
                    
			for (WordCell cell : row) {// for3
			   
				for (Character ch : getCellChars(cell)) { // for2
					List<String> wordList = TdkDictionary.getWordsStartsWith(ch);
					if(wordList==null){
						continue;
					}
					for (String word : wordList) { //for1
					   int wordWorth=WordGrid.isWordValid(cell, word, WordGrid.HORIZONTAL,player);
                       if(wordWorth>-1){
                    	   
                    	    System.out.println("kelime..:"+word +" start position..:"+cell.getRowIndex()+","+cell.getColumnIndex()+" direction horizontal");
                    	   setResult(cell, word, WordGrid.HORIZONTAL, wordWorth);
                           
                       }
					} //for1 end

				} //for2 end
			}//for 3

		}//for4 end
	
	
  }

public List<ResultWord> getMatchedWords() {
	return matchedWords;
}

public void setMatchedWords(List<ResultWord> matchedWords) {
	this.matchedWords = matchedWords;
}

}
