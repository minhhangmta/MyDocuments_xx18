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
	 * 
	 * @return Connection kết nối
	 */
	public Connection getConnection();

	/**
	 * Hàm đóng kết nối với DB
	 * 
	 * @param conn
	 *            connection cần đóng
	 */
	public void closeConnection(Connection conn);

	/**
	 * Hàm rollback
	 * 
	 * @param conn
	 *            connection
	 */
	public void rollBack(Connection conn);

	/**
	 * Hàm commit
	 * 
	 * @param conn
	 *            connection
	 */
	public void commitConnection(Connection conn);

	/**
	 * Hàm set auto commit to false
	 * 
	 * @param conn
	 *            connection
	 */
	public void setAutoCommitFalse(Connection conn);
}
