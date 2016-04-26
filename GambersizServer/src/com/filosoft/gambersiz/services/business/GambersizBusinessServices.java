package com.filosoft.gambersiz.services.business;

import com.filosoft.gambersiz.model.Customer;
import com.filosoft.gambersiz.model.GambersizEvent;
import com.filosoft.gambersiz.model.service.response.BaseResponse;
import com.filosoft.gambersiz.model.service.response.FacebookEventListResponse;
import com.filosoft.gambersiz.model.service.response.LoginResponse;
import com.filosoft.gambersiz.util.DataStoreUtil;
import com.filosoft.gambersiz.util.RestfbUtil;

public class GambersizBusinessServices {
	
	public static LoginResponse getCustomer(String accessToken) throws Exception{
		
		LoginResponse response=new LoginResponse();
		Customer cust=RestfbUtil.getFacebooUser(accessToken);
		if(cust==null){
			throw new Exception("Error occurred while fetching the User from facebook"); 
			
		}
		
		Customer customer=DataStoreUtil.getCustomer(cust.getFacebookId());
		if(customer==null){
			try{
				DataStoreUtil.saveCustomer(cust);
				response.setFirstLogin(true);
			}catch (Exception e)
			{
				 throw e;  
			}
		}
		
		
		response.setName(cust.getName());
		response.setSurname(cust.getSurname());
		response.setFacebookId(cust.getFacebookId());
		
	    return response;  
	}

    public static FacebookEventListResponse getFacebookEvents(String accessToken) throws Exception{
    	
    	FacebookEventListResponse response=new FacebookEventListResponse();
    	response.setFacebookEventList(RestfbUtil.getFacebookEvents(accessToken));
  
    	return response;
    }

     public static void saveGambersizEvent(GambersizEvent gambersizEvent, String accessToken) throws Exception{
    	 
         RestfbUtil.correctUserId(accessToken, gambersizEvent.getOwner());
    	
    	 DataStoreUtil.saveEvent(gambersizEvent);
     }
     
     public static FacebookEventListResponse getSavedEvents(String accessToken, String facebookId) throws Exception{
    	   
    	 RestfbUtil.correctUserId(accessToken, facebookId);
     	 FacebookEventListResponse response=new FacebookEventListResponse();
     	 response.setFacebookEventList(DataStoreUtil.getSavedEvents(facebookId));
     	 
     	 return response;
    	 
     }

}

