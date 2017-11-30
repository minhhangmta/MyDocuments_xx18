/**
* Copyright(C) 2017 Luvina
* MstGroupDao.java, Oct 26, 2017 minhhang
*/
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroup;

/**
 * Interface Thao tác với DB bảng mst_group
 * 
 * @author minhhang
 */
public interface MstGroupDao {
	/**
	 * Hàm lấy danh sách group
	 * 
	 * @return List<MstGroup> danh sách group
	 * @throws  ClassNotFoundException, SQLException
	 */
	public List<MstGroup> getAllGroups() throws ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra group có tồn tại trong db không
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return true nếu tồn tại, false nếu không tồn tại
	 * @throws  ClassNotFoundException, SQLException
	 */
	public boolean existGroup(int groupId) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm lấy groupname từ group id
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return String tên nhóm
	 * @throws  ClassNotFoundException, SQLException
	 */
	public String getGroupName(int groupId) throws ClassNotFoundException, SQLException;
}
