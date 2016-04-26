package com.wordfinder.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVReader;

public class TdkDictionary {
public static String DICTIONARY_FILE_PATH="";
public static final short MAPPED_BY_FIRST_CHARACTER=0;
public static final short MAPPED_BY_FIRST_TWO_CHARACTER=1;
public static HashMap<Character, List<String>> dictMappedWithFirstCharacter=new HashMap<Character, List<String>>();
public static HashMap<String, List<String>> dictMappedWithFirstTwoCharacter=new HashMap<String, List<String>> ();
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

public static void loadDictionary(String file,char deli,boolean sub) {
	// TODO Auto-generated method stub"c:\\guncelsoz.cvs"
	 CSVReader reader;
	 int sayac=0;
	try {
		reader = new CSVReader(new FileReader(file),deli);
		 try {
			List<String[]> myEntries = reader.readAll();
			int top=0;
			for(String[] str:myEntries){
				for(String sttirn:str){
					
					sttirn=sttirn.toLowerCase(new Locale("tr", "TR"));
					sttirn=sttirn.replaceAll("â", "a");
					sttirn=sttirn.replaceAll("î", "i");
						if (sub) {
							try {
								sttirn = sttirn.substring(
										sttirn.indexOf("(") + 1,
										sttirn.lastIndexOf(")") );
							} catch (Exception e) {

								System.out.println(top + "---" + sttirn);
								writeToFile(sttirn,
										"c:\\dictinoray\\map_error.txt");
								continue;
							}

						}
					
					if(sttirn.contains(",")){
						sttirn=sttirn.substring(0, sttirn.indexOf(",")); 
						if(sttirn.length()<2){
							continue;
						}
					}else if(sttirn.contains(" ") && sttirn.indexOf(" ")+1!=sttirn.length()){
						continue;
					}else if(sttirn.length()<2){
						continue;
					}else{
						sttirn=sttirn.trim();
					}
//					if(sttirn==null || sttirn.trim().equals("")){
//						continue;
//					}
//					if(Character.isUpperCase(sttirn.charAt(0))){
//						continue;
//					}
					Character ch=new Character(sttirn.charAt(0));
					String onetwo=sttirn.substring(0, 2);
					
					
					
					if(dictMappedWithFirstTwoCharacter.get(onetwo)==null){
						ArrayList<String> list=new ArrayList<String>();
						dictMappedWithFirstTwoCharacter.put(onetwo,list);
					}
					if(dictMappedWithFirstCharacter.get(ch)==null){
						ArrayList<String> list=new ArrayList<String>();
						dictMappedWithFirstCharacter.put(ch, list);
					}
//					GameUtil.log("firstChar-->"+ch+" value-->"+sttirn,false);
//					GameUtil.log("firstTwo-->"+onetwo+" value-->"+sttirn,false);
					//System.out.println("firstChar-->"+ch+" value-->"+sttirn);
					//System.out.println("firstTwo-->"+onetwo+" value-->"+sttirn+ "intex..:"+sayac++);
					dictMappedWithFirstTwoCharacter.get(onetwo).add(sttirn);
					dictMappedWithFirstCharacter.get(ch).add(sttirn);
					//writeToFile(sttirn,"c:\\dictinoray\\map_"+onetwo+".txt");
					//writeToFile(sttirn,"c:\\dictinoray\\map_"+ch+".txt");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	           
}

public static boolean isWordInDictinory(String word,short dictype)
{
	
	return false;
}
public static List<String> getWordsStartsWith(Character character){
	
	return dictMappedWithFirstCharacter.get(character);
}

}
