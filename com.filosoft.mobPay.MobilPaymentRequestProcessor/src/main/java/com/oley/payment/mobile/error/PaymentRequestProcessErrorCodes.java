package com.oley.payment.mobile.error;

public interface PaymentRequestProcessErrorCodes {
  public final static int  INVALID_MEMBER_ID=1;
  public final static int  INVALID_TC_KIMLIK_NO=2;
  public final static int  MEMBER_ID_DOES_NOT_MATCH_WITH_PHONE_NUMBER=3;
  public final static int  TC_KIMLIK_NO_DOES_NOT_MATCH_WITH_PHONE_NUMBER=4;
  public final static int  EXCEED_TOTAL_PAYMENT_AMOUNT=5;
  public final static int  EXCEED_PAYMENT_LIMIT=5;
  public final static int  EXCEED_ALLOWED_DIFFERENT_PHONE_NUMBER=6;
  public final static int  NOT_CONFIRMED_SALE=7;
  public final static int  USER_BALANCE_NOT_ENOUGH=8;
  
  
}
