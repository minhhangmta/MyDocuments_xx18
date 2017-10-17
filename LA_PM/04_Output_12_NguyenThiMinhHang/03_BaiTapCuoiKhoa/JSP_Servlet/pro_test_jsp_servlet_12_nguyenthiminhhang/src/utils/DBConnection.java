/**
 * Copyright(C) 2017 Luvina
 * DBConnection.java Oct 16, 2017 minhhang
 */
package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Lớp kết nối database
 * 
 * @author minhhang
 */
public class DBConnection {
	private Connection connection = null;
	private String URL;
	private String USER;
	private String PASS;
	private Properties properties =null;

	/**
	 * hàm mở connection
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Properties properties = new Properties();
			properties.load(classLoader.getResourceAsStream("info.properties"));
			// lấy danh sách property từ file vào
			URL = properties.getProperty("url"); // lấy giá trị url trong file
			USER = properties.getProperty("user"); // lấy giá trị user trong file
			PASS = properties.getProperty("password"); // lấy giá trị password trong file
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Hàm đóng connection
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
