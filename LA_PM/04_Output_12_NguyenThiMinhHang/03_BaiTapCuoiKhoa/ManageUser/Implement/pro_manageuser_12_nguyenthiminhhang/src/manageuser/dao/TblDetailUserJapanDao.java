/**
* Copyright(C) 2017 Luvina
* TblDetailUserJapanDao.java, Nov 7, 2017 minhhang
*/
package manageuser.dao;

import java.sql.SQLException;

import manageuser.entities.TblDetailUserJapan;

/**
 * Thao tác với bảng tbl_detail_user_japan trong DB
 * 
 * @author minhhang
 */
public interface TblDetailUserJapanDao {
	/**
	 * Inser thông tin chi tiết của user vào bảng tbl_detail_user_japan
	 * 
	 * @param tblDetailUserJapan
	 *            Đối tượng chứa thông tin của TblDetailUserJapan
	 * @return true nếu insert thành công, false nếu không
	 * @throws SQLException 
	 */
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException;

	/**
	 * Hàm lấy code level từ user id
	 * 
	 * @param userId
	 *            mã user
	 * @return String mã code level
	 * @throws SQLException 
	 */
	public String getCodeLevelById(int userId) throws SQLException;

	/**
	 * Hàm cập nhật detail japan
	 * 
	 * @param tblDetailUserJapan
	 *            đối tượng TblDetailUserJapan
	 * @return true nếu update thành công, false nếu không thành công
	 * @throws SQLException 
	 */
	public boolean updateDetailJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException;

	/**
	 * Hàm xóa detail japan theo userId
	 * 
	 * @param userId
	 *            mã user
	 * @return true nếu xóa thành công, false nếu không thành công
	 * @throws SQLException 
	 */
	public boolean deleteDetailJapan(int userId) throws SQLException;

}
