package com.filosoft.gambersiz.services.endpoint.http;

import javax.inject.Named;

import com.filosoft.gambersiz.model.GambersizEvent;
import com.filosoft.gambersiz.model.service.response.BaseResponse;
import com.filosoft.gambersiz.model.service.response.FacebookEventListResponse;
import com.filosoft.gambersiz.model.service.response.LoginResponse;
import com.filosoft.gambersiz.services.business.GambersizBusinessServices;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(name = "gambersiz",
     version = "v1",
     namespace = @ApiNamespace(ownerDomain = "filosoft.gambersiz.com",
                                 ownerName = "filosoft.gambersiz.com",
                                packagePath=""))

public class GambersizAPI {

  @ApiMethod(name="loginToGambersiz", path = "loginToGambersiz/{accessToken}", httpMethod = "get")
  public LoginResponse sayHi( @Named("accessToken") String accessToken) {
      
	     LoginResponse response=null;
	     try{
	    	 response=GambersizBusinessServices.getCustomer(accessToken);
	    	 response.setSuccess(true); 
	     }catch(Exception e){
	    	 response=new LoginResponse();
	    	 
	    	 response.setSuccess(false);
	    	 response.setErrorMessage(e.getMessage());
	     }

      return response;
  }
  @ApiMethod(name="facebookEvents", path = "facebookEvents/{accessToken}", httpMethod = "get")
  public  FacebookEventListResponse getFacebookEvents( @Named("accessToken") String accessToken){
	   
	  FacebookEventListResponse response=null;
	  
	       try{
	    	 response=GambersizBusinessServices.getFacebookEvents(accessToken);
	    	 response.setSuccess(true); 
	     }catch(Exception e){
	    	 response=new FacebookEventListResponse();
	    	 
	    	 response.setSuccess(false);
	    	 response.setErrorMessage(e.getCause().getMessage()+" "+e.getMessage());
	     }
	  
	  
	  return response;
  }
  @ApiMethod(name="saveEvent", path = "saveEvent", httpMethod = "post")
  public BaseResponse saveEvent(@Named("accessToken") String accessToken, GambersizEvent event){
	  
	  BaseResponse response =new  BaseResponse();
	  try{
	    	 GambersizBusinessServices.saveGambersizEvent(event,accessToken);
	    	 response.setSuccess(true); 
	     }catch(Exception e){
	    	 
	    	 
	    	 response.setSuccess(false);
	    	 response.setErrorMessage(e.getCause().getMessage()+" "+e.getMessage());
	     }
	  return response;
  }

  @ApiMethod(name="savedEvents", path="savedEvents/{facebookId}")
  public FacebookEventListResponse getSavedEvents(@Named("facebookId") String facebookId,@Named("accessToken") String accessToken){
	  FacebookEventListResponse response=null;
	  
     try{
   	 response=GambersizBusinessServices.getSavedEvents(accessToken, facebookId);
   	 response.setSuccess(true); 
    }catch(Exception e){
   	 response=new FacebookEventListResponse();
   	 
   	 response.setSuccess(false);
   	 response.setErrorMessage(e.getCause().getMessage()+" "+e.getMessage());
    }
 
 
    return response;
  }
         
}
