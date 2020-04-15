package com.https.dto;

public class GetParameterForInitRequestDTO {
	private String ordersn;
	private Long partner_id;
	private Long shopid;
	private Long timestamp;
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
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
