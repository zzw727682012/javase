package com.https.dto;

public class PickUp {
	
	private Long address_id;
	private String pick_time_id;
	private String tracking_no;
	public Long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}
	public String getPick_time_id() {
		return pick_time_id;
	}
	public void setPick_time_id(String pick_time_id) {
		this.pick_time_id = pick_time_id;
	}
	public String getTracking_no() {
		return tracking_no;
	}
	public void setTracking_no(String tracking_no) {
		this.tracking_no = tracking_no;
	}
	
}
