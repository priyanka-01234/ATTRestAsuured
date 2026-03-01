package utility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeProvider {
	
	public static String getCurrentDateAndTime()
	{
		LocalDate date = LocalDate.now();
		
		LocalTime time = LocalTime.now();
		
		System.out.println(date);
		
		System.out.println(time);
		
		String timeValue = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		System.out.println(timeValue);
		
		String value = date+"_"+timeValue;
		
		System.out.println(value);
		
		return value;
	}
	
	public static void main(String[] args) {
		getCurrentDateAndTime();
	}

}
