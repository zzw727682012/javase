package com.https.dto;

public class LogisticsInitRequestDTO {
	private String ordersn;
	private Long partner_id;
	private Long shopid;
	private Long timestamp;
	private PickUp pickup;
	private DropOff dropoff;
	private NonIntegrated non_integrated;
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
	public PickUp getPickup() {
		return pickup;
	}
	public void setPickup(PickUp pickup) {
		this.pickup = pickup;
	}
	public DropOff getDropoff() {
		return dropoff;
	}
	public void setDropoff(DropOff dropoff) {
		this.dropoff = dropoff;
	}
	public NonIntegrated getNon_integrated() {
		return non_integrated;
	}
	public void setNon_integrated(NonIntegrated non_integrated) {
		this.non_integrated = non_integrated;
	}
	
}
