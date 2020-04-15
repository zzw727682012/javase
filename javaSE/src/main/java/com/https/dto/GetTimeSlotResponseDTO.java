package com.https.dto;

public class GetTimeSlotResponseDTO {
	private PickUpTimeObject[] pickup_time;
	private String request_id;
	
	public PickUpTimeObject[] getPickup_time() {
		return pickup_time;
	}
	public void setPickup_time(PickUpTimeObject[] pickup_time) {
		this.pickup_time = pickup_time;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
}
