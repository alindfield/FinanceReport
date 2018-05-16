package com.report;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.report.InputData;
import com.report.Processor;

public class ProcessorTest {

	private static InputData[] inputData;
	
	private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	static {
		 inputData = new InputData[]{
			 new InputData("test1", "B", 1.32, "GBP", LocalDate.parse("04/09/2017", FORMAT), LocalDate.parse("15/10/2017", FORMAT), 99, 145.24),
			 new InputData("test2", "S", 1.00, "USD", LocalDate.parse("03/09/2017", FORMAT), LocalDate.parse("15/10/2017", FORMAT), 101, 931),
			 new InputData("test4", "S", 0.27, "AED", LocalDate.parse("03/09/2017", FORMAT), LocalDate.parse("09/10/2017", FORMAT), 19, 765.77)
		 };
	}
	
	@Test
	public void testProcess(){

		try {
			Processor processor = new Processor();
			processor.run(inputData);
			processor.print(System.out);
			OutputData data = processor.getData();
			boolean result = true;
			result = result && data.getTotalData()[0].getDate().equals(LocalDate.parse("09/10/2017", FORMAT));
			result = result && data.getTotalData()[1].getDate().equals(LocalDate.parse("15/10/2017", FORMAT));
			result = result && data.getTotalData()[0].getIncomingTotalUSD() == 3928.40;
			result = result && data.getTotalData()[1].getIncomingTotalUSD() == 94031;
			result = result && data.getTotalData()[0].getOutgoingTotalUSD() == 0;
			result = result && data.getTotalData()[1].getOutgoingTotalUSD() == 18979.96;
			result = result && data.getRankingData()[0].getEntity().equals("test1");
			result = result && data.getRankingData()[1].getEntity().equals("test2");
			result = result && data.getRankingData()[2].getEntity().equals("test4");
			result = result && data.getRankingData()[0].getIncomingRanking() == 3;
			result = result && data.getRankingData()[1].getIncomingRanking() == 1;
			result = result && data.getRankingData()[2].getIncomingRanking() == 2;
			result = result && data.getRankingData()[0].getOutgoingRanking() == 1;
			result = result && data.getRankingData()[1].getOutgoingRanking() == 2;
			result = result && data.getRankingData()[2].getOutgoingRanking() == 2;
			assertTrue(result);
		} catch(Throwable t) {
			t.printStackTrace();
		}
		
	}
}
