/**
* Copyright(C) 2017 Luvina
* ConfigProperties.java, Oct 29, 2017 minhhang
*/
package manageuser.properties;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description here
 * 
 * @author minhhang
 */
public class ConfigProperties {
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * read file config.properties
	 */
	static {
		Properties properties = new Properties();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			properties.load(classLoader.getResourceAsStream("config.properties"));
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