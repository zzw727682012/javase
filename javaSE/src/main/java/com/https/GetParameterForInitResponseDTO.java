package com.https;

public class GetParameterForInitResponseDTO {
	private String[] pickup;
	private String[] dropoff;
	private String[] non_integrated;
	private String request_id;
	public String[] getPickup() {
		return pickup;
	}
	public void setPickup(String[] pickup) {
		this.pickup = pickup;
	}
	public String[] getDropoff() {
		return dropoff;
	}
	public void setDropoff(String[] dropoff) {
		this.dropoff = dropoff;
	}
	public String[] getNon_integrated() {
		return non_integrated;
	}
	public void setNon_integrated(String[] non_integrated) {
		this.non_integrated = non_integrated;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}


}
