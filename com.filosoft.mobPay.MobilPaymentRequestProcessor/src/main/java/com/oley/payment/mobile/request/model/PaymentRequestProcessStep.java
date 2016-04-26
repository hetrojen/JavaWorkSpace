package com.oley.payment.mobile.request.model;

import java.util.Date;

public class PaymentRequestProcessStep {
public   final static int   	VALIDATE_REQUEST_STEP=1;
public   final static int     	VALIDATE_USER=2;
private int requestID;
private  int state;
private int stepType;
private  Date processDateTime;
private  int errorCode;
}
