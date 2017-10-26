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
	 */
	public List<MstGroup> getAllGroups();
}
