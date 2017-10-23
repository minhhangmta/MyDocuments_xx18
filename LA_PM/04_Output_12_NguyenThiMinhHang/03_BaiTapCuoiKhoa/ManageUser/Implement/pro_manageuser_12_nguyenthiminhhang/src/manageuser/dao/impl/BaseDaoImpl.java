/**
 * Copyright(C) 2017 Luvina
 * BaseDaoImpl.java Oct 23, 2017 minhhang
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manageuser.dao.BaseDao;
import manageuser.properties.DatabaseProperties;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class BaseDaoImpl implements BaseDao {
	private Connection connection = null;
	private String URL;
	private String USER;
	private String PASS;

	/**
	 * hàm mở connection
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		try {
			DatabaseProperties properties = new DatabaseProperties();
			// lấy danh sách property từ file vào
			URL = properties.getData("url"); // lấy giá trị url trong file
			USER = properties.getData("user"); // lấy giá trị user trong file
			PASS = properties.getData("password"); // lấy giá trị password trong file
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
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
