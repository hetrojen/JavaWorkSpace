package com.oley.payment.mobile.request.processor.util;


public class MOServicesSettings {
    private static SendSmsSetting sendSmsSetting;
    private static SaleWithConfirmSetting saleWithConfirmSetting;
    private  static SaleWithoutConfirmSetting  saleWithoutConfirmSetting;

    public class BaseSetting{
    	private  String   pin;
        private  String userCode;
		public String getPin() {
			return pin;
		}
		public void setPin(String pin) {
			this.pin = pin;
		}
		public String getUserCode() {
			return userCode;
		}
		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
        
    }
    public class SendSmsSetting extends BaseSetting{
    
    }
    public class SaleWithoutConfirmSetting  extends BaseSetting{
    
        
    }
    public class SaleWithConfirmSetting  extends BaseSetting{
  
    }
	public static SendSmsSetting getSendSmsSetting() {
		return sendSmsSetting;
	}
	public static SaleWithConfirmSetting getSaleWithConfirmSetting() {
		return saleWithConfirmSetting;
	}
	public static SaleWithoutConfirmSetting getSaleWithoutConfirmSetting() {
		return saleWithoutConfirmSetting;
	}


}
