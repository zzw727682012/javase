package com.https.dto;

public class DropOff {
	private Long branch_id;
	private String sender_real_name;
	private String tracking_no;
	
	public Long getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(Long branch_id) {
		this.branch_id = branch_id;
	}
	public String getSender_real_name() {
		return sender_real_name;
	}
	public void setSender_real_name(String sender_real_name) {
		this.sender_real_name = sender_real_name;
	}
	public String getTracking_no() {
		return tracking_no;
	}
	public void setTracking_no(String tracking_no) {
		this.tracking_no = tracking_no;
	}
}
