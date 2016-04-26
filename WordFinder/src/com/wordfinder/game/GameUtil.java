package com.wordfinder.game;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class GameUtil {

private static boolean logEnable=false;
private final static Logger logger = Logger.getLogger("com.wordfinder.game");
static{
	try {
		FileHandler file=new FileHandler("c:\\WordFinder.log");
		file.setFormatter(new SimpleFormatter() );
		logger.addHandler(file);
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static boolean isLogEnable() {
	return logEnable;
}
public static void setLogEnable(boolean logEnable) {
	GameUtil.logEnable = logEnable;
}
public static Logger getLogger() {
	return logger;
} 

public static void log(String msg){
	if(logEnable){
		logger.log(Level.INFO, msg);
	}
	
}

public static void log(String msg,boolean ignore){
	logger.log(Level.INFO, msg);
}


	
}
