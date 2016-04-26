package com.filosoft.gambersiz.model.service.response;

import java.util.List;

import com.filosoft.gambersiz.model.GambersizEvent;

public class FacebookEventListResponse extends BaseResponse{
	
	private List<GambersizEvent> facebookEventList;

	public List<GambersizEvent> getFacebookEventList() {
		return facebookEventList;
	}

	public void setFacebookEventList(List<GambersizEvent> facebookEventList) {
		this.facebookEventList = facebookEventList;
	}
	

}
