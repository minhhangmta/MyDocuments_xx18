/**
 * Copyright(C) 2017 Luvina
 * MstJapanDao.java Nov 2, 2017 minhhang
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapan;

/**
 * Interface Thao tác với DB bảng mst_japan
 * 
 * @author minhhang
 */
public interface MstJapanDao {
	/**
	 * Hàm lấy danh sách trình độ tiếng nhật
	 * 
	 * @return List<MstJapan> danh sách mstJapan
	 * @throws SQLException
	 *             ngoại lệ cho SQL
	 * @throws Exception 
	 */
	public List<MstJapan> getAllMstJapan() throws Exception;
	


	/**
	 * Hàm lấy tên level từ code level
	 * 
	 * @param codeLevel
	 *            mã level
	 * @return String tên level
	 * @throws Exception 
	 */
	public String getNameLevel(String codeLevel) throws Exception;
}
