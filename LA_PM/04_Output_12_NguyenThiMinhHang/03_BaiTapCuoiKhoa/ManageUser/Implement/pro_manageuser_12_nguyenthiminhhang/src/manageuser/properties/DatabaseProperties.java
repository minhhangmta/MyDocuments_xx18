/**
 * Copyright(C) 2017 Luvina
 * DatabaseProperties.java Oct 20, 2017 minhhang
 */
package manageuser.properties;

import java.io.IOException;
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
			properties.load(DatabaseProperties.class.getResourceAsStream("/database.properties"));
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
	 * @param key
	 * @return data 
	 */
	public static String getData(String key) {
		String data = "";
		if (map.containsKey(key)) {
			data = map.get(key);
		}
		return data;
	}
	
}
