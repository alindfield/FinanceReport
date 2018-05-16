package com.report;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

public class Processor {
	
	private final static String[] TYPES = new String[]{"B", "S"};
	
	private static class DayMap extends HashMap<DayOfWeek, DayOfWeek>{
		private static final long serialVersionUID = 1L;
	}
	
	private static class DayMapRegistry extends HashMap<String, DayMap> {
		private static final long serialVersionUID = 1L;
	}

	private OutputData outputData;
	
	private static DayMapRegistry dayMapRegistry = new DayMapRegistry();
	private static DayMap defaultMap = new DayMap();
	
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
	
	static {
		defaultMap.put(DayOfWeek.SATURDAY, DayOfWeek.MONDAY);
		defaultMap.put(DayOfWeek.SUNDAY, DayOfWeek.MONDAY);
		DayMap friSatMap = new DayMap();
		friSatMap.put(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY);
		friSatMap.put(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		dayMapRegistry.put("AED", friSatMap);
		dayMapRegistry.put("SAR", friSatMap);
	}
		
	private void validateItem(InputData inputData) throws ValidationException{
		
		if(!Arrays.asList(TYPES).contains(inputData.getType())) {
			throw new ValidationException("Invalid Buy/Sell Value " + inputData.getType());
		}
		
	}
	
	private void validate(InputData[] inputData) throws ValidationException{
		
		for(InputData data : inputData) {
			validateItem(data);
		}
		
	}
	
	private int getOffset(String currency, DayOfWeek day){
		
		DayMap dayMap = dayMapRegistry.containsKey(currency) ? dayMapRegistry.get(currency) : defaultMap;
		DayOfWeek workingDay = dayMap.containsKey(day) ? dayMap.get(day) : day;
		int fromDay = day.getValue();
		int toDay = workingDay.getValue();
		int offset = workingDay == day ? 0 : (toDay > fromDay ? (toDay - fromDay) : (toDay - fromDay + 7));
		return offset;
		
	}
	
	private double round(double value){
		return Double.parseDouble(df2.format(value));
	}
	
	private void analyse(InputData[] inputData) {
		
		outputData = new OutputData();
		
		for(InputData data : inputData) {
			
			double usdAmount = data.getPricePerUnit() * data.getUnits() * data.getRate();
			
			int offset = getOffset(data.getCurrency(), data.getSettlementDate().getDayOfWeek());

			LocalDate effectiveDate = data.getSettlementDate(); 
			effectiveDate.plusDays(offset);
			
			double incomingTotalUSD = "S".equals(data.getType()) ? usdAmount : 0;
			double outgoingTotalUSD = "B".equals(data.getType()) ? usdAmount : 0;
			
			outputData.addTotalData(effectiveDate, round(incomingTotalUSD), round(outgoingTotalUSD));
			
			outputData.addRankingData(data.getEntity(), round(incomingTotalUSD), round(outgoingTotalUSD));
		}
		
		outputData.rank();

	}

	public void run(InputData[] inputData) throws ValidationException{
		
		validate(inputData);
		analyse(inputData);
		
	}

	public OutputData getData(){
		
		return outputData;
		
	}
	
	private static String padRight(String s, int n) {
		
	     return String.format("%1$-" + n + "s", s);  
	     
	}

	private static String padLeft(String s, int n) {
		
	    return String.format("%1$" + n + "s", s);  
	    
	}
	
	public void print(PrintStream printStream){
		
		printStream.println("Results");
		printStream.println("=======");
		printStream.println();
		
		printStream.println(padRight("Date", 11) + " " + padLeft("Incoming USD", 16) + " " + padLeft("Outgoing USD", 16));
		printStream.println(padRight("====", 11) + " " + padLeft("============", 16) + " " + padLeft("============", 16));
		for(TotalData data : outputData.getTotalData()) {
			printStream.print(data.getDate().format(formatter) + " ");
			printStream.print(padLeft(String.format("%10.2f", round(data.getIncomingTotalUSD())), 16) + " ");
			printStream.println(padLeft(String.format("%10.2f", round(data.getOutgoingTotalUSD())), 16) + " ");

		}
		
		printStream.println();
		
		printStream.println(padRight("Entity", 18) + " " + padLeft("Incoming Ranking", 16) + " " + padLeft("Outgoing Ranking", 16));
		printStream.println(padRight("======", 18) + " " + padLeft("================", 16) + " " + padLeft("================", 16));
		for(RankingData data : outputData.getRankingData()){
			printStream.print(padRight(data.getEntity(), 18) + " ");
			printStream.print(padLeft(String.valueOf(data.getIncomingRanking()), 16) + " ");
			printStream.println(padLeft(String.valueOf(data.getOutgoingRanking()), 16));
		}
		
		printStream.println();
		printStream.println("Done");
		
	}
	
	public static void run(InputData[] inputData, PrintStream printStream){
		
		try {
			
			Processor processor = new Processor();
			processor.run(inputData);
			processor.print(printStream);
			
		} catch(Throwable t) {
			
			t.printStackTrace();
			
		}
		
	}
}
