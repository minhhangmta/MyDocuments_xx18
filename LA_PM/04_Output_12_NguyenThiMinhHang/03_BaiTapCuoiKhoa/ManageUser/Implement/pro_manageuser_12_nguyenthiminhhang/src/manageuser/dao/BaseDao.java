/**
 * Copyright(C) 2017 Luvina
 * BaseDao.java Oct 23, 2017 minhhang
 */
package manageuser.dao;

import java.sql.Connection;

/**
 * Description class here
 * 
 * @author minhhang
 */
public interface BaseDao {
	public Connection getConnection();
	
	public void closeConnection();
}
