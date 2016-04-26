package com.filosoft.gambersiz.model.service.response;

public class BaseResponse {
protected  boolean  success;
protected String errorCode;
protected String errorMessage;

public String getErrorCode() {
	return errorCode;
}
public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}


}
