package com.https;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import com.https.dto.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FilaShopeeeManagerImpl {
	private  static final Logger log = LoggerFactory.getLogger(FilaShopeeeManagerImpl.class);
	private static final String DICTIONARY_TYPE = "SHOPEE_INTEGRATION";
	
	private static final String BASE_URL = "https://";
	
	private static final String GET_ADDRESS = "/api/v1/logistics/address/get";

	private static final String TIME_SLOT = "/api/v1/logistics/timeslot/get";
	
	private static final String BRANCH = "/api/v1/logistics/branch/get";

	private static final String AIRWAY_BILL = "/api/v1/logistics/airway_bill/get_mass";

	private static final String INIT_PARAMETER = "/api/v1/logistics/init_parameter/get";
	
	private static final String LOGISTICS_INIT = "/api/v1/logistics/init";

	private static final CloseableHttpClient client = HttpClients.createDefault();

	public void shopeeIntgeration(String orderNo) throws Exception {
		//String orgId = SessionContext.getUser().getOrgId();
		//String projectId = SessionContext.getUser().getProjectId();
		//String orderSn = getOrderSn(orgId, projectId, orderNo);
		//ShopeeDictionary dictionary = getShopeeDictionary(orgId, projectId, DICTIONARY_TYPE);
		
		String orgId = "470";
		String projectId = "590";
		String orderSn = "19070516383A6H8";
		ShopeeDictionary dictionary = new ShopeeDictionary();
		dictionary.setAppKey("8fa7b3157f4e5f5fd728ec89874410d9dad232d69a0c50d5ff446aaff1198cb8");
		dictionary.setContent_type("application/json");
		dictionary.setHost("partner.uat.shopeemobile.com");
		dictionary.setShopId("205753");
		dictionary.setPartnerId("840363");
		


		try { 
			// 解决https本地没有证书错误
			//sslInit();

			String parameterForInitResult = GetParameterForInit(dictionary, orderSn);

			String logisticsInitResult = logisticsInit(parameterForInitResult,dictionary, orderSn);
			LogisticsInitResponseDTO dto = JSONObject.parseObject(logisticsInitResult, LogisticsInitResponseDTO.class);
			//saveTrackingNumber(orgId, projectId, dto.getTracking_number(), orderNo);
			
			String getAirwayBillResult = getAirwayBill(dictionary, orderSn);

		} catch (Exception e) {
			log.error("exception is " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	private String GetParameterForInit(ShopeeDictionary dictionary, String orderSn) throws InvalidKeyException, NoSuchAlgorithmException, ClientProtocolException, IOException{
		log.info("get parameter for init begin");
		
		GetParameterForInitRequestDTO getParameterDTO = new GetParameterForInitRequestDTO();
		getParameterDTO.setOrdersn(orderSn);
		getParameterDTO.setPartner_id(Long.valueOf(dictionary.getPartnerId()));
		getParameterDTO.setShopid(Long.valueOf(dictionary.getShopId()));
		getParameterDTO.setTimestamp(System.currentTimeMillis()/1000L);
		String result =  httpPost(INIT_PARAMETER, getParameterDTO, dictionary);

		System.out.println("get parameter for init result:" + result);
		log.info("get parameter for init result:" + result);
		return result;
	}
	
	private String logisticsInit(String parameterForInitResult, ShopeeDictionary dictionary, String orderSn) throws InvalidKeyException, ClientProtocolException, NoSuchAlgorithmException, IOException{
		
		log.info("logistics init begin");
		
		GetParameterForInitResponseDTO responseDTO = JSONObject.parseObject(parameterForInitResult, GetParameterForInitResponseDTO.class);
		LogisticsInitRequestDTO logisticsInitRequestDTO = new LogisticsInitRequestDTO();
		
		if (responseDTO.getPickup() != null) {
			PickUp pickUp = new PickUp();
			if (parameterForInitResult.contains("address_id")) {
				
				String getAddressResult = GetAddress(dictionary);
				GetAddressResponseDTO getAddressResponseDto = JSONObject.parseObject(getAddressResult, GetAddressResponseDTO.class);
				pickUp.setAddress_id(getAddressResponseDto.getAddress_list()[0].getAddress_id());
				
				if (parameterForInitResult.contains("pickup_time_id")) {
					
					String getTimeSlotResult = GetTimeSlot(dictionary, pickUp.getAddress_id(), orderSn);
					GetTimeSlotResponseDTO timeSlotResponse = JSONObject.parseObject(getTimeSlotResult, GetTimeSlotResponseDTO.class);
					pickUp.setPick_time_id(timeSlotResponse.getPickup_time()[0].getPickup_time_id());
					
				}
			}
			logisticsInitRequestDTO.setPickup(pickUp);
		}
		if (responseDTO.getDropoff() != null) {
			DropOff dropOff = new DropOff();
			if (responseDTO.getDropoff().length > 0) {
				List<String> dropList = Arrays.asList(responseDTO.getDropoff());
				if (dropList.contains("branch_id")) {
					
					String getBranchResult = GetBranch(dictionary, orderSn);
					GetBranchResponseDTO branchResponseDTO = JSONObject.parseObject(getBranchResult, GetBranchResponseDTO.class);
					dropOff.setBranch_id(branchResponseDTO.getBranch()[0].getBranch_id());
					
				}
				if (dropList.contains("sender_real_name")) {
					dropOff.setSender_real_name("");
				}
				if (dropList.contains("tracking_no")) {
					dropOff.setTracking_no("");
				}
			}
			logisticsInitRequestDTO.setDropoff(dropOff);
		}
		if (responseDTO.getNon_integrated() != null) {
			NonIntegrated non = new NonIntegrated();
			if (responseDTO.getNon_integrated().length > 0) {
				non.setTracking_no("");
			}
			logisticsInitRequestDTO.setNon_integrated(non);
		}
		
		logisticsInitRequestDTO.setOrdersn(orderSn);
		logisticsInitRequestDTO.setPartner_id(Long.valueOf(dictionary.getPartnerId()));
		logisticsInitRequestDTO.setShopid(Long.valueOf(dictionary.getShopId()));
		logisticsInitRequestDTO.setTimestamp(System.currentTimeMillis()/1000L);
		String result = httpPost(LOGISTICS_INIT, logisticsInitRequestDTO, dictionary);
		
		log.info("logistics init result:" + result);
		System.out.println("logistics init result:" + result);
		return result;
	}
	
	private String getAirwayBill(ShopeeDictionary dictionary, String orderSn) throws InvalidKeyException, ClientProtocolException, NoSuchAlgorithmException, IOException {
		
		log.info("get airway bill begin");
		
		GetAirwayBillRequestDTO requestDTO = new GetAirwayBillRequestDTO();
		List<String> list = new ArrayList<String>();
		list.add(orderSn);
		requestDTO.setOrdersn_list(list);
		requestDTO.setIs_batch(false);
		requestDTO.setPartner_id(Long.valueOf(dictionary.getPartnerId()));
		requestDTO.setShopid(Long.valueOf(dictionary.getShopId()));
		requestDTO.setTimestamp(System.currentTimeMillis()/1000L);
		String result =  httpPost(AIRWAY_BILL, requestDTO, dictionary);
		
		log.info("get airway bill result:" + result);
		System.out.println("get airway bill result:" + result);
		return result;
	}
	private String GetAddress(ShopeeDictionary dictionary) throws InvalidKeyException, ClientProtocolException, NoSuchAlgorithmException, IOException{
		log.info("get address begin");

		GetAddressRequestDTO getAddressRequestDTO = new GetAddressRequestDTO();
        getAddressRequestDTO.setPartner_id(Long.valueOf(dictionary.getPartnerId()));
        getAddressRequestDTO.setShopid(Long.valueOf(dictionary.getShopId()));
        getAddressRequestDTO.setTimestamp(System.currentTimeMillis()/1000L);
        String result = httpPost(GET_ADDRESS, getAddressRequestDTO, dictionary);
        
		log.info("get address result:" + result);
		System.out.println("get address result:" + result);
        return result;
	}
	
	private String GetTimeSlot(ShopeeDictionary dictionary, long addressId, String orderSn) throws InvalidKeyException, ClientProtocolException, NoSuchAlgorithmException, IOException{
		
		log.info("get time slot begin");

		GetTimeSlotRequestDTO timeSlotRequestDTO = new GetTimeSlotRequestDTO();
		timeSlotRequestDTO.setAddress_id(addressId);
		timeSlotRequestDTO.setOrdersn(orderSn);
		timeSlotRequestDTO.setPartner_id(Long.valueOf(dictionary.getPartnerId()));
		timeSlotRequestDTO.setShopid(Long.valueOf(dictionary.getShopId()));
		timeSlotRequestDTO.setTimestamp(System.currentTimeMillis() / 1000L);
		String result = httpPost(TIME_SLOT, timeSlotRequestDTO, dictionary);
		
		log.info("get time slot result:" + result);
		System.out.println("get time slot result:" + result);
		return result;
	}
	
	private String GetBranch( ShopeeDictionary dictionary, String orderSn) throws InvalidKeyException, ClientProtocolException, NoSuchAlgorithmException, IOException{
		log.info("get branch begin");

		GetBranchRequestDTO branchRequestDTO = new GetBranchRequestDTO();
		branchRequestDTO.setPartner_id(Long.valueOf(dictionary.getPartnerId()));
		branchRequestDTO.setShopid(Long.valueOf(dictionary.getShopId()));
		branchRequestDTO.setReturnsn(orderSn);
		branchRequestDTO.setLogistic_id(System.currentTimeMillis() / 1000L);
		String result = httpPost(BRANCH, branchRequestDTO, dictionary);

		log.info("get branch result:" + result);
		System.out.println("get branch result:" + result);
		return result;
	}
	
	private String httpPost(String url, Object data, ShopeeDictionary dictionary) throws ClientProtocolException, IOException, InvalidKeyException, NoSuchAlgorithmException{
		String jsonData = JSONObject.toJSONString(data);
		String postUrl = BASE_URL + dictionary.getHost() + url;
    	String authorization = getAuthorization(postUrl, jsonData, dictionary.getAppKey());
		System.out.println(postUrl + jsonData + authorization);
		HttpPost post = new HttpPost(postUrl);
	    HttpHost proxy = new HttpHost("172.16.100.2",8080);
	    RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
	    post.setConfig(requestConfig);
	    
	    StringEntity stringEntity = new StringEntity(jsonData);
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType(dictionary.getContent_type());
        post.setEntity(stringEntity);
        post.setHeader("Content-Type", dictionary.getContent_type());
        post.setHeader("Authorization", authorization);

        CloseableHttpResponse res = client.execute(post);
        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String result = EntityUtils.toString(res.getEntity(), "UTF-8");// 返回json格式：
			return result;
        }
        return null;
	}

/*	private void saveTrackingNumber(String orgId, String projectId, String trackNumber, String orderNo){
		WmOutPackingSummaryModel model = new WmOutPackingSummaryModel();
		model.setOrgId(orgId);
		model.setProjectId(projectId);
		model.setWmpsOrderno(orderNo);
		List<WmOutPackingSummaryModel> modelList = this.dao.findByExample(model);
		if(modelList.size()>0){
			model = modelList.get(0);
			model.setWmpsLogisticsid(trackNumber);
			this.dao.save(model);
		}
	}
	
	private String getOrderSn(String orgId, String projectId, String orderNo){
		WmOutOrderModel model = new WmOutOrderModel();
		model.setOrgId(orgId);
		model.setProjectId(projectId);
		model.setWmorOrderNo(orderNo);
		List<WmOutOrderModel> modelList = this.dao.findByExample(model);
		return modelList.size() > 0 ? modelList.get(0).getWmorSoreference4() : null;
	}
	
	private ShopeeDictionary getShopeeDictionary(String orgId, String projectId, String type) throws IllegalArgumentException, IllegalAccessException{
		EbCodeMasterQueryCondition condition = new EbCodeMasterQueryCondition();
		condition.setEbcdEbpjId(Long.valueOf(projectId));
		condition.setEbcdDictionaryType(type);
		List<EbCodeMasterQueryItem> list = this.dao.query(condition, EbCodeMasterQueryItem.class);
		ShopeeDictionary dictionary = new ShopeeDictionary();
		Field[] fields = dictionary.getClass().getFields();
		if(list.size() > 0){
			for (EbCodeMasterQueryItem item : list) {
				for (Field field : fields) {
					String dictionaryCode = field.getAnnotation(DictionaryCode.class).value();
					if(item.getEbcdCode().equals(dictionaryCode)){
						field.setAccessible(true);
						field.set(dictionary, item.getEbcdNameCn());
					}
				}
			}
		}
		return dictionary;
	}*/
	
	private String getAuthorization(String url, String data, String key) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException{
		String baseStr = url + "|" + data;
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        String authorization = Hex.encodeHexString(sha256_HMAC.doFinal(baseStr.getBytes("UTF-8")));
        return authorization;
	}

		private void sslInit() throws NoSuchAlgorithmException, KeyManagementException {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] {new TrustAnyTrustManager()}, new java.security.SecureRandom());
		}
	  	private class TrustAnyTrustManager implements X509TrustManager {

	        public void checkClientTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }

	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[] {};
	        }

	    }

}
