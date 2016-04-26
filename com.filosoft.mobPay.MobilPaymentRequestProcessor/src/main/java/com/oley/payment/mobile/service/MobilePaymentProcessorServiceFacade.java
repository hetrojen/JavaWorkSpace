package com.oley.payment.mobile.service;

import com.mikro_odeme.services.sale.ArrayOfMSaleProduct;
import com.mikro_odeme.services.sale.MAuthToken;
import com.mikro_odeme.services.sale.MSaleInput;
import com.mikro_odeme.services.sale.MSaleOutput;
import com.mikro_odeme.services.sale.MSaleProduct;
import com.mikro_odeme.services.sale.MSaleServiceSoap;
import com.mikro_odeme.services.sale.SaleWithoutConfirmResponse;
import com.oley.payment.mobile.request.model.PaymentRequest;
import com.oley.payment.mobile.request.processor.util.MOServicesSettings;


public class MobilePaymentProcessorServiceFacade {
	public static int TEK_CEKIM=1; // ref   Mikro  ödeme  Teknik entegrasyon dökümanı  versiyon 8.1  <PaymentTypeId>
	public static int BAHIS=7; // ref   Mikro  ödeme  Teknik entegrasyon dökümanı  versiyon 8.1  <PaymentCategoryId>
	 public static SaleWithoutConfirmResponse saleWithoutConfirm(PaymentRequest request) throws Exception{
		 
		 
			MSaleServiceSoap saleService=null;
		 
		     MAuthToken token=new MAuthToken();
			 MSaleInput input=new MSaleInput();
		
			token.setUserCode(MOServicesSettings.getSaleWithoutConfirmSetting().getUserCode());
			token.setPin(MOServicesSettings.getSaleWithoutConfirmSetting().getPin());

			input.setGsm(request.getSenderGSM());
			input.setPaymentTypeId(TEK_CEKIM);
			
			MSaleProduct  product=new MSaleProduct();
			ArrayOfMSaleProduct arrayOfMSaleProduct=new ArrayOfMSaleProduct();
			product.setProductId(0);// always 0 ref   Mikro  ödeme  Teknik entegrasyon dökümanı versiyon 8.1
			product.setProductCategory(BAHIS);
			product.setProductDescription(request.getProductDescription());
			product.setUnit(1);// ürün adedi her zman 1 olacak
			product.setPrice(request.getAmountOfPayment().doubleValue());
			arrayOfMSaleProduct.getMSaleProduct().add(product);
			input.setProductList(arrayOfMSaleProduct); 
			
			MSaleOutput output =saleService.saleWithoutConfirm(token, input);
		 SaleWithoutConfirmResponse saConfirmResponse=new SaleWithoutConfirmResponse();
		 saConfirmResponse.setSaleWithoutConfirmResult(output);
		 return saConfirmResponse;
	 }
	
}
