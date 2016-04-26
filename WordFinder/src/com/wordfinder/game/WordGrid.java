package com.wordfinder.game;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;


public class WordGrid {
public final static short WORD_GRID_HEIGHT=15;
public final static short WORD_GRID_WITH=15;
public final static short VERTICAL=0;
public final static short HORIZONTAL=1;
public static HashMap<Character, Integer> charPoints=new HashMap<Character,Integer>();
public static WordCell wordcell[][]=new WordCell[WORD_GRID_HEIGHT][WORD_GRID_WITH];

public static short[][] cellTemplate ={{0,0,0,0,0,1,0,0,0,1,0,0,4,0,0},
                                       {0,2,0,0,0,0,1,0,1,0,0,0,0,2,0},
                                       {4,0,0,0,0,0,0,3,0,0,0,0,0,0,4},
                                       {0,0,0,3,0,0,0,0,0,0,0,3,0,0,0},
                                       {0,0,0,0,2,0,0,0,0,0,2,0,0,0,0},
                                       {1,0,0,0,0,1,0,0,0,1,0,0,0,0,1},
                                       {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                                       {0,0,3,0,0,0,0,0,0,0,0,0,3,0,0},
                                       {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                                       {1,0,0,0,0,1,0,0,0,1,0,0,0,0,1},
                                       {0,0,0,0,2,0,0,0,0,0,2,0,0,0,0},
                                       {0,0,0,3,0,0,0,0,0,0,0,3,0,0,0},
                                       {4,0,0,0,0,0,0,3,0,0,0,0,0,0,4},
                                       {0,2,0,0,0,5,1,0,1,0,0,0,0,2,0},
                                       {0,0,4,0,0,1,0,0,0,1,0,0,4,0,0}};

public static char[][] charTemplate ={  {'0','k','a','m','0','0','0','0','0','0','0','0','o','m','0'},
	                                    {'r','a','0','u','0','0','0','d','0','0','0','a','l','i','0'},
	                                    {'a','y','a','r','0','0','0','ı','0','0','0','j','e','l','0'},
	                                    {'0','0','b','i','l','m','i','ş','0','0','0','u','0','l','0'},
	                                    {'0','k','e','s','e','0','0','ı','0','f','ı','r','0','i','z'},
	                                    {'0','0','s','0','0','ç','o','k','ç','a','0','0','0','0','0'},
	                                    {'0','0','0','0','0','0','0','0','a','r','0','0','0','0','0'},
	                                    {'0','0','0','0','t','i','l','a','v','e','t','0','0','0','0'},
	                                    {'0','0','0','0','0','0','e','k','0','0','0','0','0','0','0'},
	                                    {'0','0','0','0','0','0','g','ü','c','e','0','0','0','0','0'},
	                                    {'0','0','0','p','a','t','a','0','0','k','ö','k','0','0','0'},
	                                    {'0','0','0','a','z','e','l','0','0','0','n','ü','0','0','0'},
	                                    {'e','m','a','y','0','k','0','0','0','0','0','l','0','0','0'},
	                                    {'0','0','0','0','h','i','n','0','d','o','s','t','0','0','0'},
	                                    {'0','b','i','r','u','n','0','0','0','0','0','0','0','0','0'}};
//public static char[][] charTemplate ={  {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','a','k','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','y','e','k','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','a','l','a','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','z','e','r','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','s','e','r','ç','i','n','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
//										{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'}};


static{
	charPoints.put(new Character('a'), new Integer(1));
	charPoints.put(new Character('b'), new Integer(3));
	charPoints.put(new Character('c'), new Integer(4));
	charPoints.put(new Character('ç'), new Integer(4));
	charPoints.put(new Character('d'), new Integer(3));
	charPoints.put(new Character('e'), new Integer(1));
	charPoints.put(new Character('f'), new Integer(7));
	charPoints.put(new Character('g'), new Integer(5));
	charPoints.put(new Character('ğ'), new Integer(8));
	charPoints.put(new Character('h'), new Integer(5));
	charPoints.put(new Character('ı'), new Integer(2));
	charPoints.put(new Character('i'), new Integer(1));
	charPoints.put(new Character('j'), new Integer(10));
	charPoints.put(new Character('k'), new Integer(1));
	charPoints.put(new Character('l'), new Integer(1));
	charPoints.put(new Character('m'), new Integer(2));
	charPoints.put(new Character('n'), new Integer(1));
	charPoints.put(new Character('o'), new Integer(2));
	charPoints.put(new Character('ö'), new Integer(7));
	charPoints.put(new Character('p'), new Integer(5));
	charPoints.put(new Character('r'), new Integer(1));
	charPoints.put(new Character('s'), new Integer(2));
	charPoints.put(new Character('ş'), new Integer(4));
	charPoints.put(new Character('t'), new Integer(1));
	charPoints.put(new Character('u'), new Integer(2));
	charPoints.put(new Character('ü'), new Integer(3));
	charPoints.put(new Character('v'), new Integer(7));
	charPoints.put(new Character('y'), new Integer(3));
	charPoints.put(new Character('z'), new Integer(4));
	charPoints.put(new Character('0'), new Integer(0));
	
	
	
	for(short i=0;i<WORD_GRID_HEIGHT;i++){
      for(short j=0;j<WORD_GRID_WITH;j++){
    	  WordCell cell=new WordCell();
    	  cell.setRowIndex(i);
    	  cell.setColumnIndex(j);
    	  cell.setCellType(cellTemplate[i][j]);
    	  cell.setCharacter(charTemplate[i][j]);
    	  if(charTemplate[i][j]=='0'){
    		  cell.setEmpty(true);
    	  }else{
    		  cell.setEmpty(false);
    	  }
    	  wordcell[i][j]=cell;
      }
	}
	
}
public static void reset(){
	for(short i=0;i<WORD_GRID_HEIGHT;i++){
	      for(short j=0;j<WORD_GRID_WITH;j++){
	       	 
	       	wordcell[i][j].setCharacter(charTemplate[i][j]);
	    	  if(charTemplate[i][j]=='0'){
	    		  wordcell[i][j].setEmpty(true);
	    	  }else{
	    		  wordcell[i][j].setEmpty(false);
	    	  }
	      }
		}
	
}
public static  WordCell[]  getColumn(short index){
	    WordCell[] column=new WordCell[WORD_GRID_HEIGHT];
	   for(short i=0;i<WORD_GRID_HEIGHT;i++){
		   column[i]=wordcell[i][index];
	   }
	    
	return column;
}
public static WordCell[] getRow(short index){
	return wordcell[index];
}
public static void setWord(short x,short y,short direction,String word){
	 if(direction==VERTICAL){
		 for(short i=0;i<word.length();i++){
			 wordcell[x+i][y].setCharacter(word.charAt(i)); 
			 wordcell[x+i][y].setEmpty(false);
		 }
	 }
	
	 if(direction==HORIZONTAL){
		 for(short i=0;i<word.length();i++){
			 wordcell[x][y+i].setCharacter(word.charAt(i)); 
			 wordcell[x][y+i].setEmpty(false);
		 }
	 }
	
	return;
}
public static int calculateWordPoint(WordCell startCell,String word,short direction){
 	int total=0;
	
		WordCell[] tempList=null;
	int startPoint=0;
	if(direction==VERTICAL){
		tempList=getColumn(startCell.getColumnIndex()); 
	    startPoint=startCell.getRowIndex(); 
	}
	if(direction==HORIZONTAL){
		tempList=getRow(startCell.getRowIndex());
		startPoint=startCell.getColumnIndex();
	}
	boolean hasBonus=false;
	boolean hasK2=false;
	boolean hasK3=false;
	
	    int j=0;
		for (int i=startPoint;i<word.length()+startPoint;i++) {
			if(tempList[i].isEmpty()){
				switch (tempList[i].getCellType()) {
			case WordCell.H2:
                total+=charPoints.get(word.charAt(j)).intValue()*2;
				break;
			case WordCell.H3:
				total+=charPoints.get(word.charAt(j)).intValue()*3;
				break;
			case WordCell.K2:
				 total+=charPoints.get(word.charAt(j)).intValue();
                 hasK2=true; 
				break;
			case WordCell.K3:
				total+=charPoints.get(word.charAt(j)).intValue();
                 hasK3=true;   
				break;	
			case WordCell.STAR:
				total+=charPoints.get(word.charAt(j)).intValue();
                 hasBonus=true;                 
				break;	

			default:
				total+=charPoints.get(word.charAt(j)).intValue();
				break;
			}
				
			}else{
				total+=charPoints.get(word.charAt(j)).intValue();
			}
			 j++;
          
		}
	  if(hasBonus){
		  total=total+25;
	  }
	  if(hasK2){
		  total=total*2;
	  }
	  if(hasK3){
		  total=total*3;
	  }
	  GameUtil.log("calculating kelime..."+word+" x..."+startCell.getColumnIndex()+" y..."+startCell.getRowIndex()+" direction"+getdirectiondescription(direction)+"calculated point...:"+total);
	  GameUtil.setLogEnable(false);
	  return total;
}
public static short  getColumnFirstScanIndex(){
	
//	for(short i=0;i<WORD_GRID_WITH;i++){
//	  for(short j=0;j<WORD_GRID_HEIGHT;j++){
//		  if(!wordcell[j][i].isEmpty()){
//			  if(i==0){
//				  return 0;
//			  }else{
//				  return (short) (i-1);
//			  }
//		  }
//	  }
//		
//		
//	}
	return 0;
}
public static short  getRowFirstScanIndex(){
//	
//	for(short i=0;i<WORD_GRID_HEIGHT;i++){
//		  for(short j=0;j<WORD_GRID_WITH;j++){
//			  if(!wordcell[i][j].isEmpty()){
//				  if(i==0){
//					  return 0;
//				  }else{
//					  return (short) (i-1);
//				  }
//			  }
//		  }
//			
//			
//		}
	return 0;
}

public static short  getColumnLastScanIndex(){
//		for (short i = WORD_GRID_WITH; i > 0; i--) {
//			for (short j = 0; j < WORD_GRID_HEIGHT; j++) {
//				if (!wordcell[j][i-1].isEmpty()) {
//					if (i == 0) {
//						return 0;
//					} else {
//						return (short) (i);
//					}
//				}
//			}
//
//		}
		return WORD_GRID_WITH-1;
}
public static short  getRowLastScanIndex(){
//	for(short i=WORD_GRID_HEIGHT;i>0;i--){
//		  for(short j=0;j<WORD_GRID_WITH;j++){
//			  if(!wordcell[i-1][j].isEmpty()){
//				  if(i==0){
//					  return 0;
//				  }else{
//					  return (short) (i);
//				  }
//			  }
//		  }
//			
//			
//		}
	return WORD_GRID_HEIGHT-1;
}


public  static int validateCharacter(WordCell cell, short direction, char ch){
	StringBuffer  word=new StringBuffer();
    short x=cell.getRowIndex();
    short y=cell.getColumnIndex();
    short startIndex=0,endIndex=0;
    
    GameUtil.log("validating char for x..:"+x+" for y..:"+y+" character...:"+ch);
    
    
	if(direction==VERTICAL){
		  GameUtil.log("VERTICAL validating char for x..:"+x+" for y..:"+y+" character...:"+ch);
		
		if(y==0){
			startIndex=0;
		}
	    for(short i=y;i>0;i--){
			if(wordcell[x][i-1].isEmpty()){				
				startIndex=i;
				break;
			}
		}
	    if(y==WORD_GRID_WITH-1){
	    	endIndex=WORD_GRID_WITH-1;
	    }
		for(short i=y;i<WORD_GRID_WITH-1;i++){
			if(wordcell[x][i+1].isEmpty()){				
				endIndex=i;
				break;
			}
		}
		
		if(startIndex==endIndex && startIndex==cell.getColumnIndex()){
			return 0;
		}
		
		
		for(short i=startIndex;i<=endIndex;i++){
			if(i==cell.getColumnIndex()){
				word.append(ch);
			}else{
			word.append(wordcell[x][i].getCharacter());
			}
			
		}
		List<String> list=null;
		try{
			list=TdkDictionary.dictMappedWithFirstTwoCharacter.get(word.substring(0, 2));
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			GameUtil.log("x..:"+cell.getRowIndex()+" y..:"+cell.getColumnIndex()+"  direction"+getdirectiondescription(direction)+" char...:"+ch+" word..:"+word, true);
		}
		
 		
		GameUtil.log(" direction VERTICAL validating word...."+word+" kordinat..x:"+cell.getRowIndex()+" y..:"+cell.getColumnIndex());
		 if(list!=null && list.contains(word.toString())){
			 GameUtil.log(" BULUNDU VERTICAL validating word...."+word+" kordinat..x:"+cell.getRowIndex()+" y..:"+cell.getColumnIndex());
			 return calculateWordPoint(wordcell[x][startIndex], word.toString(), HORIZONTAL);
			 		 
		 }		
		
	}
	  word=new StringBuffer();
	  
	 x=cell.getRowIndex();
	 y=cell.getColumnIndex();
	if(direction==HORIZONTAL){
		  GameUtil.log("HORIZONTAL  validating char for x..:"+x+" for y..:"+y+" character...:"+ch);
	   if(x==0){
		   startIndex=0;
	   }
	   for(short i=x;i>0;i--){
			if(wordcell[i-1][y].isEmpty()){				
				startIndex=i;
				break;
			}
		}
	   if(x==WORD_GRID_HEIGHT-1){
		   endIndex=WORD_GRID_HEIGHT-1;
	   }
		for(short i=x;i<WORD_GRID_HEIGHT-1;i++){
			if(wordcell[i+1][y].isEmpty()){				
				endIndex=i;
				break;
			}
		}
		
		if(startIndex==endIndex && startIndex==x){
			
			return 0;
		}
		
		for(short i=startIndex;i<=endIndex;i++){
			if(i==x){
				word.append(ch);
			}else{
			word.append(wordcell[i][y].getCharacter());
			}
		}
	
		List<String> list=null;
		try{
			list=TdkDictionary.dictMappedWithFirstTwoCharacter.get(word.substring(0, 2));
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			GameUtil.log("x..:"+cell.getRowIndex()+" y..:"+cell.getColumnIndex()+"  direction"+getdirectiondescription(direction)+" char...:"+ch+" word..:"+word, true);
		}
		
		
		GameUtil.log(" direction VERTICAL validating word...."+word+" kordinat..x:"+cell.getRowIndex()+" y..:"+cell.getColumnIndex());
		 if(list!=null && list.contains(word.toString())){
			 GameUtil.log(" BULUNDU HORIZONTAL validating word...."+word+" kordinat..x:"+cell.getRowIndex()+" y..:"+cell.getColumnIndex());
			 return calculateWordPoint(wordcell[startIndex][y], word.toString(), VERTICAL);
					 
		 }		
		
	}
	
	
 return  -1;
}
private static int isWordValidforTheRow(WordCell startCell,String word,Player player){
	 //lenght control
	
	   if(WORD_GRID_WITH-startCell.getColumnIndex()<=word.length()){
		   return -1;
	   }
	
	   int x=startCell.getRowIndex();
	  
	   
	 // position control
	   int y=startCell.getColumnIndex()+word.length();
	   if(y<WORD_GRID_WITH && !wordcell[x][y].isEmpty()){
		   return -1;
	   }
	   y=startCell.getColumnIndex();
	   if(y>0 && !wordcell[x][y-1].isEmpty()){
		   return -1;
	   }
	   x=startCell.getRowIndex();	  
	   y=startCell.getColumnIndex();
	  // is there available cell
		int count = 0;
		for (int i = y; i < y + word.length(); i++) {
			if (wordcell[x][i].isEmpty()) {
				count++;
			}
		}
		if (count == 0) {
			return -1;
		}
	  
	  //
	
	 // character position control
	    y=startCell.getColumnIndex();
	   for(int i=0;i<word.length();i++){
		   if(wordcell[x][y+i].isEmpty()==false && word.charAt(i)!=wordcell[x][y+i].getCharacter()){
			   return -1;
		   }
	   }
	   
	   short collision=0;
		 //collisiton control  
			for (int i = 0; i < word.length(); i++) {
				if (!wordcell[x][y+i].isEmpty()) {
					collision++;
					break;
				}

				if (i == 0 && y > 0) {
					if (!wordcell[x][y+i-1].isEmpty()) {
						collision++;
						break;
					}
				}
				if (x> 0) {
					if (!wordcell[x -1][y +i].isEmpty()) {
						collision++;
						break;
					}
				}

				if (x < WORD_GRID_HEIGHT- 1) {
					if (!wordcell[x + 1][y + i].isEmpty()) {
						collision++;
						break;
					}

				}
				if (i == word.length()-1 && (y+i)<WORD_GRID_WITH-1) {
					if (!wordcell[x][y+i+1].isEmpty()) {
						collision++;
						break;
					}
				}

			}
		    if(collision==0){
		    	return -1;
		    }
	 //does user has the characters
	   char temp[]=player.getCharacters().clone();
	   
		boolean userHastheChar = false;
		for (int i = 0; i < word.length(); i++) {
			if (wordcell[x][y + i].isEmpty()) {
				for (short index = 0; index < temp.length; index++) {
					if (temp[index] == word.charAt(i)) {
						temp[index] = '0';
						userHastheChar = true;
						break;
					}
				}
				if (!userHastheChar) {
					return -1;
				} else {
					userHastheChar = false;
				}
			}

		}
	     
	   
	 // is character valid to put in the cell
	   int totalWordPoint=0;
	   for(int i=0;i<word.length();i++){
		   if(wordcell[x][y+i].isEmpty()){
			   int wordWorth=validateCharacter(wordcell[x][y+i], HORIZONTAL,word.charAt(i));
			   if(wordWorth==-1){
				   return -1;
			   }else{
				  totalWordPoint+=wordWorth; 
			   }
			   
		   }
	   }
	   
	
	   
	   
	return totalWordPoint;
}
private static int isWordValidforTheColumn(WordCell startCell,String word,Player player){
	 //lenght control
	
	   if(WORD_GRID_HEIGHT-startCell.getRowIndex()<=word.length()){
		   return -1;
	   }
	
	  
	   int y=startCell.getColumnIndex();
	   
	 // position control
	   int x=startCell.getRowIndex()+word.length();
	   if(x<WORD_GRID_HEIGHT && !wordcell[x][y].isEmpty()){
		   return -1;
	   }
	   x=startCell.getRowIndex();
	   if(x>0 && !wordcell[x-1][y].isEmpty()){
		   return -1;
	   }
	   //
	   x=startCell.getRowIndex();	  
	   y=startCell.getColumnIndex();
		  // is there available cell
			int count = 0;
			for (int i = x; i < x + word.length(); i++) {
				if (wordcell[i][y].isEmpty()) {
					count++;
				}
			}
			if (count == 0) {
				return -1;
			}
		  
		  //
	
	 // character position control
	   x=startCell.getRowIndex();
	   for(int i=0;i<word.length();i++){
		   if(wordcell[x+i][y].isEmpty()==false && word.charAt(i)!=wordcell[x+i][y].getCharacter()){
			   return -1;
		   }
	   }
	  short collision=0;
	 //collisiton control  
		for (int i = 0; i < word.length(); i++) {
			if (!wordcell[x + i][y].isEmpty()) {
				collision++;
				break;
			}

			if (i == 0 && x > 0) {
				if (!wordcell[x + i - 1][y].isEmpty()) {
					collision++;
					break;
				}
			}
			if (y > 0) {
				if (!wordcell[x + i][y - 1].isEmpty()) {
					collision++;
					break;
				}
			}

			if (y < WORD_GRID_WITH - 1) {
				if (!wordcell[x + i][y + 1].isEmpty()) {
					collision++;
					break;
				}

			}
			if (i == word.length()-1 && (x+i)<WORD_GRID_HEIGHT-1) {
				if (!wordcell[x + i+1][y].isEmpty()) {
					collision++;
					break;
				}
			}

		}
	    if(collision==0){
	    	return -1;
	    }
	 //does user has the characters
	   char temp[]=player.getCharacters().clone();
	   
	   boolean userHastheChar=false;
	   for(int i=0;i<word.length();i++){
		   if(wordcell[x+i][y].isEmpty()){
		      for(short index=0;index<temp.length;index++){
		    	if(temp[index]==word.charAt(i)){
		    		temp[index]='0';
		    		userHastheChar=true;
		    		break;
		    	}
		      }			   
		      if(!userHastheChar){
				   return -1;
			   }else{
				   userHastheChar=false;
			   }
		   }
		  
	   }
	   
	   
	   
	 // is character valid to put in the cell
	   int totalWordPoint=0;
	   for(int i=0;i<word.length();i++){
		   if(wordcell[x+i][y].isEmpty()){
			   int wordWorth=validateCharacter(wordcell[x+i][y], VERTICAL,word.charAt(i));
			   if(wordWorth==-1){
				   return -1;
			   }else{
				  totalWordPoint+=wordWorth; 
			   }
		   }
	   }
	   
	   
	return totalWordPoint;
}
public  static int isWordValid(WordCell startCell,String word, short direction,Player player){
	
	if(direction==VERTICAL){
		return isWordValidforTheColumn(startCell, word, player);
	}else{
		return isWordValidforTheRow(startCell, word, player);
	}
}
public static void printTheGrid(){

	for(short i=0;i<WORD_GRID_HEIGHT;i++){
	      for(short j=0;j<WORD_GRID_WITH;j++){
	      	  System.out.print(wordcell[i][j].getCharacter()+",");
	    	  
	      }
	      System.out.println();
	
		}
	
}
public static void printTheGrid(PrintWriter printWriter){

	for(short i=0;i<WORD_GRID_HEIGHT;i++){
	      for(short j=0;j<WORD_GRID_WITH;j++){
	    	  printWriter.print(wordcell[i][j].getCharacter()+",");
	         }
	      printWriter.println();
	   
		}
	 printWriter.println();
	 printWriter.println();
	 printWriter.println();
}
public static WordCell[][] getWordcell() {
	return wordcell;
}
public static String getdirectiondescription(short direction){
	if(direction==HORIZONTAL){
		return "HORİZONTAL";
	}else{
		return "VERTICAL";
	}
	
}

}
