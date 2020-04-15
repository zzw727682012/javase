package com.https.dto;

public class PickUpTimeObject {
	private String pickup_time_id;
	private Integer date;
	private String time_text;
	public String getPickup_time_id() {
		return pickup_time_id;
	}
	public void setPickup_time_id(String pickup_time_id) {
		this.pickup_time_id = pickup_time_id;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public String getTime_text() {
		return time_text;
	}
	public void setTime_text(String time_text) {
		this.time_text = time_text;
	}
	
}
