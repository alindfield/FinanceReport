package com.report;

import java.time.LocalDate;

public class InputData {

	private String entity;
	private String type;
	private double rate;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private int units;
	private double pricePerUnit;
	
	public InputData(String entity, String type, double rate, String currency, LocalDate instructionDate,
			LocalDate settlementDate, int units, double pricePerUnit) {
		super();
		this.entity = entity;
		this.type = type;
		this.rate = rate;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}

	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	public int getUnits() {
		return units;
	}
	
	public void setUnits(int units) {
		this.units = units;
	}
	
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
}
