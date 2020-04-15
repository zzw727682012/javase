package com.https.dto;

public class GetAirwayBillResponseDTO {
	private Result result;
	private Result batch_result;
	private String request_id;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Result getBatch_result() {
		return batch_result;
	}
	public void setBatch_result(Result batch_result) {
		this.batch_result = batch_result;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	
}
