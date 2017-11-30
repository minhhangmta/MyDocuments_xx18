/**
 * Copyright(C) 2017 Luvina
 * BaseDao.java Oct 23, 2017 minhhang
 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

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
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException;

	/**
	 * Hàm đóng kết nối với DB
	 * 
	 * @param conn
	 *            connection cần đóng
	 * @throws SQLException
	 */
	public void closeConnection(Connection conn) throws SQLException;

	/**
	 * Hàm rollback
	 * 
	 * @param conn
	 *            connection
	 * @throws SQLException
	 */
	public void rollBack(Connection conn) throws SQLException;

	/**
	 * Hàm commit
	 * 
	 * @param conn
	 *            connection
	 * @throws SQLException
	 */
	public void commitConnection(Connection conn) throws SQLException;

	/**
	 * Hàm set auto commit to false
	 * 
	 * @param conn
	 *            connection
	 * @throws SQLException
	 */
	public void setAutoCommitFalse(Connection conn) throws SQLException;
}
