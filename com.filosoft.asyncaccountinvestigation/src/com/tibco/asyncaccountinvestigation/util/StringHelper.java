package com.tibco.asyncaccountinvestigation.util;

public class StringHelper {
	public static boolean isEmpty(String str) {
		
	    if(str.equals(null) || str.equals("")) 
	        return true;
	    else
	        return false;
	}

	public static String prettyPrinter(String[] in){
		String out = "";
		for (String token:in)
			out+="#"+token;
		out+="#";
		return out;
	}

}
