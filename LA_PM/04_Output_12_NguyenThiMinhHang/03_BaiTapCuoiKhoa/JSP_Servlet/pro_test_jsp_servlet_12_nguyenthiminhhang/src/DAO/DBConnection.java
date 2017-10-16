/**
 * Copyright(C) 2017 Luvina
 * DBConnection.java Oct 16, 2017 minhhang
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class DBConnection {
	private Connection connection = null;

	/**
	 * hàm mở connection
	 */
	public Connection getConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
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
