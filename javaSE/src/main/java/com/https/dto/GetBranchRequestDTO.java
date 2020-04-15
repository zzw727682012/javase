package com.https.dto;

public class GetBranchRequestDTO {
	private String returnsn;
	private Long partner_id;
	private Long shopid;
	private Long logistic_id;
	public String getReturnsn() {
		return returnsn;
	}
	public void setReturnsn(String returnsn) {
		this.returnsn = returnsn;
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
	public Long getLogistic_id() {
		return logistic_id;
	}
	public void setLogistic_id(Long logistic_id) {
		this.logistic_id = logistic_id;
	}
	
}
