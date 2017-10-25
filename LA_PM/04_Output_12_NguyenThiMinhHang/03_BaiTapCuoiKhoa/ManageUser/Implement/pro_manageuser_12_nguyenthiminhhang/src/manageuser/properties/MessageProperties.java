/**
 * Copyright(C) 2017 Luvina
 * MessageProperties.java Oct 20, 2017 minhhang
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
 * Đọc các thông tin về các câu thông báo, của hệ thống
 * 
 * @author minhhang
 */
public class MessageProperties {
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * read file message_ja.properties
	 */
	static {
		Properties properties = new Properties();
		try {
			String filename = "message_ja.properties";
			InputStream inputStream = MessageErrorProperties.class.getClassLoader().getResourceAsStream(filename);
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
	 * @param key tên key trong properties
	 * @return data của key
	 */
	public static String getData(String key) {
		String data = "";
		if (map.containsKey(key)) {
			data = map.get(key);
		}
		return data;
	}
}
