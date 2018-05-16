package com.report.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.report.InputData;
import com.report.Processor;

public class Test {

	private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static InputData[] inputData;
	
	static {
		 inputData = new InputData[]{
			 new InputData("test1", "B", 1.32, "GBP", LocalDate.parse("04/09/2017", FORMAT), LocalDate.parse("15/10/2017", FORMAT), 99, 145.24),
			 new InputData("test1", "S", 1.00, "USD", LocalDate.parse("03/09/2017", FORMAT), LocalDate.parse("15/10/2017", FORMAT), 101, 931),
			 new InputData("test4", "S", 0.27, "AED", LocalDate.parse("03/09/2017", FORMAT), LocalDate.parse("09/10/2017", FORMAT), 19, 765.77),
			 new InputData("test5", "B", 0.27, "SAR", LocalDate.parse("07/09/2017", FORMAT), LocalDate.parse("12/11/2017", FORMAT), 65, 4000.50),
			 new InputData("test6", "B", 1.32, "GBP", LocalDate.parse("04/09/2017", FORMAT), LocalDate.parse("12/10/2017", FORMAT), 84, 359.45),
			 new InputData("test7", "S", 0.15, "CNY", LocalDate.parse("03/09/2017", FORMAT), LocalDate.parse("12/10/2017", FORMAT), 15, 730),
			 new InputData("test2", "B", 1.16, "EUR", LocalDate.parse("02/09/2017", FORMAT), LocalDate.parse("09/10/2017", FORMAT), 16, 900.80),
			 new InputData("test3", "S", 0.27, "PLN", LocalDate.parse("01/09/2017", FORMAT), LocalDate.parse("07/10/2017", FORMAT), 2, 89.50),
			 new InputData("test3", "S", 1.32, "GBP", LocalDate.parse("04/09/2017", FORMAT), LocalDate.parse("08/10/2017", FORMAT), 62, 717.53),
			 new InputData("test7", "S", 0.15, "CNY", LocalDate.parse("02/09/2017", FORMAT), LocalDate.parse("15/10/2017", FORMAT), 19, 804)
		 };
	}
	
	public static void main(String[] args) {
		try {
			Processor processor = new Processor();
			processor.run(inputData);
			processor.print(System.out);
			System.out.println("Finished");
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
}
