package authmanager;

import java.time.Instant;

import org.testng.annotations.Test;

public class TimeCalculator {
	
	
	@Test
	public void ashdksaj()
	{
		
	}
	
	
	public static void main(String[] args) {
		Instant timenow = Instant.now();
		
		System.out.println("Creation time of token "+timenow);
		
		
		Instant expiryTime = timenow.plusSeconds(3600-300);
		
		
		System.out.println("Expiry time of token "+expiryTime);
		
		
		
		
		
	}

}
