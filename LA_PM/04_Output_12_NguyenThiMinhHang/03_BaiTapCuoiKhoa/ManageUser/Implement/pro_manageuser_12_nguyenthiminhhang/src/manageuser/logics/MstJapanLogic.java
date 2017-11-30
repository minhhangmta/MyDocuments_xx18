/**
 * Copyright(C) 2017 Luvina
 * MstJapanLogic.java Nov 2, 2017 minhhang
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapan;

/**
 * Interface Xử lý logic cho list trình độ tiếng nhật
 * 
 * @author minhhang
 */
public interface MstJapanLogic {
	/**
	 * Hàm lấy danh sách trình độ tiếng nhật
	 * 
	 * @return List<MstJapan> danh sách mstJapan
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */
	public List<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException;

	/**
	 * Hàm lấy tên level từ code level
	 * 
	 * @param codeLevel
	 *            mã level
	 * @return String tên level
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */
	public String getNameLevel(String codeLevel) throws ClassNotFoundException, SQLException;
}
