package com.https.dto;

import java.util.List;

public class Result {
	private Integer total_count;
	private List<AirwayBill> airway_bills;
	private List<java.lang.Error> errors;
	public Integer getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	public List<AirwayBill> getAirway_bills() {
		return airway_bills;
	}
	public void setAirway_bills(List<AirwayBill> airway_bills) {
		this.airway_bills = airway_bills;
	}
	public List<java.lang.Error> getErrors() {
		return errors;
	}
	public void setErrors(List<java.lang.Error> errors) {
		this.errors = errors;
	}
	
}
