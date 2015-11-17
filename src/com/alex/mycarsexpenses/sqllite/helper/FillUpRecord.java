package com.alex.mycarsexpenses.sqllite.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class FillUpRecord implements Serializable{
	
	private int id;
	private int carId;
	private Date date;
	private BigDecimal volumeCost;
	private Double volume;	
	private FuelType fuelType;
	private Double odometer;
	private String gasStation;
	private BigDecimal totalCost;
	
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
	public BigDecimal getVolumeCost() {
		return volumeCost;
	}
	public void setVolumeCost(BigDecimal volumeCost) {
		this.volumeCost = volumeCost;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public Double getOdometer() {
		return odometer;
	}
	public void setOdometer(Double odometer) {
		this.odometer = odometer;
	}
	public String getGasStation() {
		return gasStation;
	}
	public void setGasStation(String gasStation) {
		this.gasStation = gasStation;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
}
