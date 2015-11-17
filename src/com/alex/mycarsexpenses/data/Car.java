package com.alex.mycarsexpenses.data;

public class Car {
	private int id;
	private String make;
	private String model;
	private int year;
	private String registrationNumber;
	private Volume volume;
	private Engine engine;
	
	public Car(){
		
	}
	
	public Car(int id, String make, String model, int year, String registrationNumber, Volume volume, Engine engine){
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.registrationNumber = registrationNumber;
		this.volume = volume;
		this.engine = engine;
	}
	
	public int getId() {
			return this.id;
		}
	
	public void setId(int id){
        this.id = id;
    }
    
    public String getMake(){
        return this.make;
    }
     
    public void setMake(String make){
        this.make = make;
    }
    
    public String getModel(){
        return this.model;
    }
     
    public void setModel(String model){
        this.model= model;
    }
    
    public int getYear(){
        return this.year;
    }
     
    public void setYear(int year){
        this.year= year;
    }
    
    public String getReg(){
        return this.registrationNumber;
    }
     
    public void setReg(String reg){
        this.registrationNumber = reg;
    }
    
    public Engine getEngine(){
        return this.engine;
    }
    
    public Volume getVolume(){
        return this.volume;
    }
}

