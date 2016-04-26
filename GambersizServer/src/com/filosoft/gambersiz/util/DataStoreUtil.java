package com.filosoft.gambersiz.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.filosoft.gambersiz.model.Customer;
import com.filosoft.gambersiz.model.EventPreferences;
import com.filosoft.gambersiz.model.GambersizEvent;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.TransactionOptions;

public class DataStoreUtil {
	
	public static void saveEvent(GambersizEvent event) throws Exception{
		 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		  TransactionOptions options = TransactionOptions.Builder.withXG(true);
		   datastore.beginTransaction(options);

		try {
			event.setSaveDate(new Date());
			Entity gambersizEvent = event.toEntity();
			datastore.put(gambersizEvent);
			String key=event.getOwner()+""+event.getEventId();
			EventPreferences  preferences=event.getEventPreferences();
			if(preferences==null){
				preferences=new EventPreferences();
			}
			preferences.setPreferencesKey(key);
			Entity eventPref=preferences.toEntity();
			
	        datastore.put(eventPref); 
			datastore.getCurrentTransaction().commit();
	

		} catch(Exception e){
			datastore.getCurrentTransaction().rollback();
			
			
			e.printStackTrace();
			throw e;
		}
	}

	public static void saveCustomer(Customer customer) throws Exception{
		
		 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		 datastore.beginTransaction();

		try {
			customer.setSignupDate(new Date());
			Entity cust = customer.toEntity();
			datastore.put(cust);
			
	
			datastore.getCurrentTransaction().commit();
	

		} catch(Exception e){
			datastore.getCurrentTransaction().rollback();
			
			
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public static Customer getCustomer(String facebookId) throws Exception{
		   
		  DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		   Query q = new Query("Customer");
		   Customer customer=null;

			q.setFilter(FilterOperator.EQUAL.of("facebookId", facebookId));
			PreparedQuery pq = ds.prepare(q);
	
			for (Entity entity : pq.asIterable()) {
				customer=new Customer(entity);
			}
			
			return customer;
		
	}

    public static List<GambersizEvent> getSavedEvents(String facebookId){
		  DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		   Query q = new Query("GambersizEvent");
		   List<GambersizEvent> eventList=new ArrayList<GambersizEvent>();

			q.setFilter(FilterOperator.EQUAL.of("owner", facebookId));
			PreparedQuery pq = ds.prepare(q);
	
			for (Entity entity : pq.asIterable()) {
				GambersizEvent gambersizEvent=new GambersizEvent(entity);
				eventList.add(gambersizEvent);
			}
			
			return eventList;
    }

}
