package com.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OutputData {

	private List<TotalData> totalData = new ArrayList<TotalData>();
	private List<RankingData> rankingData = new ArrayList<RankingData>();
	
	private static SortType rankSort;
	
	private static enum SortType {
		INCOMING,
		OUTGOING,
		ENTITY
	}
	
	private static final Comparator<TotalData> totalComparator = new Comparator<TotalData>(){

		@Override
		public int compare(TotalData td1, TotalData td2) {
			return td1.getDate().compareTo(td2.getDate());
		}
		
	};
	
	private static final Comparator<RankingData> rankingComparator = new Comparator<RankingData>(){

		@Override
		public int compare(RankingData rd1, RankingData rd2) {
			switch(rankSort){
				case INCOMING:
					return (int)rd2.getIncomingMaximum() - (int)rd1.getIncomingMaximum();
				case OUTGOING:
					return (int)rd2.getOutgoingMaximum() - (int)rd1.getOutgoingMaximum();
				case ENTITY:
					return rd1.getEntity().compareTo(rd2.getEntity());
				default:
					return 0;
			}
		}
		
	};

	public TotalData[] getTotalData() {
		return totalData.toArray(new TotalData[]{});
	}

	public void addTotalData(LocalDate date, double incomingTotalUSD, double outgoingTotalUSD) {
		Optional<TotalData> opt = totalData.stream().filter(item -> item.getDate().equals(date)).findFirst();
		TotalData data;
		if(opt.isPresent()){
			data = opt.get();
		} else {
			data = new TotalData(date, 0, 0);
			totalData.add(data);
		}
		data.setIncomingTotalUSD(data.getIncomingTotalUSD() + incomingTotalUSD);
		data.setOutgoingTotalUSD(data.getOutgoingTotalUSD() + outgoingTotalUSD);
	}

	public void setRankingData(List<RankingData> rankingData) {
		this.rankingData = rankingData;
	}

	public RankingData[] getRankingData() {
		return rankingData.toArray(new RankingData[]{});
	}
	

	public void addRankingData(String entity, double incomingTotalUSD, double outgoingTotalUSD) {
		
		Optional<RankingData> opt = rankingData.stream().filter(item -> item.getEntity().equals(entity)).findFirst();
		RankingData data;
		
		if(opt.isPresent()){
			data = opt.get();
		} else {
			data = new RankingData(entity, 0, 0);
			rankingData.add(data);
		}
		
		if(incomingTotalUSD > data.getIncomingMaximum()) {
			data.setIncomingMaximum(incomingTotalUSD);
		}
		
		if(outgoingTotalUSD > data.getOutgoingMaximum()) {
			data.setOutgoingMaximum(outgoingTotalUSD);
		}
		
	}

	public void rank() {
		
		Collections.sort(totalData, totalComparator);
		
		rankSort = SortType.INCOMING;
		Collections.sort(rankingData, rankingComparator);
		rankOrder();
		
		rankSort = SortType.OUTGOING;
		Collections.sort(rankingData, rankingComparator);
		rankOrder();
		
		rankSort = SortType.ENTITY;
		Collections.sort(rankingData, rankingComparator);
	
	}

	private void rankOrder() {
		
		int rank = 0;
		double lastValue = 0;
		double currentValue = 0;
		for(RankingData data : rankingData) {
			currentValue = rankSort == SortType.INCOMING ? data.getIncomingMaximum() : data.getOutgoingMaximum();
			if(rank == 0 || currentValue != lastValue) {
				rank ++;
			}
			lastValue = currentValue;
			switch(rankSort) {
				case INCOMING:
					data.setIncomingRanking(rank);
					break;
				case OUTGOING:
					data.setOutgoingRanking(rank);
					break;
				default:
					break;
			}

		}
		
	}
		
}
