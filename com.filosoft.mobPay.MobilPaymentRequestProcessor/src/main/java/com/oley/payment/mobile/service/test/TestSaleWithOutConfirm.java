package com.oley.payment.mobile.service.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mikro_odeme.services.sale.MAuthToken;
import com.mikro_odeme.services.sale.MSaleInput;
import com.mikro_odeme.services.sale.MSaleOutput;
import com.mikro_odeme.services.sale.MSaleServiceSoap;
import com.mikro_odeme.services.sale.SaleWithoutConfirmResponse;

public class TestSaleWithOutConfirm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 ClassPathXmlApplicationContext context 
         = new ClassPathXmlApplicationContext(
            new String[]{""});

      MSaleServiceSoap client = (MSaleServiceSoap)context.getBean("saleService");

     
      
      
      MAuthToken token=null;
	MSaleInput Input=null;
	MSaleOutput output =client.saleWithoutConfirm(token, Input);
     
      System.exit(0);
		
	}

}
