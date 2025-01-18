package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
	
	public static String findProperties(String value) throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream file = new FileInputStream("C:\\Users\\2373656\\Desktop\\Testing\\ZigwheelsProject (1)\\ZigwheelsProject\\src\\test\\java\\utility\\config.properties");
		
		prop.load(file);
		
		String result = prop.getProperty(value);
		
		return result;
	}

}
