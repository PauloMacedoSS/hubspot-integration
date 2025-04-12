package com.hubspot.dto;

public class WebHookEvent {

	private String eventType;
	private String data;
	
	//GETTERS
	public String getEventType() {
		return eventType;
	}
	public String getData() {
		return data;
	}
	
	//SETTERS
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
