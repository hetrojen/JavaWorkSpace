package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;

import com.wordfinder.game.Player;
import com.wordfinder.game.ResultWord;
import com.wordfinder.game.TdkDictionary;
import com.wordfinder.game.WordFinder;
import com.wordfinder.game.WordGrid;

public class DictionaryTester {
public static void main(String[] args) {
    TdkDictionary.loadDictionary("c:\\guncelsoz.cvs",'&',true);
    TdkDictionary.loadDictionary("c:\\guncelsozluk2.cvs",',',false);
    for(Character key:TdkDictionary.dictMappedWithFirstCharacter.keySet()){
    	System.out.println("key...:"+key);
    }
    
    
	Player player=new Player();
	char chr[]={'ı','n','r','t','0','0','0'}; 
//	char chr[]={'k','c','m','s','o','i','a'};
	player.setCharacters(chr); 
	WordFinder wordFinder=new WordFinder(player);
	wordFinder.findWords();
	List<ResultWord> list=wordFinder.getMatchedWords();
	Collections.sort(list);
	   
	for(ResultWord word:list){
		 WordGrid.setWord(word.getStartX(), word.getStartY(), word.getDirection(), word.getWord());
		 System.out.println("kelime .."+word.getWord() +" puanı..:"+word.getPoint());
		 
		 
		 WordGrid.printTheGrid();
		 WordGrid.reset();
		
	}
	
}

public static void writeToFile(String text,String filename) {
    try {
    	 File file=new File(filename);
    	 if(!file.exists()){
    		 file.createNewFile();
    	 }
    	 
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(text);
            bw.newLine();
            bw.close();
    } catch (Exception e) {
    	e.printStackTrace();
    }
}
}
