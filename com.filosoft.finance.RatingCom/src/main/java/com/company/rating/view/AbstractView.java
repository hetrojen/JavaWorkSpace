package com.company.rating.view;

import java.io.Serializable;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public  class AbstractView implements Serializable{
  
    

	   public ValueExpression createValueEx(String exp,Class clasz){
			ValueExpression ve = FacesContext.getCurrentInstance().getApplication()
			.getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext()
					,
					exp, clasz);
			return ve;
	 }
	   
	   public MethodExpression createMethodEx(String exp,Class clasz, Class clazsl[]){
			MethodExpression ve = FacesContext.getCurrentInstance().getApplication()
			.getExpressionFactory().createMethodExpression(FacesContext.getCurrentInstance().getELContext(), exp, clasz, clazsl);
			return ve;
	 }
public Application getApplication(){
		FacesContext faces = FacesContext.getCurrentInstance ();
	return   faces.getApplication();
}

public void  putErrorMessage(String msg){
	FacesContext context = FacesContext.getCurrentInstance();
	FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null);
	context.addMessage("",facesMessage) ;
}
public void  putInfoMessage(String msg){
	FacesContext context = FacesContext.getCurrentInstance();
	FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,msg,null);
	context.addMessage("",facesMessage) ;
}
		
}
