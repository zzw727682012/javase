package com.https.dto;

public class ShopeeDictionary {

	@DictionaryCode("PARTNER_ID")
	private String partnerId;
	@DictionaryCode("APP_KEY")
	private String appKey;
	@DictionaryCode("SHOP_ID")
	private String shopId;
	@DictionaryCode("CONTENT_TYPE")
	private String content_type;
	@DictionaryCode("HOST")
	private String host;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	
}
