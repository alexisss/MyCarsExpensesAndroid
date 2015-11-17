package com.alex.mycarsexpenses.sqllite.helper;

import java.math.BigDecimal;
import java.util.Date;


public class ServiceRecord {
	private int id;
	private int carId;
	private Date date;
	private BigDecimal serviceCost;	
	private Double odometer;
	private String serviceCenter;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(BigDecimal serviceCost) {
		this.serviceCost = serviceCost;
	}
	public Double getOdometer() {
		return odometer;
	}
	public void setOdometer(Double odometer) {
		this.odometer = odometer;
	}
	public String getServiceCenter() {
		return serviceCenter;
	}
	public void setServiceCenter(String serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	
}
