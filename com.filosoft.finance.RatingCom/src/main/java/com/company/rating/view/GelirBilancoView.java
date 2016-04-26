package com.company.rating.view;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.form.GelirBilancoViewData;
@Component("gelirBilancoView")
@Scope("request")
public class GelirBilancoView  extends  AbstractView implements  Serializable {

	@Autowired
	private GelirBilancoViewData gelirBilancoViewData;
	public GelirBilancoView() {
		// TODO Auto-generated constructor stub
	System.out.println("e");
	} 
	public String testA(){
		
		return "";
	}
	public String hesaplaGelir(){
		//gelirBilancoViewData.getGelirTablosu();
		gelirBilancoViewData.calculateGelirTableFormul();
		return "";
	}
	public  String kaydetGelirBilanco(){
		String msg=gelirBilancoViewData.kaydetGelirBilanco();
		if(msg!=null){
			putErrorMessage(msg);
		}else{
			putInfoMessage("Kaydetme işlemi başarılı");
		}
	  return null;
		
		
	}
	
	
}
