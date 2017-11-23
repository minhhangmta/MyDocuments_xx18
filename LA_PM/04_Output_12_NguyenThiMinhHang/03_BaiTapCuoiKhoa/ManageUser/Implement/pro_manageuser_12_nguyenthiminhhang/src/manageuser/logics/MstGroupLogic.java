/**
* Copyright(C) 2017 Luvina
* MstGroupLogic.java, Oct 26, 2017 minhhang
*/
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroup;

/**
 * Interface Xử lý logic cho list group
 * 
 * @author minhhang
 */
public interface MstGroupLogic {
	/**
	 * Hàm lấy danh sách group
	 * 
	 * @return List<MstGroup> danh sách group
	 * @throws SQLException
	 */
	public List<MstGroup> getAllGroups() throws SQLException;
	
	/**
	 * Hàm kiểm tra group có tồn tại trong db không
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return true nếu tồn tại, false nếu không tồn tại
	 */
	public boolean existGroup(int groupId);

	/**
	 * Hàm lấy groupname từ group id
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return String tên nhóm
	 */
	public String getGroupName(int groupId);
}
