package com.report;

public class RankingData {

	private String entity;
	private double incomingMaximum;
	private double outgoingMaximum;
	private int incomingRanking = 0;
	private int outgoingRanking = 0;
	
	public RankingData(String entity, double incomingMaximum, double outgoingMaximum) {
		super();
		this.entity = entity;
		this.incomingMaximum = incomingMaximum;
		this.outgoingMaximum = outgoingMaximum;
	}

	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public int getIncomingRanking() {
		return incomingRanking;
	}
	
	public double getIncomingMaximum() {
		return incomingMaximum;
	}

	public void setIncomingMaximum(double incomingMaximum) {
		this.incomingMaximum = incomingMaximum;
	}

	public double getOutgoingMaximum() {
		return outgoingMaximum;
	}

	public void setOutgoingMaximum(double outgoingMaximum) {
		this.outgoingMaximum = outgoingMaximum;
	}

	public void setIncomingRanking(int incomingRanking) {
		this.incomingRanking = incomingRanking;
	}
	
	public int getOutgoingRanking() {
		return outgoingRanking;
	}
	
	public void setOutgoingRanking(int outgoingRanking) {
		this.outgoingRanking = outgoingRanking;
	}
	
}
