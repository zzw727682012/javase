package com.https.dto;

import java.util.List;

public class GetAirwayBillRequestDTO {
	private List<String> ordersn_list;
	private Boolean is_batch;
	private Long partner_id;
	private Long shopid;
	private Long timestamp;
	public List<String> getOrdersn_list() {
		return ordersn_list;
	}
	public void setOrdersn_list(List<String> ordersn_list) {
		this.ordersn_list = ordersn_list;
	}
	public Boolean getIs_batch() {
		return is_batch;
	}
	public void setIs_batch(Boolean is_batch) {
		this.is_batch = is_batch;
	}
	public Long getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(Long partner_id) {
		this.partner_id = partner_id;
	}
	public Long getShopid() {
		return shopid;
	}
	public void setShopid(Long shopid) {
		this.shopid = shopid;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
