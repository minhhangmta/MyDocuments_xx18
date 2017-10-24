/**
 * Copyright(C) 2017 Luvina
 * BaseDao.java Oct 23, 2017 minhhang
 */
package manageuser.dao;

import java.sql.Connection;

/**
 * Lớp interface thao tác kết nối tới DB
 * 
 * @author minhhang
 */
public interface BaseDao {
	/**
	 * Hàm mở kết nối với DB
	 * @return Connection
	 */
	public Connection getConnection();
	
	/**
	 * Hàm đóng kết nối với DB
	 */
	public void closeConnection();
}
