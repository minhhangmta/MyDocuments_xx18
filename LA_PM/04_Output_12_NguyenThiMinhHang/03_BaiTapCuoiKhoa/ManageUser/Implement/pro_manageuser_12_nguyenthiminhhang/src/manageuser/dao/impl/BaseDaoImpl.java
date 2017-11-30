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
 * Implement tới interface BaseDao thao tác kết nối tới DB
 * 
 * @author minhhang
 */
public class BaseDaoImpl implements BaseDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#getConnection()
	 */
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		String url, user, pass, driver;
		// lấy danh sách property từ file vào
		url = DatabaseProperties.getData("url"); // lấy giá trị url trong file
		user = DatabaseProperties.getData("user"); // lấy giá trị user trong file
		pass = DatabaseProperties.getData("password"); // lấy giá trị password trong file
		driver = DatabaseProperties.getData("driver"); // lấy giá trị driver
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, pass);
		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#closeConnection()
	 */
	@Override
	public void closeConnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#rollBack(java.sql.Connection)
	 */
	@Override
	public void rollBack(Connection conn) throws SQLException {
		if (conn != null) {
			conn.rollback();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#commitConnection(java.sql.Connection)
	 */
	@Override
	public void commitConnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.commit();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#setAutoCommitFalse(java.sql.Connection)
	 */
	@Override
	public void setAutoCommitFalse(Connection conn) throws SQLException {
		if (conn != null) {
			conn.setAutoCommit(false);
		}
	}
}
