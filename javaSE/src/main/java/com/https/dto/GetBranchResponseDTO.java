package com.https.dto;

public class GetBranchResponseDTO {
	private Branch[] branch;
	private String request_id;
	public Branch[] getBranch() {
		return branch;
	}
	public void setBranch(Branch[] branch) {
		this.branch = branch;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	
}
