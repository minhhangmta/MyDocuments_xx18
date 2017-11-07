/**
* Copyright(C) 2017 Luvina
* TblDetailUserJapanDao.java, Nov 7, 2017 minhhang
*/
package manageuser.dao;

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
	 */
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan);
}
