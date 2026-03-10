package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	public static String readPropData(String propToBeRead) throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
		
		FileInputStream fis = new FileInputStream(path);
		
		Properties prop = new Properties();
		
		prop.load(fis);
		
		
		String data = prop.getProperty(propToBeRead);
		
		System.out.println(data);
		
		return data;
		
		
	}
	
	public static void main(String[] args) throws IOException {
		readPropData("client_id");
		readPropData("client_secret");
		
	}
	
	

}
