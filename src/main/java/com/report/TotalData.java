package com.report;

import java.time.LocalDate;

public class TotalData {

	private LocalDate date;
	private double incomingTotalUSD;
	private double outgoingTotalUSD;
	
	public TotalData(LocalDate date, double incomingTotalUSD, double outgoingTotalUSD) {
		super();
		this.date = date;
		this.incomingTotalUSD = incomingTotalUSD;
		this.outgoingTotalUSD = outgoingTotalUSD;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public double getIncomingTotalUSD() {
		return incomingTotalUSD;
	}
	
	public void setIncomingTotalUSD(double incomingTotalUSD) {
		this.incomingTotalUSD = incomingTotalUSD;
	}
	
	public double getOutgoingTotalUSD() {
		return outgoingTotalUSD;
	}
	
	public void setOutgoingTotalUSD(double outgoingTotalUSD) {
		this.outgoingTotalUSD = outgoingTotalUSD;
	}

}
