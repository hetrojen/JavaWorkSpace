package com.test;

public class scriptTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         String ahmet="zürefanın düşkünü";
         
         if(ahmet.contains(" ")){
        	 System.out.println("contains space");
         }
         if(ahmet.indexOf(" ")+1==ahmet.length()){
        	 System.out.println("last char is space");
         }
	}

}
