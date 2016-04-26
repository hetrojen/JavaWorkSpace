package com.filosoft.gambersiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Event;
import com.restfb.types.User;

@SuppressWarnings("serial")
public class LoadEventsFriends extends AbstractRequestHandler {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String accessToken=req.getParameter("accessToken");
		List<String> messages=initiateResFB(accessToken);
				
		handleRequest(req,resp,messages);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String accessToken=req.getParameter("accessToken");
		List<String> messages=initiateResFB(accessToken);
				
		handleRequest(req,resp,messages);
	}

	 public void handleRequest(HttpServletRequest req, HttpServletResponse resp, List<String> messages)  throws IOException{
			String accessToken=req.getParameter("accessToken");
			initiateResFB(accessToken);
			resp.setContentType("text/plain");
			for(String mes:messages){
				
				resp.getWriter().println(mes);
			}
			
		 
	 }

	private List<String> initiateResFB(String accessToken) {

		ArrayList<String> messges = new ArrayList<String>();

		FacebookClient facebookClient = new DefaultFacebookClient(accessToken,
				"", Version.VERSION_2_5);

		Connection<User> myFriends = facebookClient.fetchConnection(
				"me/friends", User.class,
				Parameter.with("Fields", "name,id,picture"));
		Connection<Event> myEvents = facebookClient.fetchConnection(
				"me/events", Event.class);
		messges.add(" Bu uygulamayi kullanan diger arkadaslari...;");
		for (User friend : myFriends.getData()) {
			String msg = "name..:" + friend.getName() + " id" + friend.getId();
			messges.add(msg);

		}
		messges.add(" Etkinlikler...;"); 
		for (Event ev : myEvents.getData()) {
			String msg = "tanim..:" + ev.getDescription() + " tarih..:" + ev.getStartTime();
			messges.add(msg);

		}
	   return messges;		
	}
}