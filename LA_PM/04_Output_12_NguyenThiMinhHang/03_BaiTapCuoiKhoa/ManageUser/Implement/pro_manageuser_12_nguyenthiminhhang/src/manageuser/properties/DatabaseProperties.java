/**
 * Copyright(C) 2017 Luvina
 * DatabaseProperties.java Oct 20, 2017 minhhang
 */
package manageuser.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Lớp đọc các thông tin thiết định kết nối tới database
 * 
 * @author minhhang
 */
public class DatabaseProperties {
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * read file database.properties
	 */
	static {
		Properties properties = new Properties();
		try {
			InputStream inputStream = MessageErrorProperties.class.getClassLoader().getResourceAsStream("database.properties");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			properties.load(bufferedReader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		@SuppressWarnings("unchecked")
		Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			map.put(key, properties.getProperty(key));
		}
	}

	/**
	 * Hàm lấy data từ file properties
	 * 
	 * @param key
	 *            tên key trong properties
	 * @return String data của key
	 */
	public static String getData(String key) {
		String data = "";
		if (map.containsKey(key)) {
			data = map.get(key);
		}
		return data;
	}
}
