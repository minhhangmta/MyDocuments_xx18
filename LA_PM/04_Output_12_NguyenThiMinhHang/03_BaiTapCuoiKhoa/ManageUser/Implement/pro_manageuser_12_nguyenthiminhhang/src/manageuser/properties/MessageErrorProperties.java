/**
 * Copyright(C) 2017 Luvina
 * MessageErrorProperties.java Oct 20, 2017 minhhang
 */
package manageuser.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Đọc các thông tin về các câu thông báo lỗi của hệ thống
 * 
 * @author minhhang
 */
public class MessageErrorProperties {
	/**
	 * Hàm đọc thông báo lỗi từ file message_error_ja.properties
	 * @return Properties
	 * @throws UnsupportedEncodingException
	 */
	public static Properties readProperties() throws UnsupportedEncodingException {
		Properties prop = new Properties();
		String filename = "message_error_ja.properties";

		InputStream input = MessageErrorProperties.class.getClassLoader().getResourceAsStream(filename);
		BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8"));
		if (input == null) {
			System.out.println("Unable to find " + filename);
			return null;
		}
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return prop;
	}

	/**
	 * Hàm lấy data từ file properties
	 * 
	 * @param key
	 * @return data
	 */
	public static String getData(String key) {
		String data = "";
		try {
			data = MessageErrorProperties.readProperties().getProperty(key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
