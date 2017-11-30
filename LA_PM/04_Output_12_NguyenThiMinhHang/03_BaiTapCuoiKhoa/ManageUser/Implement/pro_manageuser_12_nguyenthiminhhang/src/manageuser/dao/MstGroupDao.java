/**
* Copyright(C) 2017 Luvina
* MstGroupDao.java, Oct 26, 2017 minhhang
*/
package manageuser.dao;

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
	 * @throws Exception 
	 */
	public List<MstGroup> getAllGroups() throws Exception;
	
	/**
	 * Hàm kiểm tra group có tồn tại trong db không
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return true nếu tồn tại, false nếu không tồn tại
	 * @throws Exception 
	 */
	public boolean existGroup(int groupId) throws Exception;

	/**
	 * Hàm lấy groupname từ group id
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return String tên nhóm
	 * @throws Exception 
	 */
	public String getGroupName(int groupId) throws Exception;
}
