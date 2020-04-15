package com.https.dto;

public class GetAddressResponseDTO {

	private AddressObject[] address_list;
	private String request_id;
	
	public AddressObject[] getAddress_list() {
		return address_list;
	}
	public void setAddress_list(AddressObject[] address_list) {
		this.address_list = address_list;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

}
