package com.filosoft.gambersiz.util;

import java.util.ArrayList;
import java.util.List;

import com.filosoft.gambersiz.model.Customer;
import com.filosoft.gambersiz.model.GambersizEvent;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Event;
import com.restfb.types.User;

public class RestfbUtil {
	public static final String APP_SECRET="74cd68523bc6eb1b7d9cdfb7d904b6d0";
public static Customer getFacebooUser(String accessToken) throws Exception{
	FacebookClient facebookClient = new DefaultFacebookClient(accessToken,
			APP_SECRET, Version.VERSION_2_5);
	 
	  User user=facebookClient.fetchObject("me", User.class);
	 
	   Customer cust=new Customer();
	   cust.setFacebookId(user.getId());
	   cust.setName(user.getName());
	   cust.setSurname(cust.getSurname());
	
	return cust;
	
	
}
public static boolean correctUserId(String accessToken, String userId) throws Exception{
	FacebookClient facebookClient = new DefaultFacebookClient(accessToken,
			APP_SECRET, Version.VERSION_2_5);
	 
	  User user=facebookClient.fetchObject("me", User.class);
	 if(!user.getId().equals(userId)){
		throw new Exception("Token ve user id eslesmedi");
	 }
	return true;
}
public static List<GambersizEvent> getFacebookEvents(String accessToken) throws Exception{
	FacebookClient facebookClient = new DefaultFacebookClient(accessToken,
			APP_SECRET, Version.VERSION_2_5);
	
	Connection<Event> myEvents = facebookClient.fetchConnection(
			"me/events", Event.class,Parameter.with("Fields", "attending_count,description,startTime,id,picture"));
	List<GambersizEvent> gambersizEvent=new ArrayList<GambersizEvent>();
	 User user=facebookClient.fetchObject("me", User.class);
	for (Event ev : myEvents.getData()) {
	
           GambersizEvent event=new GambersizEvent();
           if(ev.getAttendingCount()!=null){
        	   event.setAttendCount(ev.getAttendingCount());
           }
           if(ev.getDescription()!=null){
        	   event.setDescription(ev.getDescription());
           }
		   if(ev.getStartTime()!=null){
			   event.setEventDate(ev.getStartTime());     	   
			           }
		   if(ev.getId()!=null){
			   event.setEventId(ev.getId());	   
			  }
		   if(ev.getPicture()!=null){
			   event.setPicture(ev.getPicture());	   
			  }
		     if(ev.getPlace()!=null){
			   event.setLocation(ev.getPlace().getLocationAsString());	  
			  }
           
           
           
           
           event.setOwner(user.getId());
	   gambersizEvent.add(event);
	   
	}
	
	
	return gambersizEvent; 
}
}
