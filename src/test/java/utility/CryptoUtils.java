package utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {
	
	static String key = "1234567890123456";
	
	public static String encryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher c = Cipher.getInstance("AES");
		
		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
		
		return Base64.getEncoder().encodeToString(c.doFinal(data.getBytes()));
		
		
		
	}
	
	
	
	public static String decryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher c = Cipher.getInstance("AES");
		
		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
		
		return new String(c.doFinal(Base64.getDecoder().decode(data)));
		
		
		
	}
	
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		String encryptedString = encryptData("63fd8e9d0fa84a10a5cbf2874c024a0b");
		
		System.out.println("Encrpted data of client id "+encryptedString);
		
		
String decryptedString = decryptData(encryptedString);
		
		System.out.println("decrpted data "+decryptedString);
		
		
String encryptedString2 = encryptData("AQDfYoWFSQAFpVNDPMjBQCO5JMSla5AQHT953tGBPTYWVA01qniqZyg7reyGRLpUaPSITwEM9NNN_3rXEE4h8qThXn-WnuyJdtNZtid1eE0rvs5yQwcnU5ur7pU4x1BkQd0");
		
		System.out.println("Encrpted data of refresh token "+encryptedString2);
		
String encryptedString3 = encryptData("fc0741a0372e4d2eb063918f3d7c77e0");
		
		System.out.println("Encrpted data of client secret "+encryptedString3);
		
		
	}

}
